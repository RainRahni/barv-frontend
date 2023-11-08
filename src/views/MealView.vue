<template>
  <main>
    <FoodsTable class="t"/>
  </main>

</template>

<style>

main {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
.t {
  height: 120%;
  width: 130%;
}


</style>
<script setup>
import FoodsTable from "../components/Meal/FoodsTable.vue";

</script>