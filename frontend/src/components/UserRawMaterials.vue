<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>Raw Materials</h1>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell">ID</div>
          <div class="cell">Name</div>
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
                @click="addRawMaterial()"
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="name"
                placeholder="Raw Material Name"
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
              @click="updateRawMaterial()"
              class="img-update"
            />
          </div>
          <div class="cell"></div>
        </div>
        <div
          class="row"
          v-for="rawMaterial in rawMaterials"
          :key="rawMaterial.id"
        >
          <div class="cell">{{ rawMaterial.id }}</div>
          <div class="cell">{{ rawMaterial.name }}</div>
          <!-- Delete and edit rawMaterial -->
          <div class="cell">
            <p class="delete-btn">
              <img
                src="../assets/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
                @click="deleteRawMaterial(rawMaterial.id)"
              />
            </p>
            <p class="edit-btn">
              <img
                src="../assets/edit.png"
                alt="edit"
                width="20"
                height="20"
                @click="editRawMaterial(rawMaterial.id)"
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
import RawMaterialDataService from "../services/RawMaterialDataServices";

export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  },
  data() {
    return {
      rawMaterials: [],
      name: "",
      update: false,
      success: "",
      error: "",
    };
  },
  methods: {
    getRawMaterials() {
      RawMaterialDataService.get()
        .then((response) => {
          this.rawMaterials = response.data;
        })
        .catch((error) => {
          this.error = "Error getting raw materials -> " + error;
        });
    },
    // Add raw material
    addRawMaterial() {
      this.cleanMsgs();
      // if the field is not empty
      if (this.name != "") {
        // add the raw material
        RawMaterialDataService.post(this.name)
          .then(() => {
            // clear the field
            this.name = "";
            // add success message
            this.success = "Raw Material added successfully";

            // get the raw materials again
            this.getRawMaterials();
          })
          .catch((error) => {
            this.error = "Error adding raw material -> " + error;
          });
      } else {
        this.error = "Please fill the field";
      }
    },
    // Edit raw material
    editRawMaterial(id) {
      this.cleanMsgs();
      // set the update to true
      this.update = true;
      // get the raw material by id and add the values to the field
      var rawMaterialUpdate = this.rawMaterials.find(
        (rawMaterial) => rawMaterial.id === id
      );
      this.name = rawMaterialUpdate.name;

      // set a local storage to the id of the raw material
      localStorage.setItem("rawMaterialId", id);
    },
    // Update raw material
    updateRawMaterial() {
      // if the field is not empty
      if (this.name != "") {
        // update the raw material
        RawMaterialDataService.put(
          localStorage.getItem("rawMaterialId"),
          this.name
        )
          .then(() => {
            // clear the field
            this.name = "";
            // add success message
            this.success = "Raw Material updated successfully";

            // set edit to false
            this.update = false;
            // reload the raw materials
            this.getRawMaterials();
          })
          .catch((error) => {
            this.error = "Something went wrong -> " + error;
          });
      } else {
        this.error = "Please fill the field";
      }
    },
    // delete one raw material
    deleteRawMaterial(id) {
      this.cleanMsgs();
      // delete the raw material
      RawMaterialDataService.deleteById(id)
        .then(() => {
          // add success message
          this.success = "Raw Material deleted successfully";
          // update list
          this.getRawMaterials();
        })
        .catch((error) => {
          this.error = "Raw Material can't be deleted ->" + error;
        });
    },
    // delete all raw materials
    cleanMsgs() {
      this.error = "";
      this.success = "";
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      console.log(localStorage.getItem("user"));
      this.getRawMaterials();
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

