<template>
  <div class="zui-form-item">
    <label class="zui-form-label">{{label}}</label>
    <slot></slot>
  </div>
</template>
<script>
  export default {
    props: {
      label: {
        default: ''
      }
    }
  }
</script>
