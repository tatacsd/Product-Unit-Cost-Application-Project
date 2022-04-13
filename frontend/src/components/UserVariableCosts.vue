<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>Variable Costs</h1>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell">ID</div>
          <div class="cell">Description</div>
          <div class="cell">Value</div>
          <div class="cell">Date</div>
          <div class="cell"></div>
        </div>
        <div class="row">
          <!-- Add raw material -->
          <div class="cell">
            <p class="add-btn" v-if="!update">
              <img
                src="../assets/plus.png"
                alt="add"
                width="20"
                height="20"
                @click="addVariableCosts()"
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="description"
                placeholder="description"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input type="text" v-model="value" placeholder="value" required />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="date"
                placeholder="yyyy-mm-dd"
                required
              />
            </p>
          </div>
          <div class="cell">
            <!-- button to update the cell  will be visible when the button add clicked-->
            <img
              src="../assets/floppy-disk.png"
              alt="add"
              width="20"
              height="20"
              v-if="update"
              @click="updateVariableCosts()"
              class="img-update"
            />
          </div>
          <div class="cell"></div>
        </div>
        <div
          class="row"
          v-for="variableCost in variableCosts"
          :key="variableCost.variableCostId"
        >
          <div class="cell">{{ variableCost.variableCostId }}</div>
          <div class="cell">{{ variableCost.description }}</div>
          <!-- display value in decimal format -->
          <div class="cell">${{ variableCost.value.toFixed(2) }}</div>
          <div class="cell">{{ variableCost.dateTime }}</div>
          <!-- Delete and edit variableCost -->
          <div class="cell">
            <p class="delete-btn">
              <img
                src="../assets/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
                @click="deleteVariableCost(variableCost.variableCostId)"
              />
            </p>
            <p class="edit-btn">
              <img
                src="../assets/edit.png"
                alt="edit"
                width="20"
                height="20"
                @click="editVariableCosts(variableCost.variableCostId)"
              />
            </p>
          </div>
        </div>
      </div>
    </div>
    <p class="success-msg" v-if="success">{{ success }}</p>
    <p class="error-msg" v-if="error">{{ error }}</p>
    <BaseFooter />
  </div>
</template>

<script>
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
import BaseFooter from "./Base/BaseFooter.vue";
import VariableCostsDataServices from "../services/VariableCostsDataServices";

export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  },
  data() {
    return {
      variableCosts: [],
      description: "",
      value: "",
      date: "",
      update: false,
      success: "",
      error: "",
    };
  },
  methods: {
    getVariableCosts() {
      VariableCostsDataServices.get()
        .then((response) => {
          this.variableCosts = response.data;
        })
        .catch((error) => {
          this.error = "Error getting variable costs -> " + error;
        });
    },
    // Add variableCosts
    addVariableCosts() {
      // clean error msgs
      this.cleanMsgs();
      // check if the user has filled the form
      if (this.description && this.value && this.date) {
        // create the raw material
        var variableCost = {
          description: this.description,
          value: this.value,
          dateTime: this.date,
        };
        VariableCostsDataServices.post(variableCost)
          .then((response) => {
            this.success = "Variable cost added successfully";
            // update the list
            this.getVariableCosts();

            // clean the form
            this.description = "";
            this.value = "";
            this.date = "";

            console.log(response.data);
          })
          .catch((error) => {
            this.error = "Error adding variable cost -> " + error;
          });
      } else {
        this.error = "Please fill all the fields";
        return;
      }
    },

    // Edit variableCosts
    editVariableCosts(id) {
      // clean error msgs
      this.cleanMsgs();
      // set the update to true
      this.update = true;
      // get the variable cost from the array
      var variableCost = this.variableCosts.find(
        (vc) => vc.variableCostId == id
      );

      // save the variable cost id for update
      localStorage.setItem("variableCostId", variableCost.variableCostId);
      console.log(variableCost.variableCostId);

      // add the values to the fields
      this.description = variableCost.description;
      this.value = variableCost.value;
      this.date = variableCost.dateTime;
    },
    updateVariableCosts() {
      console.log(localStorage.getItem("variableCostId"));
      // check if the form is not empty
      if (this.description != "" && this.value != "" && this.date != "") {
        var updated = {
          description: this.description,
          value: this.value,
          dateTime: this.date,
          variableCostId: localStorage.getItem("variableCostId"),
        };

        VariableCostsDataServices.put(
          localStorage.getItem("variableCostId"),
          updated
        )
          .then(() => {
            // add success msg
            this.success = "Variable cost updated successfully";
            // clean the form
            this.description = "";
            this.value = "";
            this.date = "";
            this.update = false;
            // update the list
            this.getVariableCosts();
          })
          .catch((error) => {
            this.error = "Error updating variable cost -> " + error;
          });
      } else {
        this.error = "Please fill all the fields";
      }
    },
    // Delete variableCosts
    deleteVariableCost(id) {
      // clean error msgs
      this.cleanMsgs();
      // delete the variable cost
      VariableCostsDataServices.deleteById(id)
        .then(() => {
          // add success msg
          this.success = "Variable cost deleted successfully";
          // update the list
          this.getVariableCosts();

          // clean the form
          this.description = "";
          this.value = "";
          this.date = "";
          this.update = false;
        })
        .catch((error) => {
          this.error = "Error deleting variable cost -> " + error;
        });
    },
    cleanMsgs() {
      this.error = "";
      this.success = "";
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {      
      this.getVariableCosts();
      this.error = "";
      this.success = "";
      this.update = false;
    } else {
      this.$router.push("/login");
      console.log("not logged in");
    }
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

.delete-btn,
.edit-btn,
.add-btn {
  display: inline-block;
  width: 20px;
  height: 20px;
  margin: 0 5px;
}

/* on hover change image */
.delete-btn:hover,
.edit-btn:hover,
.add-btn:hover {
  cursor: pointer;
}

/* error message */
.error-msg {
  color: red;
  font-size: 1.2rem;
  font-weight: bold;
  text-align: center;
}

/* success message */
.success-msg {
  color: green;
  font-size: 1.2rem;
  font-weight: bold;
  text-align: center;
}
/* update btn */
.img-update {
  cursor: pointer;
}
</style>

