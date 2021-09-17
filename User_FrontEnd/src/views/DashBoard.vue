<template>
  <div class="body-bg">
    <i class="el-icon-back tstart-toolbar-top" @click="backHome"></i>
    <div class="home">
      <div class="home-title">
        <img src="../../static/images/toptitle.png" style="margin: 0 auto;width: 100%"/>
      </div>
      <el-row :gutter='8' class="row-margin">
        <el-col :span='6'>
          <resources/>
        </el-col>
        <el-col :span='12'>
          <total-tasks/>
        </el-col>
        <el-col :span='6'>
          <users/>
        </el-col>
      </el-row>
      <el-row :gutter='8' class="row-margin">
        <el-col :span='6'>
          <node-resources/>
        </el-col>
        <el-col :span='12'>
          <history-tasks/>
        </el-col>
        <el-col :span='6'>
          <framework/>
        </el-col>
      </el-row>
      <el-row :gutter='8' class="row-margin">
        <el-col :span='5'>
          <GPU/>
        </el-col>
        <el-col :span='5'>
          <memory/>
        </el-col>
        <el-col :span='5'>
          <storage/>
        </el-col>
        <el-col :span='9'>
          <computer-resources/>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import Resources from './components/Resources.vue'
import TotalTasks from './components/TotalTasks.vue'
import Users from './components/Users.vue'
import NodeResources from './components/NodeResources.vue'
import HistoryTasks from './components/HistoryTasks.vue'
import Framework from './components/Framework.vue'
import GPU from './components/GPU.vue'
import Memory from './components/Memory.vue'
import Storage from './components/Storage.vue'
import ComputerResources from './components/ComputerResources.vue'

export default {
  name: 'DashBoard',
  components: {
    Resources, TotalTasks, Users, NodeResources, HistoryTasks, Framework, GPU, Memory, Storage, ComputerResources
  },
  methods: {
    backHome() {
      this.$router.push({path: '/'})
    }
  },
  beforeRouteEnter(to, from, next) {
    // 添加背景色 margin:0;padding:0是为了解决vue四周有白边的问题
    document.querySelector('body').setAttribute('style', 'margin:0;padding:0;background-color: #000b1f;text-align: center;height: 100%;width: 100%;overflow: hidden')
    next()
  },
  beforeRouteLeave(to, from, next) {
    // 去除背景色
    document.querySelector('body').setAttribute('style', '')
    next()
  }
}
</script>

<style scoped lang='scss'>
.body-bg {
  height: 100%;
  width: 100%;
  text-align: center;
  background-color: #000b1f;
  padding: 0;
  margin: 0;
}

.home {
  margin: 10px;

  .row-margin {
    margin: 10px 20px;
  }
}

.tstart-toolbar-top {
  font-size: 40px;
  color: #ffffff;
  top: 0; /*最上*/
  position: absolute;
  left: 0;
  z-index: 10000;
  _position: absolute; /* for IE6 */
  _top: expression(documentElement.scrollTop + documentElement.clientHeight-this.offsetHeight); /* for IE6 */
  overflow: visible;
  cursor: pointer;
}
</style>
