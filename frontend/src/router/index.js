import { createWebHistory, createRouter } from "vue-router";
import HelloWorld from "../components/HelloWorld";
import MyLogin from "../components/MyLogin";
import SignUp from "../components/SignUp";
import UserSuppliers from "../components/UserSuppliers";

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
  {
    path: "/signup",
    name: "Signup",
    component: SignUp,
  },
  {
    path: "/suppliers",
    name: "Suppliers",
    component: UserSuppliers,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
