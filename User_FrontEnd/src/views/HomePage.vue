<template>
  <page class="home-page">
    <zui-header>
      <logo type="xiaoyanyun" href="/"></logo>
      <h4 class="zui-logo-text">
        |&nbsp;&nbsp;海港云溯
        <br/>|&nbsp;&nbsp;联盟链商品溯源平台
      </h4>
      <div class="zui-rightcol" @click="openMenu" style="color: #0b8ba5;">◄目录►</div>
      <drop-list :config="configData" ref="droplist"></drop-list>
      <zui-button @click="onButtonClick('end')">后台入口</zui-button>
      <zui-button @click="onButtonClick('verify')">证书核验</zui-button>
      <zui-button @click="onButtonClick('data')">数据分析</zui-button>
      <zui-button @click="onButtonClick('home')">溯源首页</zui-button>
    </zui-header>
    <div class="banner vivify fadeIn">
      <!--      <h1 class="vivify popIn">-->
      <!--        <span class="br">溯造信任之帆</span>-->
      <!--      </h1>-->
      <!--      <h3 class="vivify popIn">文化为引领，物业为基础，服务为核心</h3>-->
      <el-input class="vivify popIn" placeholder="请输入溯源码" v-model="traceCode" style="width:50%;height: auto">
        <template slot="append">
          <el-button type="success" class="vivify popIn" @click="verifyTrace" :loading="loading2">
            马上追溯
          </el-button>
        </template>
      </el-input>
    </div>
    <el-dialog title="溯源信息一览" :visible.sync="dialogTableVisible" center append-to-body width="70%">
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
    <el-dialog :visible.sync="dialogTableVisible2" center append-to-body :width="verifyWidth + 50 + 'px'">
      <slide-verify :l="42" :r="10" :w="verifyWidth" :h="155" @success="dataTrace"
                    @fail="function() {this.refreshNumber = +new Date() }" slider-text="向右滑动进行验证"
                    :key="refreshNumber"></slide-verify>
    </el-dialog>
    <div class="nav clear">
      <a class="zhichou vivify popIn">
        <div class="watermark blue">真实</div>
        <logo type="zhongtai"></logo>
        <logo class="mobile" type="mzhichou"></logo>
        <h3>数据来源明确，真实性强</h3>
        <h4>做到问题可究，来源可查</h4>
      </a>
      <a class="hr vivify popIn delay-200">
        <div class="watermark green">安全</div>
        <logo type="hr"></logo>
        <logo class="mobile" type="mhr"></logo>
        <h3>区块链国密保障数据安全</h3>
        <h4>在各个方面保证数据不被篡改</h4>
      </a>
      <a class="plus vivify popIn delay-400">
        <div class="watermark blue">快捷</div>
        <logo type="plus"></logo>
        <logo class="mobile" type="mplus"></logo>
        <h3>系统性能高，溯源效率高</h3>
        <h4>IPFS+企业级系统架构，保证速度</h4>
      </a>
    </div>
    <div class="service">
      <h1>
        "溯"造信任之帆
      </h1>
      <h3>
        用我们的平台发掘信息溯源的价值
      </h3>
      <h4>
        兼之以大数据分析，打击套代购和二次销售的现象
      </h4>
    </div>
    <div class="features">
      <ul>
        <li>
          <img :src="feature1"/>
          <h4>SM2、SM3、SM4国密算法</h4>
          <p>加强了本平台的安全可控的能力，摆脱了对国外技术和产品的过度依赖，有效增强了平台的机密性、完整性、真实性、不可否认性。</p>
        </li>
        <li>
          <img :src="feature2"/>
          <h4>Hyperledger Fabric联盟链技术</h4>
          <p>通过成员机构节点共同维护区块链网络来实现去中心化，实现了数据可追踪溯源和防篡改，保证了追溯信息的真实有效。</p>
        </li>
        <li>
          <img :src="feature3"/>
          <h4>IPFS大规模上链方案</h4>
          <p>与Fabric联盟链相结合，可以很好地解决传统区块链数据存储容量小与读取效率低下的问题。</p>
        </li>
        <li>
          <img :src="feature4"/>
          <h4>哈希锁定、二阶段事务跨链技术</h4>
          <p>有效解决同构区块链或异构区块链之间的通信问题，满足实际场景中的数据共享需求，实现互通、互认、互联、互信、互访。</p>
        </li>
        <li>
          <img :src="feature5"/>
          <h4>"商品身份证"作为溯源凭证</h4>
          <p>采用数字签名和盲水印技术制作商品溯源信息结果的持久化凭证——“商品身份证”，作为溯源信息的载体并传至IPFS存储。</p>
        </li>
      </ul>
    </div>
    <!-- <div class="customer-case-logo">
       <img :src="companyLogo"/>
       <img class="mobile" :src="mcompanyLogo"/>
     </div> -->
    <div class="apply-hr">
      <h2>
        线上可溯源，线下可验码
      </h2>
      <h4>
        安全与效率兼顾，争做领先的区块链溯源提供者
      </h4>
    </div>
    <zui-footer/>
  </page>
