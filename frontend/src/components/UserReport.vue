<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- body -->
    <h1>Statistical Report</h1>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell">Description</div>
          <div class="cell">Value</div>
        </div>
        <div class="row" v-for="data in reportData" :key="data.descrition">
          <div class="cell left">{{ data.descrition }}</div>
          <div class="cell">{{ data.value }}</div>
        </div>
      </div>
    </div>
    <!-- footer -->
    <BaseFooter />
  </div>
</template>

<script>
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
import BaseFooter from "./Base/BaseFooter.vue";
import ReportDataServices from "../services/ReportDataServices.js";
export default {
  components: {
    BaseFooter,
    BaseHeaderDashboard,
  },
  data() {
    return {
      reportData: {},
    };
  },
  methods: {
    getReport() {
      ReportDataServices.get().then((response) => {
        this.reportData = response.data;
        console.log(this.reportData);
      });
    },
  },
  mounted() {
    this.getReport();
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
  padding: 40px;
}
.container {
  /* in the center vertically and hotizontally of the page*/
  display: flex;
  justify-content: top;
  overflow: auto;
  height: 55vh;
  width: 80vw;
}

.table {
  display: table;
  justify-content: top;
}

.row-header {
  display: table-row;
  background-color: black;
  color: white;
  text-align: center;
}

.row {
  display: table-row;
  background: #f6f6f6;
  text-align: center;
}
.cell {
  padding: 6px 12px;
  display: table-cell;
  vertical-align: middle;
  max-height: 10px;
}

.left {
  text-align: left;
}
</style>