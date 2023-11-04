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
  <FoodItem>
    <template #icon>
      <DocumentationIcon />
    </template>
    <template #name>Kiivi</template>
    <template #calories>Kiivi</template>
    <template #carbs>Kiivi</template>
    <template #protein>Kiivi</template>
    <template #fat>Kiivi</template>
    <template #weight>Kiivi</template>
  </FoodItem>

</template>

