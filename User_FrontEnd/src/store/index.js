import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)
export default new Vuex.Store({
  state: {
    toastText: '',
    loadingVisible: false
  },
  mutations: {
    increment (state) {
      state.count++
    },
    toast (state, text) {
      state.toastText = text
      setTimeout(() => {
        state.toastText = ''
      }, 2000)
    },
    loading (state, loadingVisible) {
      state.loadingVisible = loadingVisible
    },
    requireLogin (state) {
      const host = process.env.NODE_ENV === 'development' ? 'wxtest.zhichou.com' : 'wx.zhichou.com'
      location.assign('http://' + host + '/#/me/login?rurl=' + encodeURIComponent(location.href))
    }
  }
})
