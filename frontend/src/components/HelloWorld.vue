<template>
  <div class="hello">

    <header class="header">
<<<<<<< HEAD
      <a href="/" class="logo">PCU</a>
      <div class="header-right">
        <a href="signup">Sign up</a>
        <a href="login">Log in</a>
=======
      <a href="#home" class="logo">PCU</a>
      <div class="header-right">
        <a href="#home">Sign up</a>
        <a href="#home">Log in</a>
>>>>>>> ac41b47ef5c6ff43672658f29d632fac378f23fe
      </div>
  </header>
    <body>
      <div class="body">
        <div class="image">
<<<<<<< HEAD
      <img src="../assets/LandingPagePicTeam.jpeg" height="450px" width="500px">
=======
      <img src="https://www.pngall.com/wp-content/uploads/4/Gear-PNG-Image.png" height="150px" width="150px">
>>>>>>> ac41b47ef5c6ff43672658f29d632fac378f23fe
      </div>
      <p>Bring solutions to support small and medium businesses manufacturing management with specific tools to calculate their product unit cost and generate customized reports.
         The proposed software will fit any kind of manufacturing industry such as textiles, automotive, electronics, food and beverages, pharmaceuticals etc.</p>
      </div>
    </body>
<<<<<<< HEAD
    <BaseFooter />
    <!-- <footer class="footer">
      <p>Created by Coffeelovers</p>
    </footer> -->
=======
    <footer class="footer">
      <p>Created by Coffeelovers</p>
    </footer>
>>>>>>> ac41b47ef5c6ff43672658f29d632fac378f23fe
  </div>
</template>

<script>

import BaseFooter from "./Base/BaseFooter.vue";
export default {
  components: {
    BaseFooter
  },
  name: 'HelloWorld',
  props: {
    msg: String
  }
}

</script>

<style scoped>
.hello{
 background-color: #f2f2f2;
 height: 100vh;
 
}
template{
  background-color: #f2f2f2;
  
}
.footer {
   position:absolute;
   bottom:0;
   width:100%;
   height:20px;   /* Height of the footer */
   background:rgb(177, 172, 172);
}
.body{
  display: flex;
  background-color: beige;
  text-align: center;
  margin-top:150px;
  margin-left: 5%;
  margin-right: 5%;
  width: 90%;
  height: 500px;
  padding: 10px;
  justify-content: space-around;
  align-items: center;
}
.body p{
<<<<<<< HEAD
  font-size: 28px;
  text-align: justify;
  width: 150%;
  padding-right:3%;
  padding-left: 3%;
=======
  font-size: 20px;
>>>>>>> ac41b47ef5c6ff43672658f29d632fac378f23fe
}
.image{
  padding-left:0px;
  float: left;
<<<<<<< HEAD
  width: 80%;
  border-radius: 10px;
}
.image img{
  border-radius: 10px;
=======
  width: 40%;
>>>>>>> ac41b47ef5c6ff43672658f29d632fac378f23fe
}
.header {
  overflow: hidden;
  background-color: #ffdc14;
  padding: 20px 10px;
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
.header a.logo {
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
</style>