<script setup>
import MainPagePicture from "@/components/MainPagePicture.vue";
</script>

<template>
  <main>
    <MainPagePicture id="trainingPic" imageUrl='src/assets/images/trainingpic.jpg' />
    <MainPagePicture id="foodPic" imageUrl="src/assets/images/foodpic.jpg"/>
  </main>
</template>
<style>
main {
  display: grid;
  grid-template-columns: 1fr 1fr;
}
#trainingPic {
  margin-left: -26.5%;
}
#foodPic {
  margin-right: -26%;
}
</style>
