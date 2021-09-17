<template>
  <div style="height: 80rem" οnlοad="javascript:this.height=this.contentWindow.document.body.scrollHeight+30;">
    <iframe src="http://120.76.197.109/data" frameborder="0" width="100%" height="100%" ref="iframe"
    ></iframe>
  </div>
</template>

<script>
import {fetchList} from '@/api/article'
import Sortable from 'sortablejs'

export default {
  data() {
    return {
      iframeWin: {}
    }
  },
  mounted() {
    this.iframeWin = this.$refs.iframe.contentWindow
    this.$nextTick(() => {
      // 在外部 Vue 的 window 上添加 postMessage 的监听，并且绑定处理函数 handleMessage
      window.addEventListener('message', this.handleMessage)
    })
  },
  destroyed() {
    // 注意移除监听！注意移除监听！注意移除监听！
    window.removeEventListener('message', this.handleMessage)
  }
}
</script>

<style>

</style>
