<template>
  <div id="app">
    <router-view v-if="isRouterAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: 'App',
  provide() {    //父组件中通过provide来提供变量，在子组件中通过inject来注入变量。
    return {
      reload: this.reload
    }
  },
  data() {
    return {
      isRouterAlive: true                    //控制视图是否显示的变量
    }
  },
  methods: {
    reload() {
      this.isRouterAlive = false;            //先关闭，
      this.$nextTick(function () {
        this.isRouterAlive = true;         //再打开
      })
    }
  },
  created() {
    //在页面加载时读取sessionStorage里的状态信息
    this.$store.commit('SET_PRIKEYHEX', sessionStorage.getItem("prikeyHex"))
    this.$store.commit('SET_PUBKEYHEX', sessionStorage.getItem("pubkeyHex"))
    this.$store.commit('SET_PUBKEYHEXOFBACK', sessionStorage.getItem("pubkeyHexOfBack"))
    this.$store.commit('SET_SECRETKEY', sessionStorage.getItem("secretkey"))
    // console.log(sessionStorage.getItem("prikeyHex"))
    // console.log(sessionStorage.getItem("pubkeyHex"))
    // console.log(sessionStorage.getItem("pubkeyHexOfBack"))
    // console.log(sessionStorage.getItem("secretkey"))
  }
}
</script>
