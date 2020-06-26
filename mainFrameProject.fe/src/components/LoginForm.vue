<template>
  <div class="login-page">
    <a-form :form="form" @submit="handleSubmit" style="width: 360px">
      <h1>大型主机实验平台</h1>
      <a-form-item>
        <a-input
          v-focus
          v-decorator="[
            'account',
            {
              rules: [{ required: true, message: '用户名不能为空' }]
            }
          ]"
          placeholder="请输入用户名"
          size="large"
        >
          <a-icon slot="prefix" type="user" />
          <a-popover style="cursor: pointer" slot="suffix">
            <template slot="content">
              账号和密码与你登录主机的账号和密码相同
            </template>
            <a-icon type="question-circle" />
          </a-popover>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-decorator="[
            'password',
            {
              rules: [{ required: true, message: '密码不能为空' }]
            }
          ]"
          size="large"
          type="password"
          placeholder="请输入密码"
        >
          <a-icon slot="prefix" type="lock" />
          <a-popover style="cursor: pointer" slot="suffix">
            <template slot="content">
              联系老师重置密码
            </template>
            <a-icon type="question-circle" />
          </a-popover>
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input
          v-decorator="[
            'address',
            {
              rules: [{ required: true, message: '地址不能为空' }]
            }
          ]"
          size="large"
          placeholder="请填入 z/OSMF 的地址"
          @change="onAddressChange"
        >
          <a-icon slot="prefix" type="cloud" />
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-button
          type="primary"
          html-type="submit"
          size="large"
          block
          :loading="isLoading"
        >登录</a-button
        >
      </a-form-item>
      <a-popover style="cursor: pointer">
        <template slot="content">
          <p>
            请确保 z/OSMF 的地址正确，且能够访问。
          </p>
          <p>
            请确保填入的账号密码可以登录 z/OSMF。
          </p>
          <p>账号登录 z/OSMF 需要权限，可将账号关联到对应组中。</p>
          新创建的账号或重置了密码的账号需要登录一次 TSO 。
        </template>
        无法登录
        <a-icon type="question-circle" />
      </a-popover>
    </a-form>
  </div>
</template>

<script>
  import Axios from "axios";
  import { Modal } from "ant-design-vue";
  // remove warning of async-validator used by a-form
  // ref: https://github.com/yiminghe/async-validator#how-to-avoid-warning
  import Schema from "async-validator";
  Schema.warning = function() {};

  export default {
    name: "LoginForm",
    data() {
      return {
        form: this.$form.createForm(this),
        isLoading: false,
        address: ""
      };
    },
    mounted() {
      this.form.setFieldsValue({
        address: "10.60.43.8:8800"
      });
      this.address = "10.60.43.8:8800";
    },
    methods: {
      handleSubmit(e) {
        e.preventDefault();
        this.form.validateFields(async err => {
          if (err) return;

          this.doLogin();
        });
      },

      async doLogin() {
        this.isLoading = true;

        try {
          const user = this.form.getFieldsValue();
          const response = await Axios.post("http://localhost:8085/login", user);
          switch (+response.status) {
            case 200: {
              var loginState = {
                role:"student",
                uid:user["account"]

              }
              console.log(loginState)
              await this.$store.dispatch("user/login", loginState);
              return;
            }
            case 401:
              Modal.error({
                title: "登录失败",
                content: "用户名或密码错误",
                centered: true
              });
          }
        } catch (error) {
          if (error.message.includes("401")) {
            Modal.error({
              title: "登录失败",
              content: "用户名或密码错误",
              centered: true
            });
          } else {
            Modal.error({
              title: "登录失败",
              content: error.message,
              centered: true
            });
          }
        } finally {
          this.isLoading = false;
        }
      },

      onAddressChange(e) {
        if (e.target.value) {
          this.address = e.target.value;
        } else {
          this.address = "请填入z/OSMF地址";
        }
      }
    }
  };
</script>
<style scoped>
  h1 {
    text-align: center;
  }
</style>
