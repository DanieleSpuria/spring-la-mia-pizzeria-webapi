<template>

  <main class="container d-flex flex-column align-items-center">

    <Header/>
  
    <RouterView />

  </main>
  
</template>




<script setup>

  import Header from './components/partials/Header.vue';

</script>