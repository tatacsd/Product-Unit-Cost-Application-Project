<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>Products</h1>
    <div class="container">
      <div class="table">
        <div class="addButton"><a href="" @click="$router.push('/createproduct')">Add</a></div>
        <div class="row-header">
          <div class="cell">ID</div>
          <div class="cell">Code</div>
          <div class="cell">Discription</div>
          <div class="cell">Picture</div>
          <div class="cell">Size</div>
          <div class="cell"></div>
        </div>
        <div class="row">
          <!-- Add product -->
          <div class="cell">
            <p class="add-btn" v-if="!update">
              <img
                src="../assets/plus.png"
                alt="add"
                width="20"
                height="20"
                @click="addProduct()"
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input type="text" v-model="code" placeholder="Code" required />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="discription"
                placeholder="Discription"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="picture"
                placeholder="Picture"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input type="text" v-model="size" placeholder="Size" required />
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
              @click="updateProduct()"
              class="img-update"
            />
          </div>
        </div>
        <div class="row" v-for="product in products" :key="product.id">
          <div class="cell">{{ product.productID }}</div>
          <div class="cell">{{ product.code }}</div>
          <div class="cell">{{ product.discription }}</div>
          <div class="cell">{{ product.picture }}</div>
          <div class="cell">{{ product.size }}</div>
          <!-- Delete and edit product -->
          <div class="cell">
            <p class="delete-btn">
              <img
                src="../assets/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
                @click="deleteProduct(product.productID)"
              />
            </p>
            <p class="edit-btn">
              <img
                src="../assets/edit.png"
                alt="edit"
                width="20"
                height="20"
                @click="editProduct(product.productID)"
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
import ProductDataServices from "../services/ProductDataServices";
export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  },
  data() {
    return {
      products: [],
      code: "",
      discription: "",
      picture: "",
      size: "",
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
    getProducts() {
      ProductDataServices.get()
        .then((response) => {
          // add the response to the data object
          this.products = response.data;
        })
        .catch((error) => {
          this.error = error.response.data.error;
        });
    },
    addProduct() {
      this.cleanMsgs();
      // if fields are not empty add product
      if (
        this.code != "" &&
        this.discription != "" &&
        this.picture != "" &&
        this.size != ""
      ) {
        const product = {
          productID: this.productID,
          code: this.code,
          discription: this.discription,
          picture: this.picture,
          size: this.size,
        };

        ProductDataServices.post(product)
          .then(() => {
            this.getProducts();
            // clear the input fields
            this.code = "";
            this.discription = "";
            this.picture = "";
            this.size = "";

            // set the success message
            this.success = "Product added successfully";
          })
          .catch((error) => {
            this.error = "Product not added ->" + error;
          });
      } else {
        this.error = "Please fill in all the fields";
      }
    },
    deleteProduct(id) {
      this.cleanMsgs();
      ProductDataServices.deleteById(id)
        .then(() => {
          this.getProducts();
          this.success = "Product deleted successfully";
        })
        .catch((error) => {
          this.error = "Product could not be deleted ->" + error;
        });
    },
    editProduct(id) {
      this.cleanMsgs();
      // find a product with the id and add values to the field
      const productUpdate = this.products.find(
        (product) => product.productID == id
      );
      this.code = productUpdate.code;
      this.discription = productUpdate.discription;
      this.picture = productUpdate.picture;
      this.size = productUpdate.size;
      this.update = true;

      // set a local storage to the id of the product
      localStorage.setItem("productID", id);
    },
    updateProduct() {
      this.cleanMsgs();
      // if fields are not empty update product
      if (
        this.code != "" &&
        this.discription != "" &&
        this.picture != "" &&
        this.size != ""
      ) {
        const product = {
          productID: localStorage.getItem("productID"),
          code: this.code,
          discription: this.discription,
          picture: this.picture,
          size: this.size,
        };
        // add to api
        ProductDataServices.put(localStorage.getItem("productID"), product)
          .then(() => {
            this.getProducts();
          })
          .catch((error) => {
            this.error = "Product not updated ->" + error;
          });
        this.update = false;
        // clear the input fields
        this.code = "";
        this.discription = "";
        this.picture = "";
        this.size = "";

        // set message to update
        this.success = "Product updated";
      } else {
        this.error = "Please fill in all the fields";
      }
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      console.log(localStorage.getItem("user"));
      ProductDataServices.get()
        .then((response) => {
          // add the response to the data object
          this.products = response.data;
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
.addButton{
  display: table-caption;
}
.addButton a {
  float: right;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px;
  line-height: 25px;
  border-radius: 4px;
  font-weight: bold;
}

.addButton a.logo {
  font-size: 25px;
  font-weight: bold;
}

.addButton a:hover {
  background-color: #000;
  color: white;
}

.addButton a.active {
  background-color: dodgerblue;
  color: white;
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