<template>
  <div>
    <div class="center">唯一溯源码溯源</div>
    <div class="form">
      <el-form ref="form" :model="form">
        <el-form-item>
          <el-input v-model="form.code"></el-input>
        </el-form-item>
        <el-row type="flex" justify="space-around">
          <el-col :span="6">
            <el-form-item>
              <el-button type="primary" @click="onSubmit" :loading="ConfirmLoading">开始溯源</el-button>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-dialog title="溯源信息一览" :visible.sync="dialogTableVisible" center append-to-body width="50%">
      <el-table
        :data="traceData"
        style="width: 100%">
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline class="demo-table-expand">
              <el-form-item v-show="props.row.Name != null" label="产品名称">
                <span>{{ props.row.Name }}</span>
              </el-form-item>
              <el-form-item v-show="props.row.MFD != null" label="入库日期">
                <span>{{ props.row.MFD }}</span>
              </el-form-item>
              <el-form-item v-show="props.row.OriginPlace != null" label="原产地">
                <span>{{ props.row.OriginPlace }}</span>
              </el-form-item>
              <el-form-item v-show="props.row.Address != null" label="企业地址">
                <span>{{ props.row.Address }}</span>
              </el-form-item>
              <el-form-item v-show="props.row.EnterpCode != null" label="企业代码">
                <span>{{ props.row.EnterpCode }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          label="序号"
          type="index">
        </el-table-column>
        <el-table-column
          label="产品名称"
          prop="Name">
        </el-table-column>
        <el-table-column
          label="所处环节"
          prop="factoryType">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="certGenerate" :loading="loading">下载商品身份证</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
/* eslint-disable */

import axios from "axios"
import {Hex} from "@/utils/hex";
import {SM4} from "@/utils/sm4-1.0";

export default {
  name: "findByCode",
  data() {
    return {
      traceData: [],
      retailerInfo: {},
      dealerInfo: {},
      commodityInfo: {},
      materialInfo: [],
      dialogTableVisible: false,
      msgInfo: ['数据格式检查通过', '区块链SDK调用成功', '区块链网络成功调用'],
      ConfirmLoading: false,
      loading: false,
      form: {
        code: '',
        id: 1,
        amount: 0,
        name: '',
        category: '',
        farmer_id: '',
        producer_id: '',
        purch_time: '',
        in_time: '',
        out_time: null
      }
    }
  },
  methods: {
    sm4decrypt(secretText) {
      var inputBytes = Hex.decode(secretText)
      var key = Hex.decode(this.$store.getters.secretkey)
      var sm4 = new SM4()
      var plain = sm4.decrypt_ecb(key, inputBytes)
      return Hex.bytesToUtf8Str(plain)
    },
    onSubmit() {
      if (this.form.code.indexOf(" ") !== -1) {
        this.$message({
          type: 'error',
          message: '请勿输入空格'
        })
      } else if (this.form.code == '' || this.form.code == null || isNaN(this.form.code)) {
        this.$message({
          type: 'error',
          message: '请输入数值'
        })
      } else {
        console.log('Tracing submit with code: ' + this.form.code)
        axios.get('http://120.76.197.109:8282/api/pub/findByTra_code/' + this.form.code).then(response => {
          // console.log(response.data.data)
          // console.log(this.$store.getters.secretkey)
          this.ConfirmLoading = true
          this.$message({
            message: '区块链SDK调用成功',
            type: 'success',
            duration: 6000
          })
          this.traceData = []
          let traceInfo = JSON.parse(this.sm4decrypt(response.data.data))
          console.log(traceInfo)
          // 显示dialog
          this.dialogTableVisible = true
          // 用于获取商品身份证
          this.goodsId = traceInfo.id
          console.log(this.goodsId)
          // 分离这堆浑浊的json信息 并添加标识
          this.retailerInfo = traceInfo
          this.retailerInfo['factoryType'] = '零售商'
          this.dealerInfo = traceInfo.DealerInfo
          this.dealerInfo['factoryType'] = '经销商'
          this.commodityInfo = traceInfo.DealerInfo.CommodityInfo
          this.commodityInfo['factoryType'] = '生产商'
          this.materialInfo = traceInfo.DealerInfo.CommodityInfo.MaterialInfo
          // 将信息重组进traceData
          for (let i = 0; i < this.materialInfo.length; i++) {
            this.materialInfo[i]['factoryType'] = '原料商'
            this.traceData.push(this.materialInfo[i])
          }
          this.traceData.push(this.commodityInfo)
          this.traceData.push(this.dealerInfo)
          this.traceData.push(this.retailerInfo)
          this.ConfirmLoading = false
        })
      }
    },
    dataURLtoBlob(dataurl) {
      const bstr = atob(dataurl)
      let n = bstr.length
      const u8arr = new Uint8Array(n)
      while (n--) {
        u8arr[n] = bstr.charCodeAt(n)
      }
      return new Blob([u8arr], {type: 'pdf'})
    },
    certGenerate() {
      let id = this.goodsId
      this.loading = true
      certificate(this.goodsId).then(response => {
        let URL = this.dataURLtoBlob(response.data)
        // window.open(window.URL.createObjectURL(URL));
        var reader = new FileReader()
        reader.readAsDataURL(URL)
        reader.onload = function (e) {
          // 兼容IE
          if (window.navigator.msSaveOrOpenBlob) {
            var bstr = atob(e.target.result.split(',')[1])
            var n = bstr.length
            var u8arr = new Uint8Array(n)
            while (n--) {
              u8arr[n] = bstr.charCodeAt(n)
            }
            var blob = new Blob([u8arr])
            window.navigator.msSaveOrOpenBlob(blob, '可信商品身份证' + id + '.pdf')
          } else {
            // 转换完成，创建一个a标签用于下载
            let link = document.createElement('a')
            link.download = '可信商品身份证' + id + '.pdf'
            link.href = e.target.result
            link.click()
            link.remove()
          }
        }
        this.loading = false
      })
    },
  }
}
</script>

<style scoped>
.form {
  margin: 100px auto;
  width: 50%;
}

.center {
  font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB", "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
  font-size: 30px;
  text-align: center;
  color: #484848;
  margin-top: 50px;
}

.el-row--flex {
  text-align: center !important;
}

.el-message-box {
  width: 700px !important;
  height: 800px !important;
}

.hr-divide {
  width: 100%;
  height: 6px;
  border-radius: 3px;
  background-color: rgb(222 156 76);
  margin-bottom: 10px;
}
</style>

<style>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
