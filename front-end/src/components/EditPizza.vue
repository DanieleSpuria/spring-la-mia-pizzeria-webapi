<template>

  <form
    class="w-50"
    @submit.prevent="putPizza(pizza.id)"
  >

    <div class="mb-4">
      <label for="nome" class="form-label">Nome</label>
      <input type="text" id="nome" name="nome" class="form-control" v-model="pizza.nome">
    </div>

    <div class="mb-4">
      <label for="prezzo" class="form-label">Prezzo</label>
      <input type="number" id="prezzo" name="prezzo" class="form-control" v-model="pizza.prezzo">
    </div>

    <div class="mb-4">
      <label for="descrizione" class="form-label">Descrizione</label>
      <input type="text" id="descrizione" name="descrizione" class="form-control" v-model="pizza.descrizione">
    </div>

    <div class="mb-4">
      <label for="foto" class="form-label">Foto</label>
      <input type="text" id="foto" name="foto" class="form-control" v-model="pizza.foto">
    </div>

    <input type="submit" value="Invia">

  </form>
  
</template>




<script setup>

  import { defineProps, ref } from 'vue';
  import axios from 'axios';
  import { router } from '../router/router';
  
  const props = defineProps(['id', 'nome', 'prezzo', 'descrizione', 'foto'])

  const apiPizzeria = "http://localhost:8080/api/pizzeria";
  
  const pizza = ref({
    id: props.id,
    nome: props.nome,
    prezzo: props.prezzo,
    descrizione: props.descrizione,
    foto: props.foto
  })

  function putPizza(id) {
    axios.put(apiPizzeria + "/" + id, pizza.value).then(result => {
      console.log(result.data);
      router.push({ name: 'home' });
    }).catch(error => {
      console.log(error);
    })
  }


</script>
