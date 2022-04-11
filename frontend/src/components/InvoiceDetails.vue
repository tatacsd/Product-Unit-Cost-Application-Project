<template>
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
                <label># <input type="text" v-model="invoices.invoiceNumber" required></label>
                <label>Supplier # <input type="text" v-model="invoices.supplierID" required></label>
                <label>Date <input type="text" v-model="invoices.invoiceDate" required></label>
                <label>Total Value <input type="text" v-model="invoices.invoiceValue" required></label>
                <input id="submitBtn" type="button" value="Submit">
            </form>
        </div>
        <div class="container">
        <div class="table">
            <div class="row-header">
            <div class="cell">Raw Material ID</div>
            <div class="cell">Value</div>
            <div class="cell">Quantity</div>
            <div class="cell">Total Value</div>
            <div class="cell">Date</div>
            <div class="cell">Notes</div>
            <div class="cell"></div>
            </div>
            <div class="row">
            <!-- Add supplier -->
            <div class="cell">
                <p class="add-btn" v-if="!update">
                <img src="../assets/plus.png" alt="add" width="20" height="20" @click="addSupplier()" /></p>
            </div>
            <div class="cell">
                <p><input type="text" placeholder="Item Value" required /></p>
            </div>
            <div class="cell">
                <p><input type="text" placeholder="Quantity" required /></p>
            </div>
            <div class="cell">
                <p><input type="text" placeholder="Total Value" required /></p>
            </div>
            <div class="cell">
                <p><input type="text" placeholder="Date" required /></p>
            </div>
            <div class="cell">
                <p><input type="text" placeholder="Notes" required /></p>
            </div>
            <div class="cell">
                <!-- button to update the cell will be visible when the button add clicked-->
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
        <p class="success-msg" v-if="success"> {{ success }} </p>
        <p class="error-msg" v-if="error"> {{ error }} </p>
        <BaseFooter />
    </div>
</template>

<script>
import InvoiceDataServices from "../services/InvoiceDataServices";
import BaseFooter from "./Base/BaseFooter.vue"
export default {
    components: {
        BaseFooter
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
        };
    },
    methods:{
        getInvoice(){
            InvoiceDataServices.get()
            .then(response => {
                this.invoices = response.data;
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            })
        },
        getInvoiceById(id){
            InvoiceDataServices.getById(id)
            .then(response => {
                this.invoices = response.data;
                console.log(response.data);
            })
            .catch(error => {
                console.log(error);
            })
        },
        addInvoice(){
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
                        "rawMaterialID": 1,
                        "value": 20.0,
                        "quantity": 2.0,
                        "totalValue": 5000.0,
                        "dateTime": "2022-04-10",
                        "noteString": "no notes",
                        "invoiceDetailsID": 40
                    }
                ]}
            InvoiceDataServices.post(invoice)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        },
        deleteInvoice(){
             InvoiceDataServices.delete()
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        },
        deleteInvoiceById(id){
             InvoiceDataServices.deleteById(id)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        },
        editInvoice(id){
            console.log("inside editInvoice");
            const invoice = {
                invoiceID: this.invoiceID,
                invoiceNumber: this.invoiceNumber,
                supplier: this.supplier,
                invoiceValue: this.invoiceValue,
                invoiceDate: this.invoiceDate,
    
                InvoiceDetails: this.InvoiceDetails,
            }
            InvoiceDataServices.put(id, invoice)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        }
    },
    mounted(){
        this.getInvoiceById(35);
    }    
}
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