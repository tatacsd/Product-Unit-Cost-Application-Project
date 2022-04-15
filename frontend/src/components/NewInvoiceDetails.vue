<template>
  <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <!-- Body -->
    <h1>New Invoice</h1>
    <div id="invoiceDetails">
      <form>
        <label
          ># <input type="text" placeholder="Invoice Number" required
        /></label>
        <label
          >Supplier # <input type="text" placeholder="Supplier Number" required
        /></label>
        <label
          >Date <input type="text" placeholder="Invoice Date" required
        /></label>
        <label
          >Total Value <input type="text" placeholder="Total Value" required
        /></label>
        <input id="submitBtn" type="button" value="add" disabled />
      </form>
    </div>
    <div class="container">
      <div class="table">
        <div class="row-header">
          <div class="cell"></div>
          <div class="cell">Raw Material</div>
          <div class="cell">Quantity</div>
          <div class="cell">Value</div>
          <div class="cell">Date</div>
          <div class="cell">Total Value</div>
          <div class="cell">Notes</div>
          <div class="cell"></div>
        </div>

        <div class="row">
          <!-- Add new invoice details -->
          <div class="cell">
            <p class="add-btn" v-if="!update">
              <img
                src="../assets/plus.png"
                alt="add"
                width="20"
                height="20"
                @click="addInvoiceDetails()"
              />
            </p>
          </div>
          <select v-model="selected">
            <option
              v-for="rawMaterial in rawMaterials"
              :value="rawMaterial.id"
              :key="rawMaterial.id"
            >
              {{ rawMaterial.name }} ({{ rawMaterial.id }})
            </option>
          </select>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="quantityInput"
                placeholder="Quantity"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="valueInput"
                placeholder="Value"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="dateTimeinput"
                placeholder="yyyy-mm-dd"
                required
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="totalValueInput"
                placeholder="Total Value"
                disabled
              />
            </p>
          </div>
          <div class="cell">
            <p>
              <input
                type="text"
                v-model="notesInput"
                placeholder="notes"
                required
              />
            </p>
          </div>
        </div>

        <!-- For each invoiceDetails add a row -->
        <div
          class="row"
          v-for="invoiceDetails in invoices.invoiceDetails"
          :key="invoiceDetails.id"
        >
          <!-- Add new invoice details -->
          <div class="cell"></div>
          <div class="cell left">
            <!-- {{ this.getNameFromId(invoiceDetails.rawMaterialID) }}
                {{ rawHtml }} -->
            <p>{{ this.getNameFromId(invoiceDetails.rawMaterialID) }}</p>
            <!-- {{ invoiceDetails.rawMaterialID }} -->
            <p>{{ this.rawHtml }}</p>
          </div>
          <div class="cell">{{ invoiceDetails.quantity }}</div>
          <div class="cell">${{ invoiceDetails.value.toFixed(2) }}</div>
          <div class="cell">{{ invoiceDetails.dateTime }}</div>
          <div class="cell">${{ invoiceDetails.totalValue.toFixed(2) }}</div>
          <div class="cell">{{ invoiceDetails.noteString }}</div>
        </div>
      </div>
    </div>
    <p class="success-msg" v-if="success">{{ success }}</p>
    <p class="error-msg" v-if="error">{{ error }}</p>
    <!-- Footer -->
    <BaseFooter />
  </div>
</template>

<script>
import InvoiceDataServices from "../services/InvoiceDataServices";
import RawMaterialDataServices from "../services/RawMaterialDataServices";
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
      invliceId: "",
      invoiceNumber: "",
      supplierID: "",
      invoiceValue: "",
      invoiceDate: "",
      InvoiceDetails: [],
      rawMaterials: [],
      update: false,
      selected: "",
      quantityInput: "",
      valueInput: "",
      dateTimeinput: "",
      totalValueInput: 0.0,
      notesInput: "",
      error: "",
      success: "",
    };
  },
  methods: {
    getRawMaterials() {
      RawMaterialDataServices.get()
        .then((response) => {
          this.rawMaterials = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    submit() {
      // check if the fields are empty
      if (
        this.invoiceNumber == "" ||
        this.supplierID == "" ||
        this.invoiceValue == "" ||
        this.invoiceDate == ""
      ) {
        this.error = "Please fill all the fields";
        return;
      } else {
        // enable the add fields button
        let newInvoice = {
          invoiceNumber: this.invoiceNumber,
          supplierID: this.supplierID,
          invoiceValue: this.invoiceValue,
          invoiceDate: this.invoiceDate,
        };
        InvoiceDataServices.post(newInvoice)
          .then((response) => {
            console.log(response.data);

            // add the message
            this.success = "Invoice added successfully";

            // clear the fields
            this.invoiceNumber = "";
            this.supplierID = "";
            this.invoiceValue = "";
            this.invoiceDate = "";
          })
          .catch((error) => {
            this.error = "Error adding invoice";
            console.log(error);
          });
      }
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
  },
  mounted() {
    this.getRawMaterials();
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