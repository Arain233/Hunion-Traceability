<template>
 <img alt="xiaoyanyun" class="zui-logo" :src="src" @click="onClick"/>
</template>
<script>
  import './less/logo.less'
  import logo from './img/logo.png'
  import zlogo from './img/zlogo.png'
  import hrLogo from './img/hr-logo.png'
  import plusLogo from './img/plus-logo.png'
  import mlogo from './img/m-logo.png'
  import mhrLogo from './img/m-hr-logo.png'
  import mplusLogo from './img/m-plus-logo.png'
  export default {
    name: 'Logo',
    props: {
      type: {
        default: 'xiaoyanyun'
      },
      href: {
        default: '/'
      }
    },
    computed: {
      src: function () {
        if (this.type === 'xiaoyanyun') {
          return logo
        }
        if (this.type === 'zhongtai') {
          return zlogo
        }
        if (this.type === 'mzhichou') {
          return mlogo
        }
        if (this.type === 'hr') {
          return hrLogo
        }
        if (this.type === 'mhr') {
          return mhrLogo
        }
        if (this.type === 'plus') {
          return plusLogo
        }
        if (this.type === 'mplus') {
          return mplusLogo
        }
      }
    },
    methods: {
      onClick: function (e) {
        if(this.type==="xiaoyanyun"){
           location.assign(this.href)
        }
      }
    }
  }
</script>
