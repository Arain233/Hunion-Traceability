<template>
  <common-card title="异常情况监测">
    <template v-slot:content>
      <br/>
      <br/>
      <br/>
      <br/>
      根据数据分析暂无异常状况
    </template>
  </common-card>
</template>

<script>
// @ is an alias to /src
import CommonCard from './CommonCard.vue'

export default {
  name: 'GPU',
  components: {
    CommonCard
  }
}
</script>

<style scoped lang='scss'>
</style>
