<template>
  <ul class="infinite-list" style="overflow:auto; position:relative">
    <li v-for="(info, i) in listObj" class="infinite-list-item">
      {{ info.msg }}
      <el-switch
        v-model="info.value"
        class="switch-control"
        active-text="风控已开启"
        inactive-text="风控已关闭"
        :change="handleChange">
      </el-switch>
    </li>
  </ul>
</template>

<script>
export default {
  data() {
    return {
      count: Math.round(Math.random() * 5) + 1,
      value: true,
      listObj: [
        {value: true, msg: '防撞库风控系统已启动'},
        {value: false, msg: '上链业务接口防止恶提交风控探针未启动'},
        {value: true, msg: '查询业务接口防止恶提交风控探针已启动'},
        {value: true, msg: '用户信息接口防止恶提交风控探针已启动'},
        {value: false, msg: '结点监测接口未启动'}
      ],
    }
  },
  methods: {
    getCount() {
      return this.count
    },
    handleChange() {
      // this.$set(this.listObj, 1, "123")
    }
  }
}
</script>

<style>
.infinite-list-item {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 50px;
  background: #e8f3fe;
  margin: 10px;
  color: #7dbcfc;

}

.switch-control {
  position: absolute;
  left: 60px;
}

</style>
