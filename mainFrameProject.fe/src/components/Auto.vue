<template>
  <div>
    <a-form :form="form" layout="vertical" @submit="handleSubmit">
      <a-form-item>
        <span slot="label">
          模板JCL 代码
          <a-popover style="cursor: pointer">
            <template slot="content">
              <p>此处给出了一份实验模板JCL代码，预设了输入输出数据集</p>
              可以直接提交查看结果
            </template>
            <a-icon type="question-circle" />
          </a-popover>
        </span>
      </a-form-item>
      <div class="fff">
        <p>//LSTCATA JOB ,'USER',NOTIFY=&SYSUID</p>
        <p>//STEP1 EXEC PGM=IDCAMS</p>
        <p>//SYSPRINT DD SYSOUT=*</p>
        <p>//SYSIN DD *</p>
        <p> LISTCAT ENT(ST034) ALL</p>
        <p>/*</p>
      </div>
      <a-form-item>
        <a-button type="primary" html-type="submit" :loading="isLoading">
          提交
        </a-button>
      </a-form-item>
    </a-form>
    <a-collapse :bordered="false" v-if="result">
      <a-collapse-panel
        v-for="item in result"
        :key="item.id"
        :header="item.ddName"
      >
        <pre v-if="item.output">{{ item.output }}</pre>
      </a-collapse-panel>
    </a-collapse>
  </div>
</template>

<script>
  import Axios from "axios";

  export default {
    data() {
      return {
        form: this.createForm(),
        isLoading: false,
        result: null
      };
    },

    methods: {
      createForm(){
        console.log(this.$form);
        return this.$form.createForm(this);
      },


      async handleSubmit(e) {
        e.preventDefault();
        const {
          form: { validateFields }
        } = this;

        validateFields(async (errors, values) => {
          if (errors) return;
          this.isLoading = true;
          try {
            const response = await Axios.get("http://localhost:8085/autoSort", {
            });
            if (response.status === 200) {
              this.result = response.data;
              console.log(response.data)
              this.$message.success("Job 执行成功").then();
            } else {
              this.$message.warn("Job 已提交，但服务器响应超时").then();
            }
          } catch (error) {
            this.$message.error("发生错误：" + error.message).then();
          } finally {
            this.isLoading = false;
          }
        });
      },

      jclValidator(rule, value, callback) {
        const lines = (value && value.split("\n")) || [];
        for (const l of lines) {
          if (l.length > 72) {
            callback("每行不能超过 72 个字符");
          }
        }
        // always call callback. ref: https://github.com/ant-design/ant-design/issues/5155
        callback();
      }
    }
  };
</script>

<style lang="scss" scoped>
  .fff{
    background: #eeeeee;
  }

</style>
