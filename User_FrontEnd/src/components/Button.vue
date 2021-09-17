<template>
  <button class="zui-button" @click="onClick">
    <slot></slot>
  </button>
</template>
<script>
  import './less/button.less'
  export default {
    methods: {
      onClick (e) {
        this.$emit('click', e)
      }
    }
  }
</script>
