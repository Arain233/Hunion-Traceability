<template>
  <div class="login-container">
    <el-form
      ref="loginForm"
      :model="loginForm"
      :rules="loginRules"
      class="login-form"
      auto-complete="on"
      label-position="left"
    >
      <div class="title-container">
        <h3 class="title">学位学历认证管理系统</h3>
      </div>

      <el-form-item prop="name">
        <span class="svg-container">
          <svg-icon icon-class="user"/>
        </span>
        <el-input
          ref="name"
          v-model="loginForm.name"
          placeholder="账户"
          name="name"
          type="text"
          tabindex="1"
          auto-complete="on"
        />
      </el-form-item>

      <el-form-item prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password"/>
        </span>
        <el-input
          :key="passwordType"
          ref="password"
          v-model="loginForm.password"
          :type="passwordType"
          placeholder="密码"
          name="password"
          tabindex="2"
          auto-complete="on"
        />
        <span class="show-pwd" @click="showPwd">
          <svg-icon
            :icon-class="passwordType === 'password' ? 'eye' : 'eye-open'"
          />
        </span>
      </el-form-item>

      <el-form-item prop="captcha">
        <span class="svg-container">
          <i class="el-icon-warning"/>
        </span>
        <el-input
          placeholder="请输入验证码"
          tabindex="3"
          v-model="loginForm.captcha"
          name="captcha"
          ref="captcha"
          @keyup.enter.native="handleLogin"
        />
        <span class="show-captcha">
          <img :src="captchaUrl" alt="登录验证码" title="看不清？点击切换">
        </span>
      </el-form-item>

      <el-button
        :loading="loading"
        type="primary"
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handleLogin"
      >登录
      </el-button>
      <el-row></el-row>
      <el-button
        style="width:100%;margin-bottom:30px;"
        @click.native.prevent="handlePush"
        plain
      >没有账号？点此注册！
      </el-button>

    </el-form>
  </div>
</template>

<script>
import axios from "axios"

export default {
  name: 'Login',
  data() {
    const validatePassword = (rule, value, callback) => {
      if (value.length < 5) {
        callback(new Error('密码不能少于5位！'))
      } else {
        callback()
      }
    }
    return {
      loginForm: {
        name: '',
        password: '',
        captcha: ''
      },
      loginRules: {
        name: [
          { required: true, message: '请输入姓名！', trigger: 'blur' }
        ],
        password: [
          { required: true, trigger: 'blur', validator: validatePassword }
        ],
        captcha: [
          { required: true, message: '请输入验证码！', trigger: 'blur' }
        ]
      },
      loading: false,
      passwordType: 'password',
      redirect: undefined,
      captchaUrl: ''
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  created() {
    this.captchaUrl = 'http://125.217.102.101:8202/api/captcha.jpg'
  },
  // methods: {
  //   showPwd() {
  //     if (this.passwordType === 'password') {
  //       this.passwordType = ''
  //     } else {
  //       this.passwordType = 'password'
  //     }
  //     this.$nextTick(() => {
  //       this.$refs.password.focus()
  //     })
  //   },
  //   handleLogin() {
  //     this.$refs.loginForm.validate(valid => {
  //       if (valid) {
  //         this.loading = true
  //         console.log(this.loginForm)
  //         this.$store
  //           .dispatch('user/login', this.loginForm)
  //           .then(() => {
  //             this.$router.push({ path: this.redirect || '/' })
  //             this.loading = false
  //           })
  //           .catch(() => {
  //             this.loading = false
  //           })
  //       } else {
  //         console.log('error submit!!')
  //         return false
  //       }
  //     })
  //   },
  //   handlePush() {
  //     this.$router.push({ path: '/register' })
  //   }
  // }
  methods: {
    handlePush() {
      this.$router.push({ path: '/register' })
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
    // 1. 登录按钮按下 进入函数
    handleLogin() {
      this.$refs.loginForm.validate(valid => {
        // 2. js 表单验证通过之后
        if (valid) {
          this.loading = true
          // 3. 向store提交 login表单信息 做进一步验证
          // 这里跳转到 @/store/user.js -> login()
          this.$store.dispatch('user/login', this.loginForm)
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
              console.log('handleLogin: Login Failed.')
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
    }
    // afterQRScan() {
    //   if (e.key === 'x-admin-oauth-code') {
    //     const code = getQueryObject(e.newValue)
    //     const codeMap = {
    //       wechat: 'code',
    //       tencent: 'code'
    //     }
    //     const type = codeMap[this.auth_type]
    //     const codeName = code[type]
    //     if (codeName) {
    //       this.$store.dispatch('LoginByThirdparty', codeName).then(() => {
    //         this.$router.push({ path: this.redirect || '/' })
    //       })
    //     } else {
    //       alert('第三方登录失败')
    //     }
    //   }
    // }
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
    width: 90%;

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
  background-image: url("~@/assets/bg2.jpg");
  background-size: cover;
  overflow: hidden;

  .login-form {
    position: relative;
    width: 700px;
    max-width: 100%;
    //margin: 160px 35px 0;
    padding: 20px 100px;
    margin: 160px auto 0px;
    overflow: hidden;
    background-color: rgba(255,255,255,0.4);
    border: white 2px solid;
    border-radius: 10px;
  }

  .svg-container {
    padding: 6px 5px 6px 15px;
    color: $dark_gray;
    vertical-align: middle;
    width: 32px;
    display: inline-block;
  }

  .title-container {
    position: relative;

    .title {
      font-family: "\534E\6587\884C\6977";
      font-size: 43px;
      color: $light_gray;
      margin: 0px auto 40px auto;
      text-align: center;
      font-weight: bold;
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

  .show-captcha {
    position: absolute;
    right: 0px;
    top: 1px;
    font-size: 18px;
    color: $dark_gray;
    cursor: pointer;
    user-select: none;
  }
}
</style>
