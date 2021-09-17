import store from './store'
import router from './router'
import App from './App'
import 'echarts'
import VueECharts from 'vue-echarts'
import SlideVerify from 'vue-monoplasty-slide-verify'

const Vue = require('vue')
const ElementUI = require('element-ui')

Vue.component('v-chart', VueECharts)
Vue.use(SlideVerify)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
