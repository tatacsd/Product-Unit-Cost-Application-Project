<template>
  <div class="hello">

    <header class="header">
      <a href="#home" class="logo">PCU</a>
      <div class="header-right">
        <a href="#home">Sign up</a>
        <a href="#home">Log in</a>
      </div>
  </header>
    <body>
      <div class="body">
        <div class="image">
      <img src="https://www.pngall.com/wp-content/uploads/4/Gear-PNG-Image.png" height="150px" width="150px">
      </div>
      <p>Bring solutions to support small and medium businesses manufacturing management with specific tools to calculate their product unit cost and generate customized reports.
         The proposed software will fit any kind of manufacturing industry such as textiles, automotive, electronics, food and beverages, pharmaceuticals etc.</p>
      </div>
    </body>
    <footer class="footer">
      <p>Created by Coffeelovers</p>
    </footer>
  </div>
</template>

<script>
export default {
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
  font-size: 20px;
}
.image{
  padding-left:0px;
  float: left;
  width: 40%;
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