<template>
    <div>
        <!-- Header -->
         <header class="header">
            <a href="#home" class="logo">PCU</a>
            <div class="header-right">
                <a href="" @click="$router.push('/invoices')">Invoice Details</a>
            </div>
        </header>
        <!-- Body -->
        <h1>Invoice Details</h1>
        <div class="container">
            <div id="invoiceDetails">
                <!-- <div class="table" id="invoiceDetTable">
                    <div id="headerColumn">
                        <div class="invoiceHeadCell">#</div>
                        <div class="invoiceHeadCell">Supplier ID</div>
                        <div class="invoiceHeadCell">Date</div>
                        <div class="invoiceHeadCell">Value</div>
                    </div>
                    <div id="infoColumn">
                        <div class="invoiceInfoCell"><p><input type="text" placeholder="123456" required></p></div>
                        <div class="invoiceInfoCell"><p><input type="text" placeholder="Supplier" required></p></div>
                        <div class="invoiceInfoCell"><p><input type="text" placeholder="yyyy/mm/dd" required></p></div>
                        <div class="invoiceInfoCell"><p><input type="text" placeholder="$ 9,999.99" required></p></div>
                    </div> -->
                <form>
                    <label># <input type="text" placeholder="123456" required></label>
                    <label>Supplier ID <input type="text" placeholder="Supplier" required></label>
                    <label>Date <input type="text" placeholder="yyyy/mm/dd" required></label>
                    <label>Total Value <input type="text" placeholder="$ 9,999.99" required></label>
                    <input id="submitBtn" type="button" value="Submit">
                </form>
                <!-- </div> -->
            </div>
        </div>
        <div class="row-header">
            <div class="headerCell" id="firstColumn">ID</div>
            <div class="headerCell">Raw Material ID</div>
            <div class="headerCell">Value</div>
            <div class="headerCell">Quantity</div>
            <div class="headerCell">Total Value</div>
            <div class="headerCell">Notes</div>
        </div>
        <div class="container">
            <div class="table">
                <div class="row" v-for="InvoiceDetail in invoiceDetails" :key="InvoiceDetail.id">
                    <div class="cell">{{ InvoiceDetail.InvoiceDetailsID }}</div>
                    <div class="cell">{{ InvoiceDetail.rawMaterialID }}</div>
                    <div class="cell">{{ InvoiceDetail.value }}</div>
                    <div class="cell">{{ InvoiceDetail.quantity }}</div>
                    <div class="cell">{{ InvoiceDetail.totalValue }}</div>
                    <!-- <div class="cell">{{ InvoiceDetail.dateTime }}</div> -->
                    <div class="cell">{{ InvoiceDetail.noteString }}</div>
                    <!-- Delete and edit supplier -->
                    <div class="cell">
                        <p class="delete-btn">
                            <input type="button" v-on:click="deleteInvoice(invoiceDetails.InvoiceDetailsID)" value="del">
                            <!-- <img src="../../public/images/deleteRed.png" alt="delete" width="20" height="20"> -->
                        </p>
                        <p class="edit-btn">
                            <input type="button" v-on:click="editInvoice(invoiceDetails.InvoiceDetailsID)" value="edit">
                            <!-- <img src="../../public/images/edit.png" alt="edit" width="20" height="20"> -->
                        </p>
                    </div>
                </div>
                <div class="row" >
                    <!-- Add invoice -->
                    <div class="cell">
                        <p class="add-btn">
                            <input type="button" v-on:click="addInvoice()" value="add">
                            <!-- <img src="../../public/images/plus.png" alt="add" width="20" height="20"> -->
                        </p>
                    </div> 
                    <div class="cell"><p><input type="text" v-model="invoiceDetails.rawMaterialID" placeholder="Raw Material ID" required></p></div>
                    <div class="cell"><p><input type="text" v-model="invoiceDetails.value" placeholder="Value" required></p></div>
                    <div class="cell"><p><input type="text" v-model="invoiceDetails.quantity" placeholder="Quantity" required></p></div>
                    <div class="cell"><p><input type="text" v-model="invoiceDetails.totalValue" placeholder="Total Value" required></p></div>
                    <!-- <div class="cell"><p><input type="text" v-model="date" placeholder="Date" required></p></div> -->
                    <div class="cell"><p><input type="text" v-model="invoiceDetails.noteString" placeholder="Notes" required></p></div>
                    <div class="cell"></div>
                </div>
            </div>
        </div>

        <!-- Footer -->
         <footer class="footer">
            <p>Created by <a href="https://github.com/tatacsd/PCU" target="_blank">CoffeLovers</a>☕</p>
            <div class="attributions">
                <p><a href="https://www.flaticon.com/free-icons/bin" title="bin icons">Bin icons created by Kiranshastry - Flaticon</a> | </p>
                <p><a href="https://www.flaticon.com/free-icons/edit" title="edit icons">Edit icons created by Kiranshastry - Flaticon</a> |</p>
                <p><a href="https://www.flaticon.com/free-icons/plus" title="plus icons">Plus icons created by Vectors Market - Flaticon</a></p>
            </div>
        </footer>
    </div>
