<template>
  <div id="mainPagePicture">
    <img :src="imageUrl" alt="mainPagePic">
  </div>
</template>
<script>
export default {
  props: {
    imageUrl: {
      type: String,
    },
  },
};
</script>
<style>

img {
  margin-top: -5%;
  width: 100%;
  height: 203%;
}
</style>