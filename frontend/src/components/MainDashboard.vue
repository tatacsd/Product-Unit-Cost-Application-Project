<template>
  <div class="dashboard">
    <header class="header">
      <a href="" class="logo">PCU</a>
      <div class="header-right">
        <a href="login" @click="logout">Log Out</a>
      </div>
    </header>
    <div class="company">
      <h5>{{this.brand}}'s brand</h5>
    </div>
    <div class="main-background">
      <a href="/invoices" class="logo">Invoices</a>
      <a href="/products" class="logo">Products</a>
      <a href="/rawmaterials" class="logo2">Raw Materials</a>
      <a href="/suppliers" class="logo">Suppliers</a>
      <a href="/variablecosts" class="logo2">Variable Costs</a>
      <a href="/report" class="logo">Report</a>
    </div>
    <BaseFooter />
  </div>
</template>
<script>
import BaseFooter from "./Base/BaseFooter.vue";

export default {
  components: {
    BaseFooter,
  },
  name: "MainDashboard",
  data() {
    return {
      brand: "",
    };
  },
  methods: {
    logout() {
      localStorage.removeItem("user");
      this.$router.push("/login");
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      // set first letter to upercase
      this.brand = JSON.parse(localStorage.getItem("user")).brand;
      this.brand = this.brand.charAt(0).toUpperCase() + this.brand.slice(1);
    } else {
      this.$router.push("/login");
      console.log("not logged in");
    }
  },
};
</script>
<style scoped>
.header {
  margin-bottom: 5vh;
}
.dashboard {
  min-width: 100%;
  height: 100%;
}
.main-background {
  display: flex;
  justify-content: space-between;
  background-color: beige;
  height: 60%;
  width: 90%;
  margin: auto;
}
.main-background a {
  color: white;
  text-align: center;
  font-size: 2vw;
  background-color: #000;
  text-decoration: none;
  padding-top: 25vh;
  padding-bottom: 25vh;
  border-radius: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
  margin-left: 15px;
  margin-right: 15px;
}
.main-background a.logo {
  font-weight: bold;
  width: 15vw;
  padding-top: 24vh;
}
.main-background a.logo2 {
  font-weight: bold;
  width: 15vw;
  padding-top: 22vh;
}
.main-background a:hover {
  background-color: rgb(83, 83, 83);
  color: white;
}
.header a:hover {
  background-color: #000;
  color: white;
}
.header a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  line-height: 25px;
  border-radius: 4px;
  font-weight: bold;
}
.header a.logo, .header a.logo2 {
  font-size: 25px;
  font-weight: bold;
}
.header a:hover {
  background-color: #000;
  color: white;
}
.header a.active {
  background-color: dodgerblue;
  color: white;
}
.header-right {
  float: right;
}
.company {
  margin-bottom: 3vh;
  font-size: 40px;
  text-align: center;
}
</style>