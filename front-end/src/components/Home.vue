<template>

  <div>

    <h1 class="mb-5">Pizzeria</h1>

    <ul class="p-0">
      <li
        v-for="pizza in pizze"
        :key="pizza.id"
        class="my-2"
      >
        <span>{{ pizza.nome }}</span>
        <router-link :to="{ name: 'formPizza'}" class="mx-2">Modifica</router-link>
        <button @click="deletePizza(pizza.id)">Elimina</button>
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
    axios.get(apiPizzeria).then(result => {
      const data = result.data;  
      pizze.value = data;
    })
  }

  function deletePizza(id) {
    axios.delete(apiPizzeria + "/" + id).then(result => {
      getPizze()
    })
  }

  onMounted(() => {
    getPizze()
  }) 

</script>




<style>

</style>