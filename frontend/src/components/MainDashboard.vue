<template>
  <div class="dashboard">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <header class="header">
      <a href="/">PCU</a>
      <div class="header-right">      
        <a href="login">Log Out</a>      
      </div>    
    </header>
    <body>
      <h1 class="header-middle">Thays's company</h1>  
        <div class="body">
          <a href="/invoices" class="logo">Invoices</a>
          <a href="/products" class="logo">Products</a>
          <a href="/rawmaterial" class="logo">Raw Materials</a>
          <a href="/suppliers" class="logo">Suppliers</a>
          <a href="/variablecosts" class="logo">Variable Costs</a>
        </div>    
    </body>   
    <BaseFooter />
  </div>
</template>

<script>
import BaseFooter from "./Base/BaseFooter.vue"

export default {
  components: {
    BaseFooter
  },
  name: 'MainDashboard',
  props: {
    msg: String
  }
}
</script>
<style scoped>

.dashboard {
  width: 100%;
  height: 30rem;
}

h1{
  margin: 2em;
  text-align: center;
}

.body{
  display: flex;
  background-color: beige;
  text-align: center;
  vertical-align: middle;
  margin: auto;
  width: 95%;
  height: 80%;
}

.logo {
  flex: 1;
  margin: 10px;
  padding: 9% 0;
  font-size: 40px;
  border-radius: 10px;
  font-weight: bold;
  text-decoration: none;
  color: white; 
  background-color: #000;
}

.body a:hover {
  background-color: rgb(66, 66, 66);
  color: white;
}
.body a.active {
  background-color: dodgerblue;
  color: white;
}

</style>
