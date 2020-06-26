<template>
  <a-layout class="layout">
      <a-layout-header style="display: flex; background: #fff; padding: 0">
        <h2 class="title">大型主机实验平台</h2>
        <div style="margin-right: 24px;">
          <a-dropdown>
            <a><a-icon type="user" /> {{ username }} </a>
            <a-menu slot="overlay" @click="handleClick">
              <a-menu-item key="logout">退出登录</a-menu-item>
            </a-menu>
          </a-dropdown>
        </div>
      </a-layout-header>
      <a-layout-content
        :style="{
          margin: '24px 16px',
          padding: '24px',
          background: '#fff',
          height: 'calc(100vh - 64px - 64px)',
          overflow: 'auto'
        }"
      >
        <div class="lab-container">
          <div class="left">
            <vue-markdown :source="htmlMD"></vue-markdown>
          </div>
          <div class="divider"></div>
          <div class="right">
            <a-tabs type="card">
              <a-tab-pane tab="JCL" key="jcl"><JCL /></a-tab-pane>
              <a-tab-pane tab="模板JCL" key="auto"><Auto /></a-tab-pane>
              <a-tab-pane tab="参数" key="para"><Para/></a-tab-pane>
              <a-tab-pane tab="查看数据集" key="dataset"><Dataset/></a-tab-pane>
            </a-tabs>
          </div>
        </div>
        <router-view />
      </a-layout-content>
  </a-layout>
</template>

<script>
  import Axios from "axios";
  import VueMarkdown from "vue-markdown";
  import JCL from "@/components/JCL"
  import Auto from "@/components/Auto"
  import Para from "@/components/Para"
  import Dataset from "@/components/Dataset";

  export default {
    name: "Home",
    components: {Dataset, VueMarkdown,JCL,Auto,Para},
    data() {
      return {
        htmlMD:this.getContent()
      };
    },
    computed: {
      username() {
        return this.$store.state.user.username;
      },

    },

    methods: {
      getContent(){
        Axios.get(`/static/intro.md`)
          .then(response => {
            this.htmlMD = response.data;
            return response.data;
          })
          .catch(e => {
            message.error("获取实验说明失败：" + e.message).then();
          });
      },

      async handleClick({ key }) {
        if (key === "logout") {
          await this.$store.dispatch("user/logout");
        }
      },
    }
  };
</script>

<style lang="scss" scoped>
  .logo {
    height: 32px;
    color: #fff;
    margin: 12px;
    text-align: center;
    font-size: 24px;
    span {
      line-height: 32px;
    }
  }

  .layout {
    min-height: 100vh;
    transition: margin-left 0.2s;
  }

  .lab-container {
    display: flex;
    justify-content: center;
    position: relative;

    height: 100%;

    .left,
    .right {
      flex: 1 1 0;
      overflow: auto;
    }

    .divider {
      position: relative;
      top: 0;
      bottom: 0;
      width: 2px;
      background: #ddd;
      margin: 0 10px;
    }
  }


  .title {
    margin: auto;
  }
</style>
