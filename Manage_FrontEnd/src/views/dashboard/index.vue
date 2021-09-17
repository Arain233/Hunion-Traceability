<template>
  <div>
    <el-calendar :range="['2021-07-26', '2021-08-01']">
    </el-calendar>
    <div class="container">
      <CardShow data-image="https://i0.hippopx.com/photos/829/724/891/divorce-separation-marriage-breakup-preview.jpg">
        <h1 slot="header">项目背景</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目背景 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/904/479/690/android-asus-phone-mobile-smart-phone-preview.jpg"
      >
        <h1 slot="header">项目功能</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目功能 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/400/716/42/cyber-security-online-computer-cyber-preview.jpg"
      >
        <h1 slot="header">项目创新</h1>
        <p slot="content" style="font-size: 12px">{{ items.项目创新 }}</p>
      </CardShow>
      <CardShow
        data-image="https://i0.hippopx.com/photos/87/549/114/building-corporate-building-design-architecture-preview.jpg"
      >
        <h1 slot="header" style="margin-bottom: 44px">项目前景</h1>
        <p slot="content" style="font-size: 14px">{{ items.项目前景 }}</p>
      </CardShow>
    </div>
    <!--    <el-carousel :interval="4000" type="card" height="450px">-->
    <!--      <el-carousel-item v-for="(item, index) in items" :key="index">-->
    <!--        <h1>{{ index }}</h1>-->
    <!--        <h3 class="medium">{{ item }}</h3>-->
    <!--      </el-carousel-item>-->
    <!--    </el-carousel>-->
  </div>
</template>

<script>
import CardShow from './components/card'
import {sm2} from '@/utils/sm2'
import {sm2Decrypt} from "@/utils/sm2 decrypt"
import {SM4} from "@/utils/sm4-1.0";
import {Hex} from "@/utils/hex";

export default {

  name: 'Dashboard',
  components: {CardShow},
  data() {
    return {
      username: '',
      items: {
        项目背景: '海南省地处我国最南端，是我国唯一的热带岛屿省份，光温充足、物产资源丰富，是发展热带特色高效农业的黄金宝地。伴随《海南自由贸易港建设总体方案》的提出，以及海南自贸港各项政策的扶持，海南省的特产、商品贸易日渐繁荣，但与此同时，也产生了诸多的问题：产品的真伪及流向难以确认、目前追溯的方法不符合简明易操作的原则、当供应环节出现问题时不能进行有效的责任追溯、各区域各地市的溯源供应链信息相对闭塞等，在监管部门依照“十四五规划”加强农产品、商品等特色产品的行业管理同时，仍需要一些信息化手段来遏制各种问题的发生。',
        项目功能: '在大规模、大体量的商品交易袭来的当下，如何保证各类商品的安全、质量与真实，刻不容缓，而海港云溯则为此提供了解决方案。海港云溯能够确保商品贸易正常有序的进行，协助相关部门进行商品监管与监督，保护消费者的相关利益，能够有序推进海南自由贸易港经济发展与贸易往来的繁荣，推动跨境贸易自由便利。 平台主要包括身份认证、区块链管理、信息溯源、凭证生成、服务监控、数据展示六大模块。',
        项目创新: '①采用国密算法，实现了密码机制自主可控，有利于行业网络安全环境的建设，有效增强了平台的机密性、完整性、真实性、不可否认性。 ②采用Hyperledger Fabric联盟链，实现了数据可追踪溯源和防篡改 ③使用IPFS + Fabric，提高了上链效率 ④采用跨链技术，实现了跨域数据共享，打破了不同区块链间的信任孤岛，实现了不同区块链平台之间的互通、互认、互联、互信以及互访。',
        项目前景: '链聚智联团队所实现的基于区块链的学历学位认证系统，界面简洁，功能齐全，运行稳定，能够满足时代对学历证书存储安全性的要求，能够满足市场需求，将区块链、国密算法、IPFS等技术结合在一起，文件地址与证书哈希值上链，链上所有节点参与验证，最大程度杜绝造假，提高了安全与隐私保护的能力，为学历造假的解决带来一种新的途径。'
      }
    }
  },
  created() {
  },
  methods: {
  }
}
</script>

<style lang="scss" scoped>
p {
  text-align: center;
  font-family: "Helvetica Neue";
  font-size: 22px;
  font-weight: bolder;
}

.el-carousel__item h3 {
  color: #000000;
  font-size: 14px;
  opacity: 0.9;
  line-height: 40px;
  margin: 10px;
}

h1 {
  text-align: center;
}

hr {
  color: #d3dce6;
  opacity: 80%;
}

//：nth-child的下标是从1开始的
.el-carousel__item:nth-child(3) {
  background-color: #ec7272;
}

.el-carousel__item:nth-child(4) {
  background-color: #FCE38A;
}

.el-carousel__item:nth-child(5) {
  background-color: #94c2f0;
}

.el-carousel__item:nth-child(6) {
  background-color: #7dc667;
}

</style>
