<template>
  <div>
    <header class="header">
      <a href="/" class="logo">PCU</a>
      <div class="header-right">
        <a href="" @click="$router.push('/signup')">Sign up</a>
      </div>
    </header>
    <p id="error"> <strong>{{this.message}} </strong></p>
    <div class="login">
      <!-- template for login from https://codepen.io/miroot/pen/AYJGJO -->
      <div class="login-triangle"></div>
      <h2 class="login-header">Log in</h2>
      <form class="login-container" v-on:submit.prevent="submitForm">
        <p>
          <input
            type="text"
            placeholder="Email"
            v-model="form.email"
            required
          />
        </p>
        <p>
          <input
            type="password"
            placeholder="Password"
            v-model="form.password"
            required
          />
        </p>
        <p><input type="submit" value="Submit" @click="submitForm" /></p>
      </form>
    </div>
    <!-- footer -->
    <footer class="footer">
      <p>
        Created by
        <a href="https://github.com/tatacsd/PCU" target="_blank">CoffeLovers</a
        >☕
      </p>
    </footer>
  </div>
</template>

<script>
import ActorsDataServices from "../services/ActorsDataServices";

export default {
  data() {
    return {
      message: "",
      form: {
        email: "",
        password: "",
      },
    };
  },
  // on form submit send data to server
  methods: {
    submitForm() {
      ActorsDataServices.getByEmail(this.form.email)
        .then((response) => {
          // check  if password is correct
          if (response.data.password == this.form.password) {
            // save user brand and id in local storage
            localStorage.setItem("user", JSON.stringify(response.data));
            this.$router.push("/dashboard");
          }
        })
        .catch((error) => {
          console.log(error);
          this.message = "Wrong email or password";
        });
    },
  },
  mounted() {
    if(localStorage.getItem("user")){
      this.$router.push("/dashboard");
    } else {
      console.log("not logged in");
    }
  },
};
</script>

<style scoped>
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

#error {
  color: red;
  font-size: 1.2em;
  text-align: center;
  margin: 20px 0;
}
</style>