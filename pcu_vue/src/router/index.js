import { createWebHistory, createRouter } from "vue-router";
import HelloWorld from "../components/HelloWorld";

const routes = [
  {
    path: "/",
    name: "HelloWorld",
    component: HelloWorld,
  },
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
