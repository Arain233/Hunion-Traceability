<template>
  <el-row :gutter="20">
    <el-col :span="4">
      <div class="grid-content"></div>
    </el-col>
    <el-col :span="16" style="text-align: center">
      <el-page-header @back="goBack" content="证书核验" style="margin: 20px 0 40px 0">
      </el-page-header>
      <br>
      <br>
      <br>
      <br>
      <div class="upload-style">
        <el-upload
          class="upload-demo"
          drag
          name="stream"
          action="http://120.76.197.109:8282/api/pub/verify"
          :on-success="handleSuccess"
          :on-error="handleError"
          multiple>
          <i class="el-icon-upload"></i>
          <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
          <div class="el-upload__tip" slot="tip">请上传pdf证书文件</div>
        </el-upload>
      </div>
      <el-dialog title="核验成功" :visible.sync="dialogTableVisible">
        恭喜您，证书核验通过!
        <span slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible = false">取 消</el-button>
          <el-button type="primary" @click="dialogTableVisible = false">确 定</el-button>
        </span>
      </el-dialog>
    </el-col>
    <el-col :span="4">
      <div class="grid-content"></div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      dialogTableVisible: false,
      formLabelWidth: '120px'
    };
  },
  methods: {
    handleSuccess(res) {
      if (res.code === 200) {
        this.$message.success('核验通过');
        this.dialogTableVisible = true
      } else {
        this.$message.error('核验未通过');
        this.$notify.error({
          title: '核验未通过',
          message: '该证书可能经过伪造',
          duration: 10000
        });
      }
    },
    handleError() {
      this.$message.error('网络错误，证书上传失败');
    },
    goBack() {
      this.$router.push('/home')
    }
  }
}
</script>

<style>
.el-row {
  margin-bottom: 20px;

}

.el-col {
  border-radius: 4px;
}

.bg-purple-dark {
  background: #99a9bf;
}

.bg-purple {
  background: #d3dce6;
}

.bg-purple-light {
  background: #e5e9f2;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}

.upload-style{
  transform: scale(1.5);
}

</style>
