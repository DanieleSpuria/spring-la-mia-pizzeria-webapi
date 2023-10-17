import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home.vue';
import Form from '../components/Form.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/form',
      name: 'form',
      component: Form
    }
  ]
})

export {router}