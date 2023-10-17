import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home.vue';
import FormPizza from '../components/FormPizza.vue';

const router = createRouter({
  history: createWebHistory(),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/new-pizza',
      name: 'newPizza',
      component: FormPizza
    }
  ]
})

export {router}