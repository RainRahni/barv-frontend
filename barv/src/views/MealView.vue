<template>
  <main>
    <InformatingMeal />
  </main>

</template>

<style>
@media (min-width: 1024px) {
  .food {
    min-height: 100vh;
    display: flex;
    align-items: center;
  }
}
</style>
<script setup>
import MealInformation from "@/App.vue";
import TimeItem from "@/components/TimeItem.vue";
import TheMealInformation from "@/components/informatingMeal.vue";
import InformatingMeal from "@/components/informatingMeal.vue";
</script>