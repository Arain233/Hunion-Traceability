<template>
  <!-- <img alt="zhichou" class="zui-logo" :src="src" @click="onClick"/> -->
   <img alt="zhichou" class="zui-logo" :src="src" />
</template>
<script>
  import './less/logo.less'
  import case1 from './img/case1.png'
  import case2 from './img/case2.png'
  import case3 from './img/case3.png'
  import case4 from './img/case4.png'
  import case5 from './img/case5.png'
  import case6 from './img/case6.png'
  import case7 from './img/case7.png'
  import case8 from './img/case8.png'
  import case9 from './img/case9.png'
  import case10 from './img/case10.png'
  import case11 from './img/case11.png'
  import case12 from './img/case12.png'
  import case13 from './img/case13.png'
  import case14 from './img/case14.png'
  import case15 from './img/case15.png'

  import mcase1 from './img/mcase1.png'
  import mcase2 from './img/mcase2.png'
  import mcase3 from './img/mcase3.png'
  import mcase4 from './img/mcase4.png'
  import mcase5 from './img/mcase5.png'
  import mcase6 from './img/mcase6.png'
  import mcase7 from './img/mcase7.png'
  import mcase8 from './img/mcase8.png'
  import mcase9 from './img/mcase9.png'
  import mcase10 from './img/mcase10.png'
  import mcase11 from './img/mcase11.png'
  import mcase12 from './img/mcase12.png'
  import mcase13 from './img/mcase13.png'
  import mcase14 from './img/mcase14.png'
  import mcase15 from './img/mcase15.png'

  export default {
    name: 'CaseLogo',
    props: {
      type: {
        default: 'case1'
      },
      href: {
        default: '/'
      }
    },
    computed: {
      src: function () {
        if (this.type === 'case1') {
          return case1
        }
        if (this.type === 'case2') {
          return case2
        }
        if (this.type === 'case3') {
          return case3
        }
        if (this.type === 'case4') {
          return case4
        }
        if (this.type === 'case5') {
          return case5
        }
        if (this.type === 'case6') {
          return case6
        }
        if (this.type === 'case7') {
          return case7
        }
        if (this.type === 'case8') {
          return case8
        }
        if (this.type === 'case9') {
          return case9
        }
        if (this.type === 'case10') {
          return case10
        }
        if (this.type === 'case11') {
          return case11
        }
        if (this.type === 'case12') {
          return case12
        }
        if (this.type === 'case13') {
          return case13
        }
        if (this.type === 'case14') {
          return case14
        }
        if (this.type === 'case15') {
          return case15
        }


       if (this.type === 'mcase1') {
         return mcase1
       }
       if (this.type === 'mcase2') {
         return mcase2
       }
       if (this.type === 'mcase3') {
         return mcase3
       }
       if (this.type === 'mcase4') {
         return mcase4
       }
       if (this.type === 'mcase5') {
         return mcase5
       }
       if (this.type === 'mcase6') {
         return mcase6
       }
       if (this.type === 'mcase7') {
         return mcase7
       }
       if (this.type === 'mcase8') {
         return mcase8
       }
       if (this.type === 'mcase9') {
         return mcase9
       }
       if (this.type === 'mcase10') {
         return mcase10
       }
       if (this.type === 'mcase11') {
         return mcase11
       }
       if (this.type === 'mcase12') {
         return mcase12
       }
       if (this.type === 'mcase13') {
         return mcase13
       }
       if (this.type === 'mcase14') {
         return mcase14
       }
       if (this.type === 'mcase15') {
         return mcase15
       }
      }
    },
    methods: {
      onClick: function (e) {
        location.assign(this.href)
      }
    }
  }
</script>
