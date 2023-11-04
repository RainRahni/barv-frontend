<template>
  <main>
    <FoodsTable />
  </main>

</template>

<style>
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