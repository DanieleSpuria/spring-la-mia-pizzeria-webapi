<template>

  <form
    class="d-flex flex-column align-items-center border border-dark rounded p-3"
    @submit.prevent="postPizza()"
  >

    <div class="mb-4">
      <label for="nome" class="form-label">Nome</label>
      <input type="text" id="nome" name="nome" class="form-control" v-model="newPizza.nome">
    </div>

    <div class="mb-4">
      <label for="prezzo" class="form-label">Prezzo</label>
      <input type="number" step="0.01" id="prezzo" name="prezzo" class="form-control" v-model="newPizza.prezzo">
    </div>

    <div class="mb-4">
      <label for="descrizione" class="form-label">Descrizione</label>
      <input type="text" id="descrizione" name="descrizione" class="form-control" v-model="newPizza.descrizione">
    </div>

    <div class="mb-4">
      <label for="foto" class="form-label">Foto</label>
      <input type="text" id="foto" name="foto" class="form-control" v-model="newPizza.foto">
    </div>

    <input type="submit" value="Invia" class="btn btn-primary">

  </form>
  
</template>




<script setup>

  import { defineProps, ref } from 'vue';
  import axios from 'axios';
  import { router } from '../router/router';

  const props = defineProps(['nome', 'prezzo', 'descrizione', 'foto'])

  const apiPizzeria = "http://localhost:8080/api/pizzeria";
  
  const emptyPizza = {
    nome: '',
    prezzo: '',
    descrizione: '',
    foto: ''
  }
  const newPizza = ref({...emptyPizza});

  function postPizza() {
    axios.post(apiPizzeria, newPizza.value).then(result => {
      console.log(result.data);
      router.push({ name: 'home' });
    }).catch(error => {
      console.log(error);
    })
  }

</script>
