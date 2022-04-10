import { createWebHistory, createRouter } from "vue-router";
import HelloWorld from "../components/HelloWorld";
import MyLogin from "../components/MyLogin";
import SignUp from "../components/SignUp";
import UserSuppliers from "../components/UserSuppliers";
<<<<<<< HEAD
import InvoiceDetails from "../components/InvoiceDetails";
=======
import UserRawMaterial from "../components/UserRawMaterial";
>>>>>>> 10121c3 (rawMaterial base page and router)

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
  {
    path: "/invoicedetails",
    name: "InvoiceDetails",
    component: InvoiceDetails
  },
  {
    path: "/rawmaterial",
    name: "RawMaterial",
    component: UserRawMaterial,
  },
  // TODO: Products
  // TODO: Invoice
  // TODO: InvoiceDetails
  // TODO: Reports
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;