<script>
import {defineComponent} from "vue";
import WelcomeItem from "@/components/WelcomeItem.vue";

export default defineComponent({
  components: {WelcomeItem}
})

</script>
<template>
  <el-row>
    <el-col class="name">
      <el-card>
        Kiivi
      </el-card>
    </el-col>
    <el-col class="calories">
      <el-card>
        100
      </el-card>
    </el-col>
    <el-col class="carbs">
      <el-card>
        14
      </el-card>
    </el-col>
    <el-col class="protein">
      <el-card>
        5
      </el-card>
    </el-col>
    <el-col class="fats">
      <el-card>
        4
      </el-card>
    </el-col>
    <el-col class="weight">
      <el-card>
        100
      </el-card>
    </el-col>

  </el-row>
</template>

<style scoped>



i {
  display: flex;
  place-items: center;
  place-content: center;
  width: 32px;
  height: 32px;
  color: var(--color-text);
}

h3 {
  font-size: 1.2rem;
  font-weight: 500;
  margin-bottom: 0.4rem;
  color: var(--color-heading);
}
el-row {
  background-size: cover;
  background-color: white;
  color:black;
  font-size: 1.5rem;
}

@media (min-width: 1024px) {
  .item {
    margin-top: 0;
    padding: 0.4rem 0 1rem calc(var(--section-gap) / 2);
  }



  i {
    top: calc(50% - 25px);
    left: -26px;
    position: absolute;
    border: 1px solid var(--color-border);
    background: var(--color-background);
    border-radius: 8px;
    width: 50px;
    height: 50px;
  }

  .item:before {
    content: ' ';
    border-left: 1px solid var(--color-border);
    position: absolute;
    left: 0;
    bottom: calc(50% + 25px);
    height: calc(50% - 25px);
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