<template>
  <main>
    <FoodsTable class="foodTable"/>
  </main>

</template>

<style>

main {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 2fr 2fr;
  margin-top: 9%;
  margin-left: -3%;
}
.foodTable {
  width: 90%;
}

</style>
<script setup>
import FoodsTable from "../components/Meal/FoodsTable.vue";

</script>