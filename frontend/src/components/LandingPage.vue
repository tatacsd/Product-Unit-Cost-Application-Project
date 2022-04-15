<template>
  <div class="landingPage">
    <header class="header">
      <a href="/" class="logo">PCU</a>
      <div class="header-right">
        <!-- <a href="signup">Sign up</a> -->
        <a href="login">Log in</a>
      </div>
    </header>
    <body>
      <div class="body">
        <div class="image">
          <img
            src="../assets/LandingPagePicTeam.jpeg"
            height="400px"
            width="500px"
          />
        </div>
        <p>
          Bring solutions to support small and medium businesses manufacturing
          management with specific tools to calculate their product unit cost
          and generate customized reports. The proposed software will fit any
          kind of manufacturing industry such as textiles, automotive,
          electronics, food and beverages, pharmaceuticals etc.
        </p>
      </div>
    </body>
    <BaseFooter />
  </div>
</template>

<script>
import BaseFooter from "./Base/BaseFooter.vue";
export default {
  components: {
    BaseFooter,
  },
  name: "LandingPage",
  props: {
    msg: String,
  },
};
</script>

<style scoped>
.landingPage {
  background-color: #f2f2f2;
  margin: 0 auto;
  padding: 0;
}
template {
  background-color: #f2f2f2;
}
.footer {
  position: absolute;
  bottom: 0;
  width: 100%;
  height: 20px; /* Height of the footer */
  background: rgb(177, 172, 172);
}
.body {
  display: flex;
  background-color: beige;
  text-align: center;
  margin: 0 auto;
  width: 90%;
  height: 78vh;
  padding: 10px;
  justify-content: space-around;
  align-items: center;
}
.body p {
  font-size: 28px;
  text-align: justify;
  width: 150%;
  padding-right: 3%;
  padding-left: 3%;
}
.image {
  padding-left: 0px;
  float: left;
  width: 80%;
  border-radius: 10px;
}
.image img {
  border-radius: 10px;
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