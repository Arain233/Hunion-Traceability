<template>
  <div class="createPost-container">
    <el-form ref="postForm" :model="postForm" :rules="rules" class="form-container">
      <sticky :z-index="10" :class-name="'sub-navbar ' + postForm.status">
        <el-button v-loading="loading" style="margin-left: 10px;" type="success" @click="submitForm">
          录入
        </el-button>
      </sticky>

      <el-steps :active="1" align-center class="showProcess">
        <el-step title="原料商" icon="el-icon-edit" description="正在录入信息..."></el-step>
        <el-step title="生产方" description="未录入"></el-step>
        <el-step title="经销商" description="未录入"></el-step>
        <el-step title="零售商" description="未录入"></el-step>
      </el-steps>

      <div class="createPost-main-container">
        <el-row>
          <el-col :span="24">
            <el-form-item style="margin-bottom: 10px;" prop="address">
              <MDinput v-model="postForm.Address" :maxlength="100" name="address" required>
                生产商地址
              </MDinput>
            </el-form-item>

            <el-form-item style="margin-bottom: 10px;" prop="name">
              <MDinput v-model="postForm.Name" :maxlength="100" name="name" required>
                原料名称
              </MDinput>
            </el-form-item>

            <el-form-item style="margin-bottom: 10px;" prop="mfd">
              <MDinput v-model="postForm.MFD" :maxlength="100" name="mfd" required>
                原料生产日期
              </MDinput>
            </el-form-item>

            <el-form-item style="margin-bottom: 10px;" prop="enterpCode">
              <MDinput v-model="postForm.EnterpCode" :maxlength="100" name="enterpCode" required>
                原料商代码
              </MDinput>
            </el-form-item>

            <el-form-item style="margin-bottom: 10px;" prop="commodity_code">
              <MDinput v-model="postForm.commodity_code" :maxlength="100" name="commodity_code" required>
                生产代码
              </MDinput>
            </el-form-item>

            <el-form-item style="margin-bottom: 10px;" prop="originPlace">
              <MDinput v-model="postForm.OriginPlace" :maxlength="100" name="originPlace" required>
                原料产地
              </MDinput>
            </el-form-item>
          </el-col>
        </el-row>
      </div>
    </el-form>
  </div>
</template>

<script>
import MDinput from '@/components/MDinput'
import Sticky from '@/components/Sticky' // 粘性header组件
import Warning from './components/Warning'
import axios from "axios"
import {sm2} from "@/utils/sm2";
import {sm2Decrypt} from "@/utils/sm2 decrypt";
import {Hex} from "@/utils/hex";
import {SM4} from "@/utils/sm4-1.0";

const defaultForm = {
  status: 'draft', // 提交状态(draft/published) 后续开发使用
  Address: '',
  Name: '',
  MFD: '',
  EnterpCode: '',
  commodity_code: '',
  OriginPlace: ''
}

export default {
  name: 'ArticleDetail',
  components: {MDinput, Sticky, Warning},

  data() {
    const validateRequire = (rule, value, callback) => {
      if (value === '' || value === ' ') {
        this.$message({
          message: '请检查' + rule.name,
          type: 'error'
        })
        callback(new Error('请检查' + rule.name))
      } else {
        callback()
      }
    }
    return {
      postForm: Object.assign({}, defaultForm),
      loading: false,
      userListOptions: [],
      status: false,
      rules: {
        Address: [{validator: validateRequire, name: "生产商地址"}],
        Name: [{validator: validateRequire, name: "原料名称"}],
        MFD: [{validator: validateRequire, name: "原料生产日期"}],
        EnterpCode: [{validator: validateRequire, name: "原料商代码"}],
        commodity_code: [{validator: validateRequire, name: "生产代码"}],
        OriginPlace: [{validator: validateRequire, name: "原产地"}]
      },
      tempRoute: {}
    }
  },
  computed: {
    contentShortLength() {
      return this.postForm.content_short.length
    },
    lang() {
      return this.$store.getters.language
    },
    displayTime: {
      // set and get is useful when the data
      // returned by the back end api is different from the front end
      // back end return => "2013-06-25 06:59:25"
      // front end need timestamp => 1372114765000
      get() {
        return (+new Date(this.postForm.display_time))
      },
      set(val) {
        this.postForm.display_time = new Date(val)
      }
    }
  },
  created() {
    // Why need to make a copy of this.$route here?
    // Because if you enter this page and quickly switch tag, may be in the execution of the setTagsViewTitle function, this.$route is no longer pointing to the current page
    this.tempRoute = Object.assign({}, this.$route)
  },
  methods: {
    submitForm() {
      var _this = this
      this.$refs.postForm.validate(valid => {
        if (valid) {
          this.loading = true
          // console.log(this.postForm)
          let postString = JSON.stringify(this.postForm);
          // console.log(postString);
          // console.log(this.$store.getters.secretkey)
          let postSecret = this.sm4encrypt(postString);
          // console.log('postSecret: ' + postSecret)
          axios({
            method: 'post',
            url: 'http://120.76.197.109:8282/api/material/save',
            data: {
              // "address": this.postForm.address,
              // "name": this.postForm.name,
              // "mfd": this.postForm.mfd,
              // "enterpCode": this.postForm.enterpCode,
              // "commodity_code": this.postForm.commodity_code,
              // "originPlace": this.postForm.originPlace
              "json": postSecret
            },
            headers: {
              "token": this.$store.state.user.token
            }
          }).then(function (res) {
            console.log('submitForm Success: ');
            console.log(res.data);
            // _this.loading = true;
            if (res.data.code == 500) {
              _this.$notify({
                title: '失败',
                message: res.data.msg,
                type: 'error',
                duration: 10000
              }),
              _this.loading = false
            } else {
              _this.$notify({
                title: '成功',
                message: '供应链信息注册成功',
                type: 'success',
                duration: 10000
              }),
              _this.postForm.status = 'published'
              _this.loading = false
            }}).catch(function (err) {
            // axios error
            var msg = ''
            if (err.response.status === 403) {
              msg = '服务器拒绝入库请求 请重新登陆'
            } else if (err.response.status === 500) {
              msg = '服务器内部错误 请检查上链信息或联系管理员'
            } else {
              msg = err
            }
            _this.loading = false
            _this.postForm.status = 'draft'
            _this.$notify({
              title: '失败',
              message: '服务器拒绝了您的入库请求 原因: ' + msg,
              type: 'error',
              duration: 5000
            })
            console.log('submitForm Error: ')
            console.log(err)
          })
        }
      })
    },
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
    }
  }
}
</script>

<style lang="scss" scoped>
@import "~@/styles/mixin.scss";

.createPost-container {
  position: relative;

  .createPost-main-container {
    padding: 40px 45px 20px 50px;

    .postInfo-container {
      position: relative;
      @include clearfix;
      margin-bottom: 10px;

      .postInfo-container-item {
        float: left;
      }
    }
  }

  .word-counter {
    width: 40px;
    position: absolute;
    right: 10px;
    top: 0px;
  }
}

.article-textarea ::v-deep {
  textarea {
    padding-right: 40px;
    resize: none;
    border: none;
    border-radius: 0px;
    border-bottom: 1px solid #bfcbd9;
  }
}

.showProcess {
  margin-top: 20px;
}
</style>
