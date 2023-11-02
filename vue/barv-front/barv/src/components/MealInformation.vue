<template>
  <div>
    <h1>Welcome to Barv</h1>
    <p>{{ description }}</p>
  </div>
</template>

<script>
export default {
  metaInfo: {
    title: "Barv",
    meta: [
      {
        name: 'description',
        content: 'Page description',
      },
    ],
  },

}
</script>