<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>Suppliers</h1>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell">ID</div>
          <div class="cell">First Name</div>
          <div class="cell">Last Name</div>
          <div class="cell">Phone</div>
          <div class="cell">Email</div>
          <div class="cell">Address</div>
          <div class="cell"></div>
        </div>
        <div class="row">
          <!-- Add supplier -->
          <div class="cell">
            <p class="add-btn" v-if="!update">
              <img
                src="../assets/plus.png"
                alt="add"
                width="20"
                height="20"
                @click="addSupplier()"
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="firstName"
                placeholder="First Name"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="lastName"
                placeholder="Last Name"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input type="text" v-model="phone" placeholder="Phone" required />
            </p>
          </div>
          <div class="cell">
            <p>
              <input type="text" v-model="email" placeholder="Email" required />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="address"
                placeholder="Address"
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
              @click="updateSupplier()"
              class="img-update"
            />
          </div>
        </div>
        <div class="row" v-for="supplier in suppliers" :key="supplier.id">
          <div class="cell">{{ supplier.supplierID }}</div>
          <div class="cell">{{ supplier.firstName }}</div>
          <div class="cell">{{ supplier.lastName }}</div>
          <div class="cell">{{ supplier.phone }}</div>
          <div class="cell">{{ supplier.email }}</div>
          <div class="cell">{{ supplier.address }}</div>
          <!-- Delete and edit supplier -->
          <div class="cell">
            <p class="delete-btn">
              <img
                src="../assets/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
                @click="deleteSupplier(supplier.supplierID)"
              />
            </p>
            <p class="edit-btn">
              <img
                src="../assets/edit.png"
                alt="edit"
                width="20"
                height="20"
                @click="editSupplier(supplier.supplierID)"
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
import SupplierDataServices from "../services/SupplierDataServices";
export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  },
  data() {
    return {
      suppliers: [],
      firstName: "",
      lastName: "",
      phone: "",
      email: "",
      address: "",
      error: "",
      success: "",
      update: false,
    };
  },
  methods: {
    cleanMsgs() {
      this.error = "";
      this.success = "";
    },
    getSuppliers() {
      SupplierDataServices.get()
        .then((response) => {
          // add the response to the data object
          this.suppliers = response.data;
        })
        .catch((error) => {
          this.error = error.response.data.error;
        });
    },
    addSupplier() {
      this.cleanMsgs();
      // if fields are not empty add supplier
      if (
        this.firstName != "" &&
        this.lastName != "" &&
        this.phone != "" &&
        this.email != "" &&
        this.address != ""
      ) {
        const supplier = {
          supplierID: this.supplierID,
          firstName: this.firstName,
          lastName: this.lastName,
          phone: this.phone,
          email: this.email,
          address: this.address,
        };

        SupplierDataServices.post(supplier)
          .then(() => {
            this.getSuppliers();
            // clear the input fields
            this.firstName = "";
            this.lastName = "";
            this.phone = "";
            this.email = "";
            this.address = "";

            // set the success message
            this.success = "Supplier added successfully";
          })
          .catch((error) => {
            this.error = "Supplier not added ->" + error;
          });
      } else {
        this.error = "Please fill in all the fields";
      }
    },
    deleteSupplier(id) {
      this.cleanMsgs();
      SupplierDataServices.deleteById(id)
        .then(() => {
          this.getSuppliers();
          this.success = "Supplier deleted successfully";
        })
        .catch((error) => {
          this.error = "Supplier could not be deleted ->" + error;
        });
    },
    editSupplier(id) {
      this.cleanMsgs();
      // find a supplier with the id and add values to the field
      const supplierUpdate = this.suppliers.find(
        (supplier) => supplier.supplierID == id
      );
      this.firstName = supplierUpdate.firstName;
      this.lastName = supplierUpdate.lastName;
      this.phone = supplierUpdate.phone;
      this.email = supplierUpdate.email;
      this.address = supplierUpdate.address;
      this.update = true;

      // set a local storage to the id of the supplier
      localStorage.setItem("supplierID", id);
    },
    updateSupplier() {
      this.cleanMsgs();
      // if fields are not empty update supplier
      if (
        this.firstName != "" &&
        this.lastName != "" &&
        this.phone != "" &&
        this.email != "" &&
        this.address != ""
      ) {
        const supplier = {
          supplierID: localStorage.getItem("supplierID"),
          firstName: this.firstName,
          lastName: this.lastName,
          phone: this.phone,
          email: this.email,
          address: this.address,
        };
        // add to api
        SupplierDataServices.put(localStorage.getItem("supplierID"), supplier)
          .then(() => {
            this.getSuppliers();
          })
          .catch((error) => {
            this.error = "Supplier not updated ->" + error;
          });
        this.update = false;
        // clear the input fields
        this.firstName = "";
        this.lastName = "";
        this.phone = "";
        this.email = "";
        this.address = "";

        // set message to update
        this.success = "Supplier updated";
      } else {
        this.error = "Please fill in all the fields";
      }
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      console.log(localStorage.getItem("user"));
      SupplierDataServices.get()
        .then((response) => {
          // add the response to the data object
          this.suppliers = response.data;
        })
        .catch((error) => {
          this.error = error.response.data.error;
        });
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