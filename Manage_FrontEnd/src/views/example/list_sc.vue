<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        fixed
        prop="Name"
        label="原料名称"
        width="200">
      </el-table-column>
      <el-table-column
        prop="Address"
        label="生产商地址"
        width="300">
      </el-table-column>
      <el-table-column
        prop="EnterpCode"
        label="生产代码"
        width="200">
      </el-table-column>
      <el-table-column
        prop="MFD"
        label="生产日期"
        width="200">
      </el-table-column>
      <el-table-column
        prop="OriginPlace"
        label="产地">
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
/* eslint-disable */
import axios from 'axios'
import {sm2} from "@/utils/sm2";
import {sm2Decrypt} from "@/utils/sm2 decrypt";
import {Hex} from "@/utils/hex";
import {SM4} from "@/utils/sm4-1.0";

export default {
  name: 'shengchan',
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
    handleClick(row) {
      console.log(row);
    }
  },

  data() {
    return {
      tableData: null
    }
  },

  created() {
    var _this = this
    // console.log(this.$store.state.user.token) // for debug
    axios.get('http://120.76.197.109:8282/api/commodity/findAll', {
      headers: {
        token: this.$store.state.user.token
      }
    }).then((response) => {
      console.log(response.data.data)
      // decrypt part
      let tableDataJson = this.sm4decrypt(response.data.data)
      console.log(JSON.parse(tableDataJson))
      this.tableData = JSON.parse(tableDataJson)
    }).catch(err => {
      _this.$notify({
        title: '网络错误',
        message: err,
        type: 'error',
        duration: 10000
      })
    })
  }
}

</script>

<style scoped>

</style>
