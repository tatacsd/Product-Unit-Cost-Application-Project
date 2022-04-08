<template>
  <div>
    <!-- Header -->
    <header class="header">
      <a href="#home" class="logo">PCU</a>
      <div class="header-right">
        <a href="" @click="$router.push('/login')">Login</a>
      </div>
    </header>
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
              <input
                type="button"
                v-on:delete="deleteSupplier(supplier.supplierID)"
              />
              <img
                src="../../public/images/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
              />
            </p>
            <p class="edit-btn">
              <input
                type="button"
                v-on:click="editSupplier(supplier.supplierID)"
              />
              <img
                src="../../public/images/edit.png"
                alt="edit"
                width="20"
                height="20"
              />
            </p>
          </div>
        </div>
        <div class="row">
          <!-- Add supplier -->
          <div class="cell">
            <p class="add-btn">
              <input type="button" v-on:click="addSupplier()" />
              <img
                src="../../public/images/plus.png"
                alt="add"
                width="20"
                height="20"
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
        </div>
      </div>
    </div>

    <!-- Footer -->
    <footer class="footer">
      <p>
        Created by
        <a href="https://github.com/tatacsd/PCU" target="_blank">CoffeLovers</a
        >☕
      </p>
      <div class="attributions">
        <p>
          <a href="https://www.flaticon.com/free-icons/bin" title="bin icons"
            >Bin icons created by Kiranshastry - Flaticon</a
          >
          |
        </p>
        <p>
          <a href="https://www.flaticon.com/free-icons/edit" title="edit icons"
            >Edit icons created by Kiranshastry - Flaticon</a
          >
          |
        </p>
        <p>
          <a href="https://www.flaticon.com/free-icons/plus" title="plus icons"
            >Plus icons created by Vectors Market - Flaticon</a
          >
        </p>
      </div>
    </footer>
  </div>
</template>

<script>
import SupplierDataServices from "../services/SupplierDataServices";
export default {
  data() {
    return {
      suppliers: [],
      firstName: "",
      lastName: "",
      phone: "",
      email: "",
      address: "",
    };
  },
  methods: {
    getSuppliers() {
      SupplierDataServices.get()
        .then((response) => {
          this.suppliers = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    addSupplier() {
      console.log("inside addSupplier");
      const supplier = {
        supplierID: this.supplierID,
        firstName: this.firstName,
        lastName: this.lastName,
        phone: this.phone,
        email: this.email,
        address: this.address,
      };

      SupplierDataServices.post(supplier)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    deleteSupplier(id) {
      SupplierDataServices.deleteById(id)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    editSupplier(id) {
      const supplier = {
        supplierID: this.supplierID,
        firstName: this.firstName,
        lastName: this.lastName,
        phone: this.phone,
        email: this.email,
        address: this.address,
      };

      SupplierDataServices.put(id, supplier)
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    SupplierDataServices.get()
      .then((response) => {
        // add the response to the data object
        this.suppliers = response.data;
      })
      .catch((error) => {
        console.log(error);
      });
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
  justify-content: center;
}

.table {
  display: table;
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
.attributions p {
  font-size: 0.6rem;
  display: inline-block;
}
</style>