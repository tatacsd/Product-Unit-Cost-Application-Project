<template>
    <div>
    <!-- Header -->
    <BaseHeaderDashboard />
    <h1>Create Product</h1>
    <div class="container">
        <div class="table">
        <div class="column">
          <div class="row">
              <div class="cell">Code</div>
              <div class="cell"><input type="text" v-model="code" placeholder="Code" required /></div>
              </div>
          <div class="row">
              <div class="cell">Description</div>
              <div class="cell">
              <input
                type="text"
                v-model="discription"
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
                v-model="picture"
                placeholder="Picture"
                required
              />
          </div>
              </div>
          <div class="row">
              <div class="cell">Size</div>
              <div class="cell">
              <input type="text" v-model="size" placeholder="Size" required />
          </div>
              </div>
          <div class="row">
              <div class="cell">Net Cost</div>
              <div class="cell"></div>
          </div>
        </div>
        
        
    </div>
    <div class="secondContainer">
    <div class="secondTable">
        <div class="caption">Raw materials</div>
        <div class="secondRow-header">
          <div class="secondCell">ID</div>
          <div class="secondCell">Cost</div>
          <div class="secondCell">Quantity</div>
          <div class="secondCell">Total</div>
        </div>
        <div class="row" >
          <div class="cell">dropdown</div>
          <div class="cell">from dropdown</div>
          <div class="cell"><input type="number"></div>
          <div class="cell">{{this.totalRaw}}</div>
          <div class="cell">
          </div>
        </div>
    </div>
        <div class="secondTable">
        <div class="caption">Variable costs</div>
        <div class="secondRow-header">
          <div class="secondCell">ID</div>
          <div class="secondCell">Name</div>
          <div class="secondCell">Cost</div>
        </div>
        <div class="row" >
          <div class="cell">dropdown</div>
          <div class="cell">from dropdown</div>
          <div class="cell">from dropdown</div>
          <div class="cell">
        </div>
        </div>
        </div>
    </div>
    <div class="totals">
        <div class="row">
            <div class="cell">Total raw materials</div>
            <div class="cell">{{totalRawMaterials}}</div>
        </div>
        <div class="row">
            <div class="cell">Total variable costs</div>
            <div class="cell">{{totalVarCosts}}</div>
        </div>
    </div>
    
    






    
    <BaseFooter />
</div>
</div>
</template>

<script>
import BaseHeaderDashboard from "./Base/BaseHeaderDashboard.vue";
import BaseFooter from "./Base/BaseFooter.vue";

export default {
  components: {
    BaseHeaderDashboard,
    BaseFooter,
  }};


</script>

<style scoped>

.caption{
    display:table-caption;
    text-align: center;
    
}

.secondContainer{
    display:flex;
}
.table{
    display: table;
}
.secondTable{
    display: table;
}

.row{
    display: table-row;
}
.secondRow-header{
    display:table-row;
    background-color: black;
    color: white;
}

.cell{
    display: table-cell;
    padding: 6px 12px;
}
.secondCell{
    display: table-cell;
    padding: 5px 4vw;
}
.totals{
    display: table;
    float: left;
    margin-top: 2vh;
}

</style>
