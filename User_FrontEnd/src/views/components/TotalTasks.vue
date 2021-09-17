<template>
  <common-card>
    <template v-slot:content>
      <div>
        <img src="../../../static/images/subtitle.png" />
        <span class="task-title">系统总浏览量</span>
        <img src="../../../static/images/subtitle.png" />
      </div>
      <div style="margin: 30px 0; display: inline-block;">
        <template v-for="(num, index) in taskNum.split('')">
          <div class="task-num" :key="index"><span>{{num}}</span></div>
        </template>
      </div>
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard.vue'

export default {
  name: 'TotalTasks',
  components: {
    CommonCard
  },
  data() {
    return {
      taskNum: '25345'
    }
  }
}
</script>

<style scoped lang='scss'>
.task-title {
  color: #00ccff;
  font-size: 27px;
  margin: auto 10px;
}
.task-num {
  width: 77px;
  height: 123px;
  display: inline-block;
  margin: 1px;
  background-color: rgba(74, 157, 242, 0.24);
  border: solid 2px rgba(74, 157, 242, 0.24);

  span {
    height: 123px;
    font-size: 67px;
    line-height: 123px;
    vertical-align: middle;
  }
}
</style>
