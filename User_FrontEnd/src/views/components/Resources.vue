<template>
  <common-card title="各类数据情况">
    <template v-slot:content>
      <div style='text-align: left;'>
        <el-row style="margin-top:20px">
          <el-col :span="12">
            <div class="res-image">
              <img src="../../../static/images/image.png" />
            </div>
            <div class="res-desc">
              <span class='res-num'>33</span>
              <span class='res-unit'>次</span>
              <br />
              <span class='res-name'>今日浏览</span>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="res-image">
              <img src="../../../static/images/node.png" />
            </div>
            <div class="res-desc">
              <span class='res-num'>27</span>
              <span class='res-unit'>次</span>
              <br />
              <span class='res-name'>今日溯源</span>
            </div>
          </el-col>
        </el-row>
        <el-row style="margin-top:40px">
          <el-col :span="12">
            <div class="res-image">
              <img src="../../../static/images/storage.png" />
            </div>
            <div class="res-desc">
              <span class='res-num'>15</span>
              <span class='res-unit'>次</span>
              <br />
              <span class='res-name'>今日入库</span>
            </div>
          </el-col>
          <el-col :span="12">
            <div class="res-image">
              <img src="../../../static/images/gpu.png" />
            </div>
            <div class="res-desc">
              <span class='res-num'>2</span>
              <span class='res-unit'>条</span>
              <br />
              <span class='res-name'>区块链数</span>
            </div>
          </el-col>
        </el-row>
      </div>
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard.vue'

export default {
  name: 'Resources',
  components: {
    CommonCard
  }
}
</script>

<style scoped lang='scss'>
.res-image {
  margin-left: 50px;
  display: inline-block;
}
.res-desc {
  margin-left: 10px;
  display: inline-block;
  bottom: 12px;
  position: relative;

  .res-num {
    font-size: 24px;
  }

  .res-unit {
    font-size: 12px;
    color: #656970;
  }

  .res-name {
    font-size: 17px;
  }
}
</style>
