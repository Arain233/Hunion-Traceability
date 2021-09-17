<template>
  <div>
    <el-menu
      :default-active="this.$route.path"
      router
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#ffd04b">
      <el-menu-item index="/manipulation/enterInfo/one">原料商</el-menu-item>
      <el-menu-item index="/manipulation/enterInfo/two">生产方</el-menu-item>
      <el-menu-item index="/manipulation/enterInfo/three">经销商</el-menu-item>
      <el-menu-item index="/manipulation/enterInfo/four">零售商</el-menu-item>
    </el-menu>
    <router-view />
  </div>
</template>

<script>
/* eslint-disable */

import axios from "axios"

export default {
  name: "findByCode",
  methods: {
    handleSelect(key, keyPath) {
      console.log(key, keyPath);
    }
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
</style>
