import { createWebHistory, createRouter } from "vue-router";
import LandingPage from "../components/LandingPage";
import MyLogin from "../components/MyLogin";
import SignUp from "../components/SignUp";
import UserSuppliers from "../components/UserSuppliers";
import VendorInvoices from "../components/VendorInvoices";
import InvoiceDetails from "../components/InvoiceDetails";
import UserRawMaterial from "../components/UserRawMaterial";
import UserVariableCosts from "../components/UserVariableCosts";
import MainDashboard from "../components/MainDashboard";
import UserProducts from "../components/UserProducts";

const routes = [
  {
    path: "/",
    name: "LandingPage",
    component: LandingPage,
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
    path: "/vendorinvoices",
    name: "VendorInvoices",
    component: VendorInvoices,
  },
  {
    path: "/invoicedetails",
    name: "InvoiceDetails",
    component: InvoiceDetails,
  },
  {
    path: "/rawmaterials",
    name: "RawMaterial",
    component: UserRawMaterial,
  },
  {
    path: "/variablecosts",
    name: "VariableCosts",
    component: UserVariableCosts,
  },
  {
    path: "/dashboard",
    name: "Dashboard",
    component: MainDashboard,

  },
  {
    path: "/products",
    name: "Products",
    component: UserProducts,
  },


  // TODO: Invoice
  // TODO: Reports
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
