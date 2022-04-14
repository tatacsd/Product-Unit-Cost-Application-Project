<template>
<div>
    <div>
        <!-- Header -->
         <header class="header">
            <a href="#home" class="logo">PCU</a>
            <div class="header-right">
                <a href="" @click="$router.push('/dashboard')">Dashboard</a>
            </div>
        </header>
        <!-- Body -->
        <h1>Invoice Details</h1>
        <div id="invoiceDetails">
            <form>
                <!-- selectbox of invliceId -->
                <label># <input type="text" v-model="invoiceSearch.invliceId" required></label>
                <label># <input type="text" v-model="invoiceSearch.invoiceNumber" disabled></label>
                <label>Supplier # <input type="text" v-model="invoiceSearch.supplierID" disabled></label>
                <label>Date <input type="text" v-model="invoiceSearch.invoiceDate" disabled></label>
                <label>Total Value <input type="text" v-model="invoiceSearch.invoiceValue" disabled></label>
                <input id="submitBtn" type="button" value="Submit" @click="searchInvoice()">
            </form>
        </div>
        <div class="container">
        <div class="table">
            <div class="row-header">
            <div class="cell"></div>
            <div class="cell">Raw Material</div>
            <div class="cell">Quantity</div>
            <div class="cell">Value</div>
            <div class="cell">Total Value</div>
            <div class="cell">Notes</div>
            </div>
            <!-- For each invoiceDetails add a row -->
            <div class="row" v-for="invoiceDetails in invoices.invoiceDetails" :key="invoiceDetails.id">
            <!-- Add new invoice details -->
                <div class="cell"></div>
                <div class="cell left">
                    {{getNameFromId(invoiceDetails.rawMaterialID)}}
                    {{rawHtml}}
                 
                </div>
                <div class="cell">{{invoiceDetails.quantity}}</div>
                <div class="cell">${{invoiceDetails.value.toFixed(2)}}</div>
                <div class="cell">${{invoiceDetails.totalValue.toFixed(2)}}</div>
                <div class="cell">{{invoiceDetails.noteString}}</div>
                <!-- Delete and edit supplier -->
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
import RawMaterialDataServices from "../services/RawMaterialDataServices";
import BaseFooter from "./Base/BaseFooter.vue";
export default {
  components: {
    BaseFooter,
  },
  data() {
    return {
      addMore: 0,
      rawMaterials: [],
      invoicesRawMaterialsNames: [],
      invoices: [],
      update: false,
      success: "",
      error: "",
      rawHtml: "",
      invoiceSearch: {
        invliceId: "",
        invoiceNumber: "",
        supplierID: "",
        invoiceDate: "",
        invoiceValue: "",
      },
    };
  },
  methods: {
    getNameFromId(id) {
      this.rawMaterials.forEach((rawMaterial) => {
        if (rawMaterial.id == id) {
          this.rawHtml = rawMaterial.name;
        }
      });
    },
    getRawMaterial() {
      RawMaterialDataServices.get()
        .then((response) => {
          this.rawMaterials = response.data;

          //
        })
        .catch((error) => {
          console.log(error);
        });
    },
    getInvoiceById(id) {
      InvoiceDataServices.getById(id)
        .then((response) => {
          this.invoices = response.data;
          // invliceId
          this.invoiceSearch.invliceId = this.invoices.invliceId;
          this.invoiceSearch.invoiceNumber = this.invoices.invoiceNumber;
          this.invoiceSearch.supplierID = this.invoices.supplierID;
          this.invoiceSearch.invoiceDate = this.invoices.invoiceDate;
          this.invoiceSearch.invoiceValue = this.invoices.invoiceValue;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchInvoice() {
      console.log("new invoice");
      // get invoice number
    },
  },
  getRawMaterial() {
    RawMaterialDataServices.get()
      .then((response) => {
        this.rawMaterials = response.data;

        //
      })
      .catch((error) => {
        console.log(error);
      });
  },
  getInvoiceById(id) {
    InvoiceDataServices.getById(id)
      .then((response) => {
        this.invoices = response.data;
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  addInvoice() {
    console.log("inside addInvoice");
    const invoice = {
      invliceId: this.invliceId,
      invoiceNumber: this.invoiceNumber,
      supplierID: this.supplierID,
      invoiceValue: this.invoiceValue,
      invoiceDate: this.invoiceDate,
      invoiceDetails: [
        {
          rawMaterialID: 1,
          value: 20.0,
          quantity: 2.0,
          totalValue: 5000.0,
          dateTime: "2022-04-10",
          noteString: "no notes",
          invoiceDetailsID: 40,
        },
      ],
    };
    InvoiceDataServices.post(invoice)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteInvoice() {
    InvoiceDataServices.delete()
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteInvoiceById(id) {
    InvoiceDataServices.deleteById(id)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  editInvoice(id) {
    console.log("inside editInvoice");
    const invoice = {
      invoiceID: this.invoiceID,
      invoiceNumber: this.invoiceNumber,
      supplier: this.supplier,
      invoiceValue: this.invoiceValue,
      invoiceDate: this.invoiceDate,

      InvoiceDetails: this.InvoiceDetails,
    };
    InvoiceDataServices.put(id, invoice)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  getInvoiceById(id) {
    InvoiceDataServices.getById(id)
      .then((response) => {
        this.invoices = response.data;
        console.log(response.data);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  addInvoice() {
    console.log("inside addInvoice");
    const invoice = {
      // invoiceID: this.invoiceID,
      // invoiceNumber: this.invoiceNumber,
      // supplier: this.supplier,
      // invoiceValue: this.invoiceValue,
      // invoiceDate: this.invoiceDate,

      // InvoiceDetails: this.InvoiceDetails,
      // rawMaterialID: this.rawMaterialID,
      // value: this.value,
      // quantity: this.quantity,
      // totalValue: this.totalValue,
      // dateTime: this.dateTime,
      // noteString: this.noteString

      invliceId: this.invliceId,
      invoiceNumber: this.invoiceNumber,
      supplierID: this.supplierID,
      invoiceValue: this.invoiceValue,
      invoiceDate: this.invoiceDate,
      invoiceDetails: [
        {
          rawMaterialID: 1,
          value: 20.0,
          quantity: 2.0,
          totalValue: 5000.0,
          dateTime: "2022-04-10",
          noteString: "no notes",
          invoiceDetailsID: 40,
        },
      ],
    };
    InvoiceDataServices.post(invoice)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteInvoice() {
    InvoiceDataServices.delete()
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  deleteInvoiceById(id) {
    InvoiceDataServices.deleteById(id)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  editInvoice(id) {
    console.log("inside editInvoice");
    const invoice = {
      invoiceID: this.invoiceID,
      invoiceNumber: this.invoiceNumber,
      supplier: this.supplier,
      invoiceValue: this.invoiceValue,
      invoiceDate: this.invoiceDate,

      InvoiceDetails: this.InvoiceDetails,
    };
    InvoiceDataServices.put(id, invoice)
      .then((response) => {
        console.log(response);
      })
      .catch((error) => {
        console.log(error);
      });
  },
  mounted() {
    this.getInvoiceById(35);
    this.getRawMaterial();
    if (localStorage.getItem("user")) {
      console.log(localStorage.getItem("user"));
      this.getInvoiceById(35);
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
  justify-content: top;
  overflow: auto;
  height: 55vh;
  width: 80vw;
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