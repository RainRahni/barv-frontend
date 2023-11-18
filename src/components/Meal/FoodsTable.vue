<script>
import {defineComponent} from "vue";
import WelcomeItem from "@/components/WelcomeItem.vue";
import FoodItem from "@/components/Meal/FoodItem.vue";
import DocumentationIcon from '../icons/IconDocumentation.vue'
export default defineComponent({
  components: {WelcomeItem,FoodItem, DocumentationIcon}
})

</script>
<template>
  <div class="containerTwo">
    <div class="row">
      <div class="col card firstCol">Name</div>
      <div class="col card middle">Calories</div>
      <div class="col card middle carbs">Carbohydrates</div>
      <div class="col card middle">Fats</div>
      <div class="col card middle">Protein</div>
      <div class="col card lastCol">Weight</div>
    </div>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>
    <FoodItem class="foodRow" name="Apple" calories="95" carbs="25" fats="0.3" protein="0.5" weight="100"></FoodItem>

  </div>

</template>

<style scoped>

.col {
  width: 75px;
  border: none;
  color: black;
  background-color: #c9c8c8;
}
.firstCol {
  border-radius: 5px 0px 0px 0px;
}
.lastCol {
  border-radius: 0px 5px 0px 0px;
}
.middle {
  border-radius: 0px 0px 0px 0px;
}
.row {
  width: 120%;
}

</style>