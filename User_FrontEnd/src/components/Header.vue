<template>
  <header class="zui-header">
    <slot></slot>
  </header>
</template>
<script>
  import './less/header.less'
  import Logo from './Logo'
  export default {
    props: {
      logoType: {
        default: 'zhichou'
      }
    },
    components: {Logo}
  }
</script>
