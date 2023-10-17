<template>

  <div>

    <h1 class="text-center border border-dark rounded">Pizzeria</h1>

    <div class="text-center d-flex flex-column border border-dark rounded mb-2 p-2">
      <label>Cerca una pizza:</label>
      <input type="text" v-model="search" @keyup="searchPizze()" class="form-control">
    </div>

    <ul class="border border-dark rounded p-3">
      <li
        v-for="pizza in pizze"
        :key="pizza.id"
        class="d-flex justify-content-between align-items-center my-2"
      >
        <span>{{ pizza.nome }}</span>

        <div>
          <router-link
            :to="{ name: 'editPizza', params: {id: pizza.id, nome: pizza.nome, prezzo: pizza.prezzo, descrizione: pizza.descrizione, foto: pizza.foto}}"
            class="a-ds btn btn-secondary mx-2"
          >Modifica</router-link>
          <button @click="deletePizza(pizza.id)" class="btn btn-danger">Elimina</button>
        </div>
      </li>
    </ul>

  </div>

</template>




<script setup>

  import { ref, onMounted, computed } from 'vue';
  import axios from 'axios'

  const apiPizzeria = "http://localhost:8080/api/pizzeria";
  const pizze = ref(null);
  const search = ref('');

  function getPizze() {
    axios.get(apiPizzeria).then(result => {
      const data = result.data;  
      pizze.value = data;
    })
  }

  function searchPizze() {
    axios.get(apiPizzeria + "?search=" + search.value).then(result => {
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