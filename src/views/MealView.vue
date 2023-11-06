<template>
  <main>
    <FoodsTable class="t"/>
  </main>

</template>

<style>

main {
  display: grid;
  grid-template-rows: 2fr 4fr;
}

@media (min-width: 1024px) {
  .meal {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}

</style>
<script setup>
import FoodsTable from "../components/Meal/FoodsTable.vue";

</script>