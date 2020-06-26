// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Antd from "ant-design-vue";
import store from "./store"
import "ant-design-vue/dist/antd.css";
import VueResource from "vue-resource";

Vue.use(Antd);
Vue.use(VueResource);
Vue.config.productionTip = false

Vue.directive("focus", {
  // When the bound element is inserted into the DOM...
  inserted: function(el) {
    if (el.tagName === "input") {
      // Focus the element
      el.focus();
    } else {
      el = el.getElementsByTagName("input")[0];
      el.focus();
    }
  }
});

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
