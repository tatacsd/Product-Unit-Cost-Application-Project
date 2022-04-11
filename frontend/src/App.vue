<template>
  <router-view />
</template>

<script>
export default {
  name: "App",
};
</script>

<style>
*,
*:before,
*:after {
  margin: auto;
  padding: 0;

  -webkit-box-sizing: border-box !important;
  -moz-box-sizing: border-box !important;
  -ms-box-sizing: border-box !important;
  box-sizing: border-box !important;
}

#app {
  font-family: "Open Sans", sans-serif;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
  height: 100vh;
  width: 100vw;
}

/* 'Open Sans' font from Google Fonts */
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);

body {
  background: #f2f2f2;
  font-family: "Open Sans", sans-serif;
}

/* Header */
.header {
  overflow: hidden;
  background-color: #ffdc14;
  padding: 20px 10px;
  width:100%;
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

/* Footer */
.footer {
  position: absolute;
  bottom: 0;
  background: #ffdc14;
  color: #000;
  font-weight: bold;
  text-align: center;
  padding: 20px;
  width: 100%;
}

.footer a {
  text-decoration: none;
  color: #000;
  font-weight: bold;
}
</style>
