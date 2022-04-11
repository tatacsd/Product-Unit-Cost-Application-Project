<template>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <div class="dashboard">
        <header class="header">
      <a href="/" class="logo">PCU</a>
      
        
      
    
      <div class="header-right">
        
        <!-- <a href="signup">Sign up</a> -->
        <a href="login">Log Out</a>
        
      </div>
      
  </header>
  <body>
    <div class="company">
    <h5 class="header-middle">Thays's company</h5>
    </div>  
      <div class="body">
        <!-- <div id="firstbutton"> -->
          
            <a href="/dashboard" class="logo">Invoices</a>
            
            
          
  
            <a href="/dashboard" class="logo">Products</a>
            <a href="/dashboard" class="logo">Raw Materials</a>
            <!-- </div> -->
            <!-- <div id="thirdbutton"> -->
         
            <a href="/dashboard" class="logo">Suppliers</a>
            <a href="/dashboard" class="logo">Variable Costs</a>
            <!-- </div> -->
          
        
      </div>
      
    </body>
    
    <BaseFooter />
    

    </div>




</template>
<script>
import BaseFooter from "./Base/BaseFooter.vue";

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

/* #firstbutton{
  text-align: center;
    margin-left: 38%;
    margin-right: 38%;
}

#secondbutton{
  display: flex;
    flex-direction: column;
    height: 45%;
    flex-wrap: wrap;
    justify-content: space-between;
    align-content: space-around;
    align-items: stretch;
    /* margin-left: 20%; */
    /* margin-right: 20%; */
    /* padding-left: 20%;
    padding-right: 20%; */
/* } */ 

.body{
 display: flex;
    flex-direction: row;
    flex-wrap: nowrap;
    background-color: beige;
    text-align: center;
    margin-top: 80px;
    margin-left: 5%;
    margin-right: 5%;
    width: 90%;
    height: 500px;
    padding: 10px;
    justify-content: space-between;
    align-items: center;
    align-content: center;
}

/* .body a {
  float: left;
  color: white;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 40px;
  line-height: 50px;
  border-radius: 10px;
  font-weight: bold;
  background-color: #000;
}

.body a.logo {
  font-size: 40px;
  font-weight: bold;
  width: 396px;
}

.body a:hover {
  background-color: rgb(66, 66, 66);
  color: white;
}

.body a.active {
  background-color: dodgerblue;
  color: white;
} */
/* .header-middle{
  
    width: 20%;
    margin-right: 768px;
    margin-left: 768px;
    margin-top: 60.178px;
    font-size: 26px;
    text-align: center;
} */
.company{
    margin-right: 768px;
    margin-left: 768px;
    margin-top: 60.178px;
    font-size: 26px;
    text-align: center;
}
.body a {
 float: center;
    color: white;
    text-align: center;
    padding: 12px;
    text-decoration: none;
    font-size: 40px;
    line-height: 50px;
    border-radius: 10px;
    font-weight: bold;
    background-color: #000;
    /* margin-left: 1.5%; */
    text-align: center;
    width: 310px;
    padding-top: 210px;
    padding-bottom: 210px;
    /* margin-left: 1.5%; */
    /* height: 100px;
}

.body a.logo {
  font-size: 40px;
    font-weight: bold;
    /* height: 100%; */
    /* text-align: center;
    width: 310px;
    padding-top: 12%;
    padding-bottom: 12%; */
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
