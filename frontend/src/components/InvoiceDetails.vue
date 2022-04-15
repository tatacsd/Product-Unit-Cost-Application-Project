<template>
  <div>
    <div>
      <!-- Header -->
      <BaseHeaderDashboard />
      <!-- Body -->
      <!-- back to previous page icon -->
      <div class="back-btn">
        <img
          class="back-icon"
          src="../assets/return-button.png"
          alt="back"
          width="20"
          height="20"
          @click="backToPreviousPage()"
        />
      </div>
      <h1>Invoice Details</h1>

      <div id="invoiceDetails">
        <form>
          <label
            ># <input type="text" v-model="invoiceSearch.invliceId" disabled
          /></label>
          <label
            >#
            <input type="text" v-model="invoiceSearch.invoiceNumber" disabled
          /></label>
          <label
            >Supplier #
            <input type="text" v-model="invoiceSearch.supplierID" disabled
          /></label>
          <label
            >Date
            <input type="text" v-model="invoiceSearch.invoiceDate" disabled
          /></label>
          <label
            >Total Value: $
            <input type="text" v-model="invoiceSearch.invoiceValue" disabled
          /></label>
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
            <!-- dropdown with the raw materials type -->
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
                  type="date"
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
              <!-- {{ invoiceDetails.rawMaterialID }} -->
              <p>{{ this.getNameFromId(invoiceDetails.rawMaterialID) }}</p>
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
    </div>
    <p class="success-msg" v-if="success">{{ success }}</p>
    <p class="error-msg" v-if="error">{{ error }}</p>
    <BaseFooter />
  </div>
</template>

<script>
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
import BaseFooter from "./Base/BaseFooter.vue";
import InvoiceDataServices from "../services/InvoiceDataServices";
import RawMaterialDataServices from "../services/RawMaterialDataServices";
export default {
  components: {
    BaseFooter,
    BaseHeaderDashboard,
  },
  data() {
    return {
      addMore: 0,
      rawMaterials: [],
      selected: "",
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
      rawMaterialInput: "",
      quantityInput: "",
      valueInput: "",
      dateTimeinput: "",
      totalValueInput: 0.0,
      notesInput: "",
      fixRendering: true,
    };
  },
  methods: {
    getNameFromId(id) {
      if (id) {
        this.rawMaterials.forEach((rawMaterial) => {
          if (rawMaterial.id == id) {
            return (this.rawHtml = rawMaterial.name);
          }
        });
      }
    },
    getRawMaterial() {
      RawMaterialDataServices.get()
        .then((response) => {
          this.rawMaterials = response.data;

          this.invoices.invoiceDetails.forEach((invoiceDetails) => {
            // find the id inside the rawMaterials array
            this.rawMaterials.forEach((rawMaterial) => {
              if (rawMaterial.id == invoiceDetails.rawMaterialID) {
                this.invoicesRawMaterialsNames.push({
                  id: invoiceDetails.invoiceDetailsID,
                  name: rawMaterial.name,
                  rawID: rawMaterial.id,
                });
              }
            });
          });
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
          this.invoiceSearch.invoiceValue =
            this.invoices.invoiceValue.toFixed(2);
        })
        .catch((error) => {
          console.log(error);
        });
    },
    searchInvoice() {
      console.log("new invoice");
      // get invoice number from the form
      let newInvoiceID = this.invoiceSearch.invliceId;
      console.log(newInvoiceID);
      localStorage.setItem("invoiceDetailsID", newInvoiceID);
      this.getInvoiceById(newInvoiceID);
    },
    addInvoiceDetails() {
      console.log("inside addInvoice");
      // check if all fiels are filled
      if (
        this.selected == "" ||
        this.quantityInput == "" ||
        this.valueInput == "" ||
        this.dateTimeinput == "" ||
        this.notesInput == ""
      ) {
        this.error = "Please fill all the fields";
      } else {
        // create a new invoice details object
        let newInvoiceDetails = {
          // get selected raw material id
          rawMaterialID: this.selected,
          quantity: this.quantityInput,
          value: this.valueInput,
          dateTime: this.dateTimeinput,
          totalValue:
            parseFloat(this.valueInput) * parseFloat(this.quantityInput),
          noteString: this.notesInput,
        };

        localStorage.setItem(
          "newInvoiceDetailsTotalValue",
          newInvoiceDetails.totalValue
        );

        console.log("New invoice details -->", newInvoiceDetails);
        // make a post request to add the new invoice details
        InvoiceDataServices.postInvoiceDetail(
          localStorage.getItem("invoiceDetailsID"),
          newInvoiceDetails
        )
          .then((response) => {
            this.success = "Invoice details added successfully";
            this.getInvoiceById(localStorage.getItem("invoiceDetailsID"));
            console.log(response);

            // update the total value of invoice
            this.updateTotalValue();

            // clear the form
            this.selected = "";
            this.quantityInput = "";
            this.valueInput = "";
            this.totalValueInput = "";
            this.notesInput = "";

            // refresh the page
            this.getInvoiceById(localStorage.getItem("invoiceDetailsID"));

            // add success message
            this.success = "Invoice details added successfully";
            this.error = "";
          })
          .catch((error) => {
            console.log(error);
          });
      }
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
    // method to update the total value of the invoice by summing up the total value of each invoice details
    updateTotalValue() {
      this.getInvoiceById(localStorage.getItem("invoiceDetailsID"));
      // sum the total value of each invoice details
      let totalValueUpdate = 0;
      this.invoices.invoiceDetails.forEach((invoiceDetails) => {
        totalValueUpdate += invoiceDetails.totalValue;
      });

      totalValueUpdate += parseFloat(
        localStorage.getItem("newInvoiceDetailsTotalValue")
      );
      // update the total value of the invoice
      this.invoiceSearch.invoiceValue = totalValueUpdate;
      console.log("total value", totalValueUpdate);

      InvoiceDataServices.put(localStorage.getItem("invoiceDetailsID"), {
        invoiceValue: totalValueUpdate,
      })
        .then((response) => {
          console.log(response);
        })
        .catch((error) => {
          console.log(error);
        });

      this.searchInvoice();

      // reload the page
      location.reload();
    },
    backToPreviousPage() {
      window.history.back();
    },
  },
  mounted() {
    if (localStorage.getItem("user")) {
      this.getInvoiceById(localStorage.getItem("invoiceDetailsID"));
      this.getRawMaterial();
      console.log("here");
    } else {
      this.$router.push("/login");
      console.log("not logged in");
    }
  },
};
</script>

<style scoped>
.back-btn {
  position: absolute;
  top: 8rem;
  left: 7rem;
  padding: 5px;
  cursor: pointer;
}

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
  width: 80vw;
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

select {
  margin: 1vh auto;
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