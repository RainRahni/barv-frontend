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
  grid-template-columns: 1fr  1fr;
  width: 250%;
  height:100%;
}
.logina {
  position:fixed;
}




</style>
