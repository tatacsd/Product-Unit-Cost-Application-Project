<template>
 <router-view />
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
*, *:before, *:after {
	margin: 0;
	padding: 0;

	-webkit-box-sizing: border-box !important;
	-moz-box-sizing: border-box !important;
	-ms-box-sizing: border-box !important;
	box-sizing: border-box !important;
}

#app {
  font-family: 'Open Sans', sans-serif;
  box-sizing: border-box;
  margin: 0;
  padding: 0;
}
</style>
