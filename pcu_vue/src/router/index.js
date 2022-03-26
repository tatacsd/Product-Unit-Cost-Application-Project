import { createWebHistory, createRouter } from "vue-router";
import HelloWorld from "../components/HelloWorld";
import MyLogin from "../components/MyLogin";

const routes = [
  {
    path: "/",
    name: "HelloWorld",
    component: HelloWorld,
  },
  {
    path: "/login",
    name: "Login",
    component: MyLogin,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
