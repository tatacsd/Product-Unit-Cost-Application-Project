<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>Invoices</h1>
    <div id="invoiceDetails">
      <form>
        <label
          >#
          <input
            class="form-input"
            type="text"
            placeholder="Invoice Number"
            required
            v-model="newInvoice.invoiceNumber"
        /></label>
        <label
          >Supplier #
          <!-- <input
            class="form-input"
            type="text"
            placeholder="Supplier Number"
            required
            v-model="newInvoice.supplierNumber"
          /> -->
          <select
            class="form-input"
            v-model="newInvoice.supplierNumber"
            required
          >
            <option
              v-for="supplier in suppliers"
              :value="supplier.supplierID"
              :key="supplier.supplierID"
            >
              {{ supplier.firstName }} {{ supplier.lastName }} ({{
                supplier.supplierID
              }})
            </option>
          </select>
        </label>
        <label
          >Date
          <input
            class="form-input"
            type="text"
            placeholder="yyyy-mm-dd"
            required
            v-model="newInvoice.invoiceDate"
        /></label>
        <input id="submitBtn" type="button" value="New" @click="addInvoice" />
      </form>
    </div>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell">Invoice ID</div>
          <div class="cell">Invoice Number</div>
          <div class="cell">Supplier</div>
          <div class="cell">Total Value</div>
          <div class="cell">Date</div>
          <div class="cell"></div>
        </div>
        <div class="row" v-for="invoice in invoices" :key="invoice.id">
          <div class="cell">{{ invoice.invliceId }}</div>
          <div class="cell">{{ invoice.invoiceNumber }}</div>
          <div class="cell">{{ invoice.supplierID }}</div>
          <div class="cell">{{ invoice.invoiceValue }}</div>
          <div class="cell">{{ invoice.invoiceDate }}</div>
          <!-- Delete and edit supplier -->
          <div class="cell">
            <p class="delete-btn">
              <img
                src="../assets/deleteRed.png"
                alt="delete"
                width="20"
                height="20"
                @click="deleteInvoiceById(invoice.invliceId)"
              />
            </p>
            <p class="edit-btn">
              <img
                src="../assets/edit.png"
                alt="edit"
                width="20"
                height="20"
                @click="
                  editInvoice(invoice.invliceId),
                    $router.push('/invoicedetails')
                "
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
import InvoiceDataServices from "../services/InvoiceDataServices";
import SupplierDataServices from "../services/SupplierDataServices";
import BaseFooter from "./Base/BaseFooter.vue";
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
export default {
  components: {
    BaseFooter,
    BaseHeaderDashboard,
  },
  data() {
    return {
      invoices: [],
      error: "",
      success: "",
      update: false,
      newInvoice: {
        invoiceNumber: "",
        supplierNumber: "",
        invoiceDate: "",
      },
      suppliers: [],
    };
  },
  methods: {
    cleanMsgs() {
      this.error = "";
      this.success = "";
    },
    getInvoices() {
      console.log("inside getInvoice");
      InvoiceDataServices.get()
        .then((response) => {
          this.invoices = response.data;
        })
        .catch((error) => {
          this.error = "Erro fetching invoices -> " + error;
        });
    },
    getInvoiceById(id) {
      console.log("inside getInvoiceById");
      InvoiceDataServices.getById(id)
        .then((response) => {
          this.invoices = response.data;
        })
        .catch((error) => {
          this.error = "Erro fetching invoices by ID-> " + error;
        });
    },
    deleteInvoiceById(id) {
      this.cleanMsgs();
      InvoiceDataServices.deleteById(id)
        .then(() => {
          this.getInvoices();
          this.success = "Invoice deleted successfully";
        })
        .catch((error) => {
          this.error = "Invoice could not be deleted ->" + error;
        });
    },
    editInvoice(id) {
      this.cleanMsgs();
      console.log("inside editInvoice");
      // find a supplier with the id and add values to the field
      localStorage.setItem("invoiceDetailsID", id);
    },
    addInvoice() {
      this.cleanMsgs();
      if (
        this.newInvoice.invoiceNumber === "" ||
        this.newInvoice.supplierNumber === "" ||
        this.newInvoice.invoiceDate === ""
      ) {
        this.error = "Please fill all the fields";
        return;
      } else {
        // enable the add fields button
        let newInvoice = {
          invoiceNumber: this.newInvoice.invoiceNumber,
          supplierID: this.newInvoice.supplierNumber,
          invoiceValue: this.newInvoice.invoiceValue,
          invoiceDate: this.newInvoice.invoiceDate,
        };
        InvoiceDataServices.post(newInvoice)
          .then((response) => {
            console.log(response.data);

            // add the message
            this.success = "Invoice added successfully";

            // clear the fields
            this.newInvoice.invoiceNumber = "";
            this.newInvoice.supplierNumber = "";
            this.newInvoice.invoiceDate = "";
            this.getInvoices();
          })
          .catch((error) => {
            this.error = "Error adding invoice";
            console.log(error);
          });
      }
    },
    getSuppliers() {
      SupplierDataServices.get()
        .then((response) => {
          this.suppliers = response.data;
          console.log(this.suppliers);
        })
        .catch((error) => {
          this.error = "Error fetching suppliers -> " + error;
        });
    },
  },
  mounted() {
    this.getInvoices();
    this.getSuppliers();
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
  justify-content: top;
  overflow: auto;
  height: 55vh;
  width: 80vw;
}

.form-input {
  width: 10rem;
}

.table {
  display: table;
  justify-content: top;
}

form {
  display: block;
  padding: 5px;
  border-radius: 5px;
  background: black;
  color: white;
}

form label {
  margin: 0 25px;
}

form input {
  display: inline-block;
  margin: 5px;
  width: 10%;
}

input#submitBtn {
  margin: 0 10px;
}

#invoiceDetails {
  max-width: 61%;
  margin: 0 auto 10px;
  background-color: white;
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
