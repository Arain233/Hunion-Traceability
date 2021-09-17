<template>
  <div class="login-container">
    <el-form ref="loginForm" :model="loginForm" :rules="loginRules" class="login-form" autocomplete="on"
             label-position="left">

      <div class="title-container">
        <h3 class="title">
          {{ $t('login.title') }}
        </h3>
        <lang-select class="set-language"/>
      </div>

      <el-form-item prop="name">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="name"
          v-model="loginForm.name"
          :placeholder="$t('login.username')"
          name="name"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="Caps lock is On" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <svg-icon icon-class="password"/>
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="loginForm.password"
            :type="passwordType"
            :placeholder="$t('login.password')"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="handleLogin"
          />
          <span class="show-pwd" @click="showPwd">
            <svg-icon :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'" style="color: white"/>
          </span>
        </el-form-item>
      </el-tooltip>

      <el-form-item prop="kapt">
        <span class="svg-container">
          <svg-icon icon-class="code"/>
        </span>
        <el-input
          ref="code"
          v-model="loginForm.kapt"
          :placeholder="$t('login.code')"
          name="kapt"
          type="text"
          tabindex="3"
          autocomplete="on"
        />
        <span class="show-captcha">
          <img :src="captchaUrl" alt="登录验证码" title="看不清？点击切换" @click="handleRefreshCode">
        </span>
      </el-form-item>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;"
                 @click.native.prevent="handleLogin">
        {{ $t('login.logIn') }}
      </el-button>
      <el-row></el-row>
      <el-button
        style="width:100%;margin-bottom:30px;"
        type="warning"
        plain
        @click.native.prevent="handleRegister"
      >{{ $t('login.register') }}
      </el-button>
    </el-form>

    <el-dialog :title="$t('login.thirdparty')" :visible.sync="showDialog">
      {{ $t('login.thirdpartyTips') }}
      <br>
      <br>
      <br>
      <social-sign/>
    </el-dialog>
  </div>
</template>

<script>
import {validUsername} from '@/utils/validate'
import LangSelect from '@/components/LangSelect'
import SocialSign from './components/SocialSignin'
import {sm2} from "@/utils/sm2";
import {sm2Decrypt, doGenerate} from "@/utils/sm2 decrypt";
import {Hex} from "@/utils/hex";
import {SM4} from "@/utils/sm4-1.0";
import axios from "axios";

