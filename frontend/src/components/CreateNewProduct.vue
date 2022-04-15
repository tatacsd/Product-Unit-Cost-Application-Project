<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <h1>Create Product</h1>
    <div class="container">
      <!-- Description table -->
      <div class="containerDescription">
        <div class="table">
          <div class="column">
            <div class="row">
              <div class="cell">Code</div>
              <div class="cell">
                <input
                  type="text"
                  v-model="description.code"
                  placeholder="Code"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="cell">Description</div>
              <div class="cell">
                <input
                  type="text"
                  v-model="description.discription"
                  placeholder="Description"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="cell">Picture</div>
              <div class="cell">
                <input
                  type="text"
                  v-model="description.picture"
                  placeholder="Picture"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="cell">Size</div>
              <div class="cell">
                <input
                  type="text"
                  v-model="description.size"
                  placeholder="Size"
                  required
                />
              </div>
            </div>
            <div class="row">
              <div class="cell">Net Cost</div>
              <div class="cell">
                <input
                  type="text"
                  v-model="description.netcost"
                  placeholder="0.0"
                  disabled
                />
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="containerMain">
        <!-- Raw materials table -->
        <div class="containerRaw">
          <h4>Raw Materials</h4>
          <div class="table">
            <div class="row-header">
              <div class="cell"></div>
              <div class="cell">Name/ID</div>
              <div class="cell">Quantity</div>
              <div class="cell">Value</div>
              <div class="cell">Total</div>
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
                <!-- TODO: Add dropdown here -->
                <!-- dropdown with the raw materials type -->
                <select v-model="selected">
                  <option
                    v-for="rawMaterial in dropdownRawMaterials"
                    :value="rawMaterial.id"
                    :key="rawMaterial.id"
                  >
                    {{ rawMaterial.name }} ({{ rawMaterial.id }})
                  </option>
                </select>
              </div>
              <div class="cell">
                <p>
                  <input
                    type="number"
                    v-model="quantity"
                    placeholder="Quantity"
                    required
                  />
                </p>
              </div>
              <div class="cell">
                <p>
                  <input
                    type="number"
                    v-model="value"
                    placeholder="Value"
                    required
                  />
                </p>
              </div>
              <div class="cell">
                <p>
                  <input
                    type="number"
                    v-model="total"
                    placeholder="Total"
                    disabled
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
              <div class="cell"></div>
              <div class="cell">{{ rawMaterial.id }}</div>
              <div class="cell">{{ rawMaterial.quantity }}</div>
              <div class="cell">{{ rawMaterial.value }}</div>
              <div class="cell">{{ rawMaterial.total }}</div>
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
        <!-- variable cost table -->
        <div class="containerVariable">
          <h4>Variable Costs</h4>
          <div class="table">
            <div class="row-header">
              <div class="cell"></div>
              <div class="cell">Name/Value</div>
              <div class="cell"></div>
            </div>
            <div class="row">
              <!-- Add Variable costs -->
              <div class="cell">
                <p class="add-btn" v-if="!updateVariableCosts">
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
                <!-- TODO: Add dropdown here -->
                <!-- dropdown with the variable costs type -->
                <select v-model="selectedVariableCosts">
                  <option
                    v-for="variableCosts in dropdownVariableCosts"
                    :value="variableCosts.variableCostId"
                    :key="variableCosts.variableCostId"
                  >
                    {{ variableCosts.description }} ({{
                      variableCosts.value.toFixed(2)
                    }})
                  </option>
                </select>
              </div>
              <div class="cell"></div>
            </div>
            <div
              class="row"
              v-for="item in variableCosts"
              :key="item.variableCostId"
            >
              <div class="cell"></div>
              <div class="cell">
                {{ item.description }} {{ item.value.toFixed(2) }}
              </div>
              <!-- Delete and edit rawMaterial -->
              <div class="cell">
                <p class="delete-btn">
                  <img
                    src="../assets/deleteRed.png"
                    alt="delete"
                    width="20"
                    height="20"
                    @click="deleteVariableCosts(item.variableCostId)"
                  />
                </p>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- Totals table -->
      <div class="totals">
        <div class="row">
          <div class="cell">Total raw materials: $</div>
          <div class="cell">{{ totalRawMaterials.toFixed(2) }}</div>
        </div>
        <div class="row">
          <div class="cell">Total variable costs: $</div>
          <div class="cell">{{ totalVarCosts.toFixed(2) }}</div>
        </div>
      </div>
      <!-- submit button for the api -->
      <div>
        <button class="submit" type="submit" @click="submit">Submit</button>
      </div>
      <p class="success-msg" v-if="success">{{ success }}</p>
      <p class="error-msg" v-if="error">{{ error }}</p>
      <BaseFooter />
    </div>
  </div>
</template>

<script>
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
import BaseFooter from "./Base/BaseFooter.vue";
import RawMaterialDataServices from "../services/RawMaterialDataServices";
import VariableCostsDataServices from "../services/VariableCostsDataServices";
import ProductDataService from "../services/ProductDataServices";

