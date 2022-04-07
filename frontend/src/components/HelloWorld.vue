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

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
