<template >
  <main>
    <SignUpForm class="signup"/>
  </main>
</template>

<script>

import LoginForm from "@/components/LoginAndSignup/LoginForm.vue";
import FoodItem from "@/components/Meal/FoodItem.vue";
import SignUpForm from "@/components/LoginAndSignup/SignUpForm.vue";

export default {
  name: "SignUpView.vue",
  components: {SignUpForm, FoodItem, LoginForm}
}
</script>
<style scoped>

main {
  display: grid;
  grid-template-columns: 1fr  1fr;

}
.signup {
  grid-column: 2/3;
  margin-top: 50%;
  margin-left: 20%;
}




</style>
