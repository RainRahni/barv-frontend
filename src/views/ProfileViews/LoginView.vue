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
}
.logina {
  grid-column: 2/4;
  margin-top: 50%;
  margin-left: 20%;
}


</style>
