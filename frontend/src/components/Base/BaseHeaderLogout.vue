<template>
  <header class="header">
    <a href="#home" class="logo">PCU</a>
    <div class="header-right">
      <a href="" @click="$router.push('/login'), logout()">Logout</a>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {};
  },
  // on form submit send data to server
  methods: {
    logout() {
      console.log("logout");
      localStorage.removeItem("user");
      // clean local storage
      localStorage.clear();
      // this.$router.push("/login");
    },
  },
};
</script>

<style scoped>
</style>