<template>
  <el-card shadow='never' class="custom-header">
    <div slot="header" v-if='title'>
      <span>{{ title }}</span>
    </div>
    <slot name="content"/>
  </el-card>
</template>

<script>
export default {
  name: 'CommonCard',
  props: {
    title: String
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style>
.custom-header {
  min-height: 300px;
  border: 4px solid #065278;
  -moz-box-shadow: 0 -4px 9px 3px #065278 inset;
  /* 老的 Firefox */
  box-shadow: 0 -4px 9px 3px #065278 inset;
  background-color: rgba(255, 255, 255, 0);
  color: #eeeeee;
}

.custom-header .el-card__header {
  height: 36px;
  text-align: left;
  padding: 2px 20px;
  background-image: linear-gradient(90deg, #065278 0%, #032f4c 51%, rgba(0, 11, 31, 0) 100%);
  border-bottom: 0px solid #EBEEF5;
}

.custom-header .el-card__header span {
  color: #eeeeee;
  text-align: left;
  font-size: 21px;
}
</style>
