<template>
<div>
  <header class="header">
      <a href="#home" class="logo">PCU</a>
      <div class="header-right">
        <a href="" @click="$router.push('/signup')">Sign up</a>
      </div>
  </header>
<!-- template for login from https://codepen.io/miroot/pen/AYJGJO -->
    <div class="login">
      <div class="login-triangle"></div>
      <h2 class="login-header">Log in</h2>
      <form class="login-container" v-on:submit.prevent="submitForm">
        <p><input type="text" placeholder="Email" v-model="form.email" required></p>
        <p><input type="password" placeholder="Password" v-model="form.password" required></p>
        <p><input type="submit" value="Log in"></p>
      </form>
    </div>
  </div>
  <!-- footer -->
  <footer class="footer">
      <p>Created by <a href="https://github.com/tatacsd/PCU" target="_blank">CoffeLovers</a> 🛸 </p>
  </footer>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      form: {
        email: '',
        password: ''
      }
    }
  },
  // on form submit send data to server
  methods:{
    submitForm(){
      http.get("/actors/" + this.form.email)
        .then(
            (response) => {
                // check  if password is correct
                if(response.data.password == this.form.password){
                    // save user brand and id in local storage
                    localStorage.setItem('user', JSON.stringify(response.data));
                    this.$router.push('/dashboard');
                }     
        })
        .catch(
            error => {
                console.log(error);
            }
        );
    },
  }    
}
</script>

<style scoped>
/* 'Open Sans' font from Google Fonts */
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,700);

body {
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
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

.login {
  width: 400px;
  margin: 16px auto;
  font-size: 16px;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* Reset top and bottom margins from certain elements */
.login-header,
.login p {
  margin-top: 0;
  margin-bottom: 0;
}


.login-header {
  background: #000;
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
}

.login-container {
  background: #ebebeb;
  padding: 12px;
}

/* Every row inside .login-container is defined with p tags */
.login p {
  padding: 12px;
}

.login input {
  box-sizing: border-box;
  display: block;
  width: 100%;
  border-width: 1px;
  border-style: solid;
  padding: 16px;
  outline: 0;
  font-family: inherit;
  font-size: 0.95em;
}

.login input[type="email"],
.login input[type="password"] {
  background: #fff;
  border-color: #bbb;
  color: #555;
}

/* Text fields' focus effect */
.login input[type="email"]:focus,
.login input[type="password"]:focus {
  border-color: #888;
}

.login input[type="submit"] {
  background: #000;
  border-color: transparent;
  color: #fff;
  cursor: pointer;
}

.login input[type="submit"]:hover {
  background: #17c;
}

/* Buttons' focus effect */
.login input[type="submit"]:focus {
  border-color: #05a;
}

/* Footer */
.footer {
  background: #ffdc14;
  color: #000;
  font-weight: bold;
  text-align: center;
  padding: 20px;
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
}

.footer a {
  text-decoration: none;
  color: #000;
  font-weight: bold;
}

</style>