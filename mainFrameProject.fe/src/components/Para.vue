<template>
  <a-form :form="form" @submit="handleSubmit" style="width: 360px">
    <a-form-item>
      <a-input
        v-focus
        v-decorator="[
            'input1',
            {
              rules: [{ required: true, message: '数据集名字不能为空' }]
            }
          ]"
        placeholder="请输入要合并的数据集1"
        size="large"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            如：ST034.DATA1(TEST1)
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
        v-decorator="[
            'input2',
            {
              rules: [{ required: true, message: '数据集名字不能为空' }]
            }
          ]"
        size="large"
        placeholder="请输入要合并的数据集2"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            如：ST034.DATA1(TEST2)
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-input
        v-decorator="[
            'output',
            {
              rules: [{ required: true, message: '数据集名字不能为空' }]
            }
          ]"
        size="large"
        placeholder="请输入存放合并结果的数据集"
      >
        <a-popover style="cursor: pointer" slot="suffix">
          <template slot="content">
            如：ST034.DATA(TEST)
          </template>
          <a-icon type="question-circle" />
        </a-popover>
      </a-input>
    </a-form-item>
    <a-form-item>
      <a-button
        type="primary"
        html-type="submit"
        size="large"
        block
        :loading="isLoading"
      >提交</a-button
      >
    </a-form-item>
  </a-form>
</template>

<script>
  import Axios from "axios"
  import { Modal } from "ant-design-vue";

  export default {
    name: "Para",
    data() {
      return {
        form: this.$form.createForm(this),
        isLoading: false
      };
    },
    mounted() {
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault();
        this.form.validateFields(async err => {
          if (err) return;

          this.doSort();
        });
      },

      async doSort() {
        this.isLoading = true;

        try {
          const io = this.form.getFieldsValue();
          const response = await Axios.get("http://localhost:8085/autoSort1",{
            params:{
              input1:io.input1,
              input2:io.input2,
              output:io.output
            }
          });
          switch (+response.status) {
            case 200: {
              this.$message.success("Job 执行成功").then();
              console.log(response.data)
              return;
            }
            case 401:
              Modal.error({
                title: "操作失败",
                content: "网络错误",
                centered: true
              });
          }
        } catch (error) {
          if (error.message.includes("401")) {
            Modal.error({
              title: "操作失败",
              content: "网络错误",
              centered: true
            });
          } else {
            Modal.error({
              title: "操作失败",
              content: error.message,
              centered: true
            });
          }
        } finally {
          this.isLoading = false;
        }
      }
    }
  };
</script>

<style scoped>

</style>