export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  },
  data() {
    return {
      description: {
        code: "",
        discription: "",
        picture: "",
        size: "",
        netcost: "",
      },
      selected: "",
      quantity: "",
      value: "",
      total: "",
      rawMaterials: [],
      variableCosts: [],
      selectedVariableCosts: "",
      valueVariableCosts: "",
      dropdownRawMaterials: [],
      dropdownVariableCosts: [],
      totalRawMaterials: 0,
      totalVarCosts: 0,
      update: false,
      updateVariableCosts: false,
      success: "",
      error: "",
    };
  },
  methods: {
    getRawMaterials() {
      RawMaterialDataServices.get()
        .then((response) => {
          this.dropdownRawMaterials = response.data;
          //   console.log(this.dropdownRawMaterials);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addRawMaterial() {
      // check if the fields are not empty
      if (this.selected != "" && this.quantity != "" && this.value != "") {
        // add the data to the rawMaterials array
        this.rawMaterials.push({
          id: this.selected,
          quantity: this.quantity,
          value: this.value,
          total: this.quantity * this.value,
        });

        this.totalRawMaterials += this.quantity * this.value;
        this.description.netcost = this.totalRawMaterials + this.totalVarCosts;

        // clear fields
        this.selected = "";
        this.quantity = "";
        this.value = "";
        this.total = "";

        console.log(this.rawMaterials);
      } else {
        this.error = "Please fill all the fields";
        return;
      }
    },
    deleteRawMaterial(id) {
      // delete the raw material from the array
      this.rawMaterials = this.rawMaterials.filter((rawMaterial) => {
        return rawMaterial.id != id;
      });
    },
    editRawMaterial(id) {
      // find the raw material in the array
      let rawMaterial = this.rawMaterials.find((rawMaterial) => {
        return rawMaterial.id == id;
      });
      // set the values to the fields
      this.selected = rawMaterial.id;
      this.quantity = rawMaterial.quantity;
      this.value = rawMaterial.value;
      this.total = rawMaterial.total;

      localStorage.setItem("previousRawMaterialTotalValue", rawMaterial.total);
      // set the update to true
      this.update = true;
    },
    updateRawMaterial() {
      console.log(this.selected);
      // find the raw material in the array
      try {
        let rawMaterial = this.rawMaterials.find((rawMaterial) => {
          return rawMaterial.id == this.selected;
        });
        // update the raw material
        rawMaterial.id = this.selected;
        rawMaterial.quantity = this.quantity;
        rawMaterial.value = this.value;
        rawMaterial.total = this.quantity * this.value;
        // set the update to false
        this.update = false;

        // update total value
        this.totalRawMaterials -= localStorage.getItem(
          "previousRawMaterialTotalValue"
        );
        this.totalRawMaterials += rawMaterial.total;
        this.description.netcost = this.totalRawMaterials + this.totalVarCosts;

        // clear the fields
        this.selected = "";
        this.quantity = "";
        this.value = "";
        this.total = "";
      } catch (error) {
        this.error = "You can't modify the id";
      }
    },

    // methods for variable costs
    getVariableCosts() {
      VariableCostsDataServices.get()
        .then((response) => {
          this.dropdownVariableCosts = response.data;
          console.log(this.dropdownVariableCosts);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addVariableCosts() {
      // check if the fields are not empty
      if (this.selectedVariableCosts != "") {
        console.log(this.selectedVariableCosts);
        // find in the dropdown the selected variable costs
        let variableCosts = this.dropdownVariableCosts.find((variableCosts) => {
          return variableCosts.variableCostId == this.selectedVariableCosts;
        });
        this.variableCosts.push(variableCosts);

        this.totalVarCosts += variableCosts.value;
        this.description.netcost = this.totalRawMaterials + this.totalVarCosts;

        // clear fields
        this.selectedVariableCosts = "";
        this.valueVariableCosts = "";

        console.log(this.variableCosts);
      } else {
        this.error = "Please fill all the fields";
        return;
      }
    },
    deleteVariableCosts(id) {
      // delete the variable costs from the array
      this.variableCosts = this.variableCosts.filter((variableCosts) => {
        this.totalVarCosts -= variableCosts.value;
        return variableCosts.variableCostId != id;
      });
    },
    submit() {
      console.log(this.rawMaterials);
      console.log(this.variableCosts);
      // check if the fields are not empty
      if (
        this.description.code != "" &&
        this.description.discription != "" &&
        this.description.picture != "" &&
        this.description.size != "" &&
        this.description.netcost != ""
      ) {
        // creat a product json
        let product = {
          productID: Math.floor(Math.random() * 100),
          code: this.description.code,
          discription: this.description.discription,
          picture: this.description.picture,
          size: this.description.size,
          totalMaterialCost: this.totalRawMaterials,
          variableCosts: JSON.parse(JSON.stringify(this.variableCosts)),
          rawMaterials: JSON.parse(JSON.stringify(this.rawMaterials)),
          netCost: this.description.netcost,
          totalvariableCosts: this.totalVarCosts,
        };

        console.log(product);
        // send the product to the server
        ProductDataService.post(product)
          .then((response) => {
            console.log(response);
            this.success = "Product added successfully";
            this.$router.push("/products");
          })
          .catch((error) => {
            this.error = "Error adding product -> " + error;
          });
      } else {
        this.error = "Please fill all the fields";
        return;
      }
    },
  },

  mounted() {
    this.getRawMaterials();
    this.getVariableCosts();
  },
};
</script>

<style scoped>
h1 {
  text-align: center;
}

.containerMain {
  margin-top: 10px;
  display: flex;
}

.containerRaw {
  overflow: auto;
  height: 30vh;
  width: 65vw;
  float: left;
}

.containerVariable {
  overflow: auto;
  height: 30vh;
  width: 20vw;
  float: right;
}

h4 {
  text-align: center;
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

.submit {
  float: right;
  color: black;
  text-align: center;
  padding: 12px;
  font-size: 18px;
  line-height: 25px;
  border-radius: 4px;
  font-weight: bold;
  margin-right: 3rem;
  cursor: pointer;
}

.submit:hover {
  background-color: black;
  color: white;
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

/* for small screens */
@media screen and (max-width: 600px) {
  .containerMain {
    /* smaller to fit */
    flex-direction: column;
  }
}
</style>
