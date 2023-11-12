<template >
  <main>
    <LoginForm class="logina"/>
  </main>
</template>

<script>

import LoginForm from "@/components/LoginAndSignup/LoginForm.vue";
import FoodItem from "@/components/Meal/FoodItem.vue";

export default {
  name: "LoginView.vue",
  components: {FoodItem, LoginForm}
}
</script>
<style scoped>

main {
  display: grid;
  grid-template-columns: 1fr 1fr;
  grid-template-rows: 2fr 2fr;
  margin-top: 10%;
  margin-left: -3%;
}
.logina {
  grid-column: 2/3;
}


</style>
