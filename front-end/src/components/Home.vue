<template>

  <div>

    <h1 class="mb-5">Pizzeria</h1>

    <ul class="p-0">
      <li
        v-for="pizza in pizze"
        :key="pizza.id"
        class="my-2"
      >
        {{ pizza.nome }}
      </li>
    </ul>

  </div>

</template>




<script setup>

  import { ref, onMounted } from 'vue';
  import axios from 'axios'

  const apiPizzeria = "http://localhost:8080/api/pizzeria";
  const pizze = ref(null);

  function getPizze() {
    axios.get(apiPizzeria).then(res => {
            const data = res.data;
            pizze.value = data;
          })
  }

  onMounted(() => {
    getPizze()
  }) 

</script>




<style>

</style>