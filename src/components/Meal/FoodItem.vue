<script>
export default {
  props: {
    name: String,
    calories: Number,
    carbs: Number,
    fats: Number,
    protein: Number,
    weight: Number
  }
}


</script>
<template>
  <div class="containerOne">
    <div class="row">
      <div class="col card lastCol">{{ name }}</div>
      <div class="col card middle">{{ calories }}</div>
      <div class="col card middle">{{ carbs }}</div>
      <div class="col card middle">{{ fats }}</div>
      <div class="col card middle">{{ protein }}</div>
      <div class="col card firstCol">{{ weight }}</div>
    </div>
  </div>
</template>

<style scoped>
.col {
  width: 75px;
  border: none;
  color: black;
  border-radius: 0px 0px 0px 0px;
}
.row {
  display: flex;
  width: 120%;
}
@media (min-width: 1024px) {
  .item {
    margin-top: 0;
    padding: 0.4rem 0 1rem calc(var(--section-gap) / 2);
  }

  .item:after {
    content: ' ';
    border-left: 1px solid var(--color-border);
    position: absolute;
    left: 0;
    top: calc(50% + 25px);
    height: calc(50% - 25px);
  }

  .item:first-of-type:before {
    display: none;
  }

  .item:last-of-type:after {
    display: none;
  }
}
</style>