export default {
  name: 'Login',
  components: {LangSelect, SocialSign},
  inject: ['reload'],
  data() {
    const validateUsername = (rule, value, callback) => {
      if (!validUsername(value)) { // 验证用户名的合法性
        callback(new Error('Please enter the correct user name'))
      } else {
        callback()
      }
    }
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能少于五位!'))
      } else {
        callback()
      }
    }

    const validateCode = (rule, value, callback) => {
      if (value.length != 5) {
        callback(new Error('请输入正确的验证码!'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        name: '',
        password: '',
        kapt: ''
      },
      loginRules: {
        name: [{required: true, trigger: 'blur', validator: validateUsername}],
        password: [{required: true, trigger: 'blur', validator: validatePassword}],
        kapt: [{required: true, trigger: 'blur', validator: validateCode}]
      },
      passwordType: 'password',
      capsTooltip: false,
      captchaUrl: '',
      loading: false,
      showDialog: false,
      redirect: undefined,
      otherQuery: {}
    }
  },
  watch: {
    $route: {
      handler: function (route) {
        const query = route.query
        if (query) {
          this.redirect = query.redirect
          this.otherQuery = this.getOtherQuery(query)
        }
      },
      immediate: true
    }
  },
  created() {
    this.captchaUrl = 'http://120.76.197.109:8282/api/captcha.jpg'
    console.log('code initialized.');
    let keypair = doGenerate();
    this.$store.commit('SET_PRIKEYHEX', keypair.ecprvhex);
    sessionStorage.setItem("prikeyHex", keypair.ecprvhex);
    this.$store.commit('SET_PUBKEYHEX', keypair.ecpubhex);
    sessionStorage.setItem("pubkeyHex", keypair.ecpubhex);
    axios.post('http://120.76.197.109:8282/api/pub/key/send/' + this.$store.getters.pubkeyHex).then(() => {
      console.log('pubkeyHex transform successfully!')
    })
    this.getSecret()
  },
  mounted() {
    if (this.loginForm.username === '') {
      this.$refs.username.focus()
    } else if (this.loginForm.password === '') {
      this.$refs.password.focus()
    }
  }
  ,
  destroyed() {
  }
  ,
  methods: {
    sm2encrypt(clearText) {
      return sm2(clearText, this.$store.getters.pubkeyHexOfBack, 1)
    },
    sm2decrypt(secretText) {
      return sm2Decrypt(secretText, this.$store.getters.prikeyHex, 1)
    },
    sm4encrypt(clearText) {
      var inputBytes = Hex.utf8StrToBytes(clearText)
      var key = Hex.decode(this.$store.getters.secretkey)
      var sm4 = new SM4()
      var cipher = sm4.encrypt_ecb(key, inputBytes)
      return Hex.encode(cipher, 0, cipher.length)
    },
    sm4decrypt(secretText) {
      var inputBytes = Hex.decode(secretText)
      var key = Hex.decode(this.$store.getters.secretkey)
      var sm4 = new SM4()
      var plain = sm4.decrypt_ecb(key, inputBytes)
      return Hex.bytesToUtf8Str(plain)
    },
    getSecret() {
      axios.get('http://120.76.197.109:8282/api/pub/key/get').then(response => {
        try {
          this.$store.commit('SET_PUBKEYHEXOFBACK', response.data.data[0])
          sessionStorage.setItem("pubkeyHexOfBack", response.data.data[0]);
          let base64_secretkey = window.atob(response.data.data[1])
          // console.log(base64_secretkey)
          let decrypt_secretkey = this.sm2decrypt(base64_secretkey);
          this.$store.commit('SET_SECRETKEY', decrypt_secretkey)
          sessionStorage.setItem("secretkey", decrypt_secretkey);
          console.log(this.$store.getters.secretkey)
        } catch (e) {
          this.getSecret()
        }
      })
    },
    checkCapslock(e) {
      const {key} = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    showPwd() {
      if (this.passwordType === 'password') {
        this.passwordType = ''
      } else {
        this.passwordType = 'password'
      }
      this.$nextTick(() => {
        this.$refs.password.focus()
      })
    },
    handleRefreshCode() {
      this.captchaUrl = 'http://120.76.197.109:8282/api/captcha.jpg?time=' + new Date()
      console.log('code refreshed.');
      // console.log('fake handler')
    },
    // 1. 登录按钮按下 进入函数
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        // 2. js 表单验证通过之后
        if (valid) {
          this.loading = true
          // 3. 向store提交 login表单信息 做进一步验证
          // 这里跳转到 @/store/user.js -> login()
          let loginString = JSON.stringify(this.loginForm);
          console.log(loginString);
          let loginSecret = this.sm2encrypt(loginString);
          this.$store.dispatch('user/login', loginSecret)
            .then(() => {
              // 8. 通过axios 从 @/src/store/user.js 跳转回来 成功验证账号密码 进入路由 '/'
              // 9. 路由有 beforeEach 钩子函数 位于 @/src/permission.js
              this.$router.push({path: this.redirect || '/', query: this.otherQuery})
              this.loading = false
              this.$notify({
                title: '登录成功',
                message: '欢迎回来!',
                type: 'success',
                duration: 5000
              })
            })
            .catch((error) => {
              this.$notify({
                title: '登录失败',
                message: error,
                type: 'error',
                duration: 5000
              })
              console.log('@/login/index.vue: handleLogin: Login Failed.')
              this.loading = false
            })
        } else {
          console.log('@/login/index.vue: error submit!!')
          return false
        }
      })
    },
    getOtherQuery(query) {
      return Object.keys(query).reduce((acc, cur) => {
        if (cur !== 'redirect') {
          acc[cur] = query[cur]
        }
        return acc
      }, {})
    },
    handleRegister() {
      this.$router.push({path: '/register'})
    }
  }
}
</script>

<style lang="scss">
/* 修复input 背景不协调 和光标变色 */
/* Detail see https://github.com/PanJiaChen/vue-element-admin/pull/927 */

$bg: #283443;
$light_gray: #fff;
$cursor: #fff;

@supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
  .login-container .el-input input {
    color: $cursor;
  }
}

/* reset element-ui css */
.login-container {
  .el-input {
    display: inline-block;
    height: 47px;
    width: 85%;

    input {
      background: transparent;
      border: 0px;
      -webkit-appearance: none;
      border-radius: 0px;
      padding: 12px 5px 12px 15px;
      color: $light_gray;
      height: 47px;
      caret-color: $cursor;

      &:-webkit-autofill {
        box-shadow: 0 0 0px 1000px $bg inset !important;
        -webkit-text-fill-color: $cursor !important;
      }
    }
  }

  .el-form-item {
    border: 1px solid rgba(255, 255, 255, 0.1);
    background: rgba(0, 0, 0, 0.1);
    border-radius: 5px;
    color: #454545;
  }
}
</style>

<style lang="scss" scoped>
$bg: #2d3a4b;
$dark_gray: #889aa4;
$light_gray: #eee;

.login-container {
  min-height: 100%;
  width: 100%;
  background-color: $bg;
  overflow: hidden;
  background: url("../../assets/bg2.jpg");

  .login-form {
    position: relative;
    width: 600px;
    max-width: 100%;
    //margin: 160px 35px 0;
    padding: 50px 80px;
    margin: 160px auto 0px;
    overflow: hidden;
    background-color: rgba(255, 255, 255, 0.4);
    border: white 2px solid;
    border-radius: 10px;
  }

  .tips {
    font-size: 14px;
    color: #fff;
    margin-bottom: 10px;

    span {
      &:first-of-type {
        margin-right: 16px;
      }
    }
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 30px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-size: 26px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
    }

    .set-language {
      color: #fff;
      position: absolute;
      top: 3px;
      font-size: 18px;
      right: 0px;
      cursor: pointer;
    }
  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }

  .thirdparty-button {
    position: absolute;
    right: 0;
    bottom: 6px;
  }

  @media only screen and (max-width: 470px) {
    .thirdparty-button {
      display: none;
    }
  }

  .show-captcha {
    position: absolute;
    right: 0;
  }
}
</style>
