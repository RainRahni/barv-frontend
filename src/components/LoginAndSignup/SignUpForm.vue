<template>
  <div class="forms">
    <form>
      <h3 style="color: white">
        Sign Up
      </h3>
      <div class="form-group">
        <input type="firstName" class="form-control" placeholder="First Name"/>
      </div>
      <div class="form-group">
        <input type="lastName" class="form-control" placeholder="Last Name"/>
      </div>
      <div class="form-group">
        <input type="email" class="form-control" placeholder="Email"/>
      </div>
      <div class="form-group">
        <input type="password" class="form-control" placeholder="Password"/>
      </div>      <div class="form-group">
      <input type="confirmPassword" class="form-control" placeholder="Confrim password"/>
      </div>
      <button class="btn" style="margin-top: 5%; background-color: blueviolet; border-color: blueviolet">Sign up</button>
    </form>
  </div>


</template>
<script setup>
</script>
<style>
form {
  position: fixed;
  transform: translate(-50%, -50%);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-left: 8.5%;
  z-index: 1;
}

.form-group {
  margin-top: 5%;
}

.form-container {
  position: ;
  z-index: -1;
  background-color: white;
  width: 20%;
  border-radius: 10px;
  bottom: 20%;
  right: 20%;
  height: 30%;
}

</style>