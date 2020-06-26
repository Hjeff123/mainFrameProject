<template>
  <div>
    <a-form :form="form" @submit="handleSubmit" style="width: 360px">
      <a-form-item>
        <a-input
          v-focus
          v-decorator="[
              'name',
              {
                rules: [{ required: true, message: '数据集名字不能为空' }]
              }
            ]"
          placeholder="请输入要查看的数据集名称"
          size="large"
        >
          <a-popover style="cursor: pointer" slot="suffix">
            <template slot="content">
              巴巴爸爸
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
        >查询</a-button
        >
      </a-form-item>
    </a-form>
    <a-collapse :bordered="true" v-if="result" v-model="activeKey">
      <a-collapse-panel key="1" disabled="true">
        <pre>{{result}}</pre>
      </a-collapse-panel>
    </a-collapse>

  </div>
</template>

<script>
  import Axios from "axios"
  import { Modal } from "ant-design-vue";

  export default {
    name: "Dataset",
    data() {
      return {
        form: this.$form.createForm(this),
        isLoading: false,
        result:null,
        activeKey:['1']
      };
    },
    mounted() {
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault();
        this.form.validateFields(async err => {
          if (err) return;

          this.doSearch();
        });
      },

      async doSearch() {
        this.isLoading = true;

        try {
          const io = this.form.getFieldsValue();
          const response = await Axios.get("http://localhost:8085/dataset/"+io.name,{
          });
          switch (+response.status) {
            case 200: {
              if(response.data == "")this.$message.warn("无此数据集")
              console.log(response.data)
              this.result = response.data;
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
