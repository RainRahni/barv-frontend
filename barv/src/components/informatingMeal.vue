<template>
  <WelcomeItem>
    <template #icon>
      <DocumentationIcon />
    </template>
    <template #heading>Documentation</template>

    Vue’s
    <a href="https://vuejs.org/" target="_blank" rel="noopener">official documentation</a>
    provides you with all information you need to get started.
  </WelcomeItem>
</template>
<script>
  import {defineComponent} from "vue";
  import WelcomeItem from "@/components/WelcomeItem.vue";

  export default defineComponent({
  components: {WelcomeItem}
})

</script>
