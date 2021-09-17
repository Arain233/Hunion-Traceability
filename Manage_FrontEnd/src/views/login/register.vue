<template>
  <div id="login">
    <div class="con">
      <div class="user">
        <div class="header">普通用户注册</div>
        <div class="reg">
          <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px"
                   class="demo-ruleForm">
            <el-form :model="dynamicValidateForm" ref="user" label-width="100px" class="demo-dynamic"
                     hide-required-asterisk=false>
              <el-form-item
                class="pass"
                prop="value"
                label="用户名"
                :rules="[
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    ]"
              >
                <el-input v-model="dynamicValidateForm.value"></el-input>
              </el-form-item>
            </el-form>
            <el-form-item label="密码" prop="pass" class="pass">
              <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass" class="pass">
              <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
            </el-form-item>
            <el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic"
                     hide-required-asterisk=false>
              <el-form-item
                class="pass"
                prop="email"
                label="邮箱"
                :rules="[
                    { required: true, message: '请输入邮箱地址', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ]"
              >
                <el-input v-model="dynamicValidateForm.email"></el-input>
              </el-form-item>
              <el-form-item
                class="pass"
                prop="nickName"
                label="昵称"
                :rules="[
                    { required: true, message: '请输入昵称', trigger: 'blur' },
                    ]"
              >
                <el-input v-model="dynamicValidateForm.nickName"></el-input>
              </el-form-item>
            </el-form>
            <el-form-item style="text-align: center; margin-right: 36px">
              <el-button type="primary" @click="handleRegister">提 交</el-button>
              <el-button @click="resetForm('ruleForm','dynamicValidateForm','user')" class="reset">重 置</el-button>
              <el-button
                type="warning"
                plain
                @click.native.prevent="handleBack"
              >< 返回
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from "axios"
import {sm2} from "@/utils/sm2";
import {sm2Decrypt} from "@/utils/sm2 decrypt";
import {Hex} from "@/utils/hex";
import {SM4} from "@/utils/sm4-1.0";

export default {
  name: 'register',
  props: [''],
  data() {
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else if (value.length < 5) {
        callback(new Error('密码需要大于5位'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      ruleForm: {
        pass: '',
        checkPass: '',
        age: '',
      },
      rules: {
        pass: [
          {validator: validatePass, trigger: 'blur'}
        ],
        checkPass: [
          {validator: validatePass2, trigger: 'blur'}
        ],
      },
      dynamicValidateForm: {
        value: '',
        email: '',
        nickName: ''
      }
    };
  },

  components: {},

  computed: {},

  mounted() {
  },

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
    checkCapslock(e) {
      const {key} = e
      this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
    },
    handleBack() {
      this.$router.push({path: '/login'})
    },
    handleRegister() {
      var _this = this
      let regString = JSON.stringify({
        "email": this.dynamicValidateForm.email,
        "mobile": "string",
        "name": this.dynamicValidateForm.value,
        "nickName": this.dynamicValidateForm.nickName,
        "password": this.ruleForm.checkPass,
        "role_id": 1
      });
      console.log(regString);
      let regSecret = this.sm2encrypt(regString);
      axios({
        url: 'http://120.76.197.109:8282/api/save',
        method: 'post',
        // responseType: 'json', // 默认的
        data: {
          // "email": this.dynamicValidateForm.email,
          // "mobile": "string",
          // "name": this.dynamicValidateForm.value,
          // "nickName": this.dynamicValidateForm.nickName,
          // "password": this.ruleForm.checkPass,
          // "role_id": 1
          "json": regSecret
        }
      }).then(function (response) {
        console.log(response.data);
        _this.$notify({
          title: '注册成功',
          message: '请回到登录页面',
          type: 'success',
          duration: 5000
        })
        _this.$router.push({path: '/login'})
      }).catch(function (error) {
        console.log(error);
        this.$notify({
          title: '注册失败',
          message: error,
          type: 'error',
          duration: 5000
        })
      })
    },
    submitForm(formName, dynamicValidateForm, user) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.$refs.dynamicValidateForm.validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
      this.$refs.user.validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName, dynamicValidateForm, user) {
      this.$refs[formName].resetFields();
      this.$refs.dynamicValidateForm.resetFields();
      this.$refs.user.resetFields();
    }
  },
}
</script>
<style scoped>
#login {
  min-height: 100%;
  background: url("~@/assets/bg2.jpg");
  width: 100%;
  overflow: hidden;
}

.con {
  width: 50%;
  /*height: 500px;*/
  border: white 2px solid;
  border-radius: 10px;
  background-color: rgba(255, 255, 255, 0.5);
  margin: 8rem auto 0px;
  padding-right: 40px;
}

.header {
  color: white;
  font-size: 35px;
  font-weight: 700;
  padding: 10px 0 20px 100px;
  text-align: center;
  margin-right: 32px;
}
</style>
