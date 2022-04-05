<template>
    <div>
        <header class="header">
            <a href="#home" class="logo">PCU</a>
            <div class="header-right">
                <a href="" @click="$router.push('/login')">Login</a>
            </div>
        </header>
        <body>
            <div class="signup">
                <div class="signup-triangle"></div>
                <h2 class="signup-header">Signup</h2>
                <form class="signup-container" v-on:submit.prevent="submitForm">
                    <p><input type="text" placeholder="Brand" v-model="form.brand" required></p>
                    <p>
                        <select v-model="form.activity" required>
                            <option value="" disabled selected>Select Activity</option>
                            <option value="textil sport">Textil Sport</option>
                            <option value="textil formal">Textil Formal</option>
                        </select>
                    </p>
                    <!-- <p><input type="text" placeholder="Activity" v-model="form.activity" required></p> -->
                    <p><input type="text" placeholder="Phone" v-model="form.phone" required></p>
                    <p><input type="text" placeholder="Website" v-model="form.webside" required></p>
                    <p><input type="email" placeholder="Email" v-model="form.email" required></p>
                    <p><input type="password" placeholder="Password" v-model="form.password" required></p>
                    <p><input type="text" placeholder="Address" v-model="form.address" required></p>
                    <p><input type="submit" value="Create account" @click="signup"></p>
                </form>
            </div>

        </body>
        <!-- footer -->
        <footer class="footer">
            <p>Created by <a href="https://github.com/tatacsd/PCU" target="_blank">CoffeLovers</a> 🛸 </p>
        </footer>
    </div>
</template>

<script>
import http from "../http-common";

export default {
    data() {
        return {
            form: {
                brand: '',
                activity: '',
                phone: '',
                webside: '',
                email: '',
                password: '',
                address: ''
            }
        }
    },
    // on form submit send data to server
    methods:{
      submitForm(){
        http.post("/actors", this.form)
          .then(
              (response) => {
                  // add a alert msg
                  alert("Successfully created account!\nPlease to login save the user id:" + response.data.actorId);
                  // redirect to login page
                  this.$router.push('/login');         
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

/* Signup */
.signup {
  width: 50%;
  margin: 16px auto;
  font-size: 16px;
  text-align: center;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

/* Reset top and bottom margins from certain elements */
.signup-header,
.signup p {
  margin-top: 0;
  margin-bottom: 0;
}


.signup-header {
  background: #000;
  padding: 20px;
  font-size: 1.4em;
  font-weight: normal;
  text-align: center;
  text-transform: uppercase;
  color: #fff;
}

.signup-container {
  background: #ebebeb;
  padding: 12px;
}

/* Every row inside .signup-container is defined with p tags */
.signup p {
  padding: 12px;
}

.signup input,
.signup select {
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

.signup input[type="email"],
.signup input[type="password"] {
  background: #fff;
  border-color: #bbb;
  color: #555;
}

/* Text fields' focus effect */
.signup input[type="email"]:focus,
.signup input[type="password"]:focus {
  border-color: #888;
}

.signup input[type="submit"] {
  background: #000;
  border-color: transparent;
  color: #fff;
  cursor: pointer;
}

.signup input[type="submit"]:hover {
  background: #17c;
}

/* Buttons' focus effect */
.signup input[type="submit"]:focus {
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