</template>
<script>
import './less/home-page.less'
import Logo from '../components/Logo'
import Page from '../components/Page'
import ZuiHeader from '../components/Header'
import ZuiFooter from '../components/Footer'
import ZuiButton from '../components/Button'
import feature1 from '../assets/img/feature1.png'
import feature2 from '../assets/img/feature2.png'
import feature3 from '../assets/img/feature3.png'
import feature4 from '../assets/img/feature4.png'
import feature5 from '../assets/img/feature5.png'
import Util from '../Util'
import DropList from 'vue-droplist'
import {trace, getkey, certificate} from "../api/trace";
import {Hex} from "../utils/hex";
import {SM4} from "../utils/sm4-1.0";

export default {
  components: {Page, Logo, ZuiHeader, ZuiFooter, ZuiButton, DropList},
  data() {
    return {
      traceData: [],
      retailerInfo: {},
      dealerInfo: {},
      commodityInfo: {},
      materialInfo: [],
      dialogTableVisible: false,
      dialogTableVisible2: false,
      traceCode: '',
      feature1: feature1,
      feature2: feature2,
      feature3: feature3,
      feature4: feature4,
      feature5: feature5,
      carouselPerPage: (Util.os.android || Util.os.iPhone) ? 1 : 2,
      configData: {
        position: {  // 设置显示位置，position
          top: '60px',
          right: '15px',
          bottom: '',
          left: ''
        },
        width: '40%', // 设置宽度
        list: [ // 设置下拉列表数据和对应的点击事件
          {text: '溯源首页', action: this.gohome},
          {text: '数据分析', action: this.godata},
          {text: '后台入口', action: this.goend}
        ]
      },
      secretkey: '',
      goodsId: '',
      loading: false,
      loading2: false,
      refreshNumber: +new Date(),
      verifyWidth: 310
    }
  },
  methods: {
    sm4decrypt(secretText) {
      var inputBytes = Hex.decode(secretText)
      var key = Hex.decode(this.secretkey)
      var sm4 = new SM4()
      var plain = sm4.decrypt_ecb(key, inputBytes)
      return Hex.bytesToUtf8Str(plain)
    },
    onButtonClick: function (e) {
      if (e == "home") {
        this.$router.push({path: '/'})
      } else if (e == "data") {
        this.$router.push({path: '/data'})
      } else if (e == "end") {
        window.location.href = 'http://localhost:9527'
      } else if (e == "verify") {
        this.$router.push({path: '/verify'})
      } else {
        this.$router.push({path: '/'})
      }
    },
    openMenu: function () {
      this.$refs.droplist.show()
    },
    gohome: function () {
      this.$router.push({path: '/'})
    },
    godata: function () {
      this.$router.push({path: '/data'})
    },
    goend: function () {
      window.location.href = 'http://120.76.197.109:99'
    },
    dataTrace() {
      this.dialogTableVisible2 = false
      this.loading2 = true
      trace(this.traceCode).then(response => {
        if (response.code == 500) {
          this.$message({
            message: '您输入的溯源码未与商品关联！',
            type: 'warning'
          })
          this.loading2 = false
          return
        }
        this.traceData = []
        let traceInfo = JSON.parse(this.sm4decrypt(response.data))
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
        // console.log(this.retailerInfo)
        // console.log(this.dealerInfo)
        // console.log(this.commodityInfo)
        // console.log(this.materialInfo)
        // console.log(this.traceData)
      }).catch(e => {
        console.log(e)
        this.$message({
          message: '您输入的溯源码无效！',
          type: 'error'
        })
      })
      this.loading2 = false
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
    verifyTrace() {
      if (this.traceCode == '') {
        this.$message({
          message: '请输入溯源码！',
          type: 'warning'
        })
      } else {
        this.refreshNumber = +new Date()
        this.dialogTableVisible2 = true
      }
    }
  },
  created() {
    getkey().then(response => {
      this.secretkey = response.data
    })
    if (this.$route.params.traceCode != undefined) {
      console.log(this.$route.params.traceCode)
      this.traceCode = this.$route.params.traceCode
      this.dataTrace()
    }
  }
}
</script>

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
