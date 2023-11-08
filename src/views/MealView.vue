<template>
  <main>
    <FoodsTable class="foodTable"/>
  </main>

</template>

<style>

main {
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-bottom: 40%;
}

.foodTable {
  height: 120%;
  width: 130%;
}

</style>
<script setup>
import FoodsTable from "../components/Meal/FoodsTable.vue";

</script>