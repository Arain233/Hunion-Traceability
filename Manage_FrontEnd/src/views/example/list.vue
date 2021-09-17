<template>
  <div class="app-container">
    <el-table
      :data="tableData"
      border
      style="width: 100%">
      <el-table-column
        fixed="left"
        prop="id"
        label="ID"
        width="60">
      </el-table-column>
      <el-table-column
        prop="farmer_id"
        label="种植户ID"
        width="70">
      </el-table-column>
      <el-table-column
        prop="producer_id"
        label="生产商ID"
        width="70">
      </el-table-column>
      <el-table-column
        prop="name"
        label="药材名称"
        width="120">
      </el-table-column>
      <el-table-column
        prop="category"
        label="药材类别"
        width="120">
      </el-table-column>
      <el-table-column
        prop="purch_time"
        label="购买时间"
        width="120">
      </el-table-column>
      <el-table-column
        prop="tra_code"
        label="唯一溯源码"
        width="150">
      </el-table-column>
      <el-table-column
        prop="amount"
        label="药材数量"
        width="100">
      </el-table-column>
      <el-table-column
        prop="in_time"
        label="入库时间"
        width="150">
      </el-table-column>
      <el-table-column
        prop="out_time"
        label="出库时间"
        width="150">
      </el-table-column>
      <el-table-column
        label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
/* eslint-disable */
import axios from 'axios'
import {fetchList} from '@/api/article'
import Pagination from '@/components/Pagination'
import {getToken} from "@/api/qiniu"; // Secondary package based on el-pagination

export default {
  name: 'ArticleList',
  methods: {
    handleClick(row) {
      console.log(row);
    }
  },

  data() {
    return {
      tableData: null
    }
  },

  created() {
    // console.log(this.$store.state.user.token) // for debug
    axios.get('http://120.76.197.109:8282/medicine/findAll', {
      headers: {
        token: this.$store.state.user.token
      }
    }).then((response) => {
      console.log(response.data.data)
      this.tableData = response.data.data
    })
  }
}

</script>

<style scoped>
/*.edit-input {
  padding-right: 100px;
}

.cancel-btn {
  position: absolute;
  right: 15px;
  top: 10px;
}*/
</style>