</template>

<script>
import https from "../http-common";
export default {
    data() {
        return {
            invoices: [],
            invoiceDetails: {
                rawMaterialID: "",
                value: "",
                quantity: "",
                totalValue: "",
                
                noteString: ""
            }
        };
    },
    methods:{
        getInvoice(){
            https.get('/invoices')
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
                rawMaterialID: this.rawMaterialID,

                value: this.value,
                quantity: this.quantity,
                totalValue: this.totalValue,
                dateTime: this.dateTime,
                noteString: this.noteString
            }
            https.post('/invoices', invoice)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        },
        deleteInvoice(id){
            https.delete("/invoices/", id)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        },
        editInvoice(id){
            const invoice = {
                rawMaterialID: "111",
                value: "123",
                quantity: "1",
                totalValue: "123",
                // dateTime: this.dateTime,
                noteString: "abc"
            }
            https.put('/invoices/' + id, invoice)
            .then(response => {
                console.log(response);
            })
            .catch(error => {
                console.log(error);
            })
        }
    },
    mounted(){
        https.get('/invoices')
        .then(response => {
            // add the response to the data object
            this.invoiceDetails = response.data;
        })
        .catch(error => {
            console.log(error);
        })
    }    
}
</script>

<style scoped>
html{
    overflow: auto;
}
body {
  background: #f2f2f2;
  font-family: 'Open Sans', sans-serif;
}
.header {
  overflow: hidden;
  background-color: #ffdc14;
  padding: 20px 10px;
}
.header a {
  float: left;
  color: black;
  text-align: center;
  padding: 12px;
  text-decoration: none;
  font-size: 18px; 
  line-height: 25px;
  border-radius: 4px;
  font-weight: bold;
}
.header a.logo {
  font-size: 25px;
  font-weight: bold;
}
.header a:hover {
  background-color: #000;
  color: white;
}
.header a.active {
  background-color: dodgerblue;
  color: white;
}
.header-right {
  float: right;
}
h1 {
    text-align: center;
    padding: 40px;
}
.container {
  /* in the center vertically and hotizontally of the page*/
    margin: auto;
    width: 70%;
    overflow: auto;
    max-height: 21rem;
    border-radius: 5px;
}
form {
    display: block;
    padding: 5px;
    border-radius: 5px;
    background: black;
    color: white;
}
form label {
    margin: 0 8px;
    width: 22%;
}
form input {
    display: inline-block;
    margin: 5px;
}
input#submitBtn {
    margin: 0 10px;
}
#invoiceDetails {
    width: 95%;
    margin: 0 auto 10px;
    background-color: white;
}
/* #invoiceDetTable {
    display: table;
    width: 100%;
    height: 100%;
    border: 1px solid black;
    border-radius: 5px;
}
#headerColumn {
    height: 100%;
    width: 20%;
    padding: 0 5px;
    float: left;
    background-color: black;
    color: white;
    text-align: left;
}
#infoColumn {
    height: 100%;
    width: 55%;
    float: right;
    color: black;
    text-align: center;
}
.invoiceHeadCell {
    display: inline-block;
}
.invoiceInfoCell {
    display: inline-block;
    height: auto;
    line-height: 1.87em;
    background-color: #f2f2f2;
}
.invoiceInfoCell p input {
    width: 95%;
} */
.table{
  display: table;
  overflow: auto;
  margin: auto;
  width: 100%;
}
.row-header{
    display: block;
    background-color: black;
    color: white;
    margin: auto;
    width: 70%;
}
#firstColumn {
    width: 7%;
}
.row {
  display: table-row;
  background: #f6f6f6;
  text-align: center;
  overflow: auto;
  max-height: 10px;
}
.headerCell {
    display: inline-block;
    width: 15%;
    margin: 5px;
    padding: 0 0 0 40px;
    text-align: center;
}
.cell {
  padding: 6px 12px;
  display: table-cell;
  width: 100%;
}
.delete-btn,
.edit-btn,
.add-btn {
    display: inline-block;
    width: 20px;
    height: 20px;
    margin: 5px;
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

/* Footer */
.footer {
  background: #ffdc14;
  color: #000;
  font-weight: bold;
  text-align: center;
  padding: 20px;
  position: absolute;
  left: 0;
  bottom: 0;
  width: 100%;
}
.footer a {
  text-decoration: none;
  color: #000;
  font-weight: bold;
}
</style>