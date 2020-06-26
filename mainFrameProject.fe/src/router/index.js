import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/Login'
import store from "@/store"
import Home from "@/components/Home"

Vue.use(Router)

const router =  new Router({
  routes: [

    {
      path: '/login',
      name: 'login',
      component: Login
    },
    {
      path:'/home',
      name:'home',
      component:Home
    }
  ]
})

router.beforeEach((to, from, next) => {
  if (store.getters["user/isLogin"]) {
    if (to.name === "login") {
      next("/home");
    }
  } else {
    if (to.name !== "login") {
      next("/login");
    }
  }
  next();
});


export default router;
