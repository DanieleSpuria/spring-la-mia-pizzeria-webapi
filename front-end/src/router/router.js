import { createRouter, createWebHistory } from 'vue-router';
import Home from '../components/Home.vue';
import NewPizza from '../components/NewPizza.vue';
import EditPizza from '../components/EditPizza.vue';

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
      component: NewPizza
    },
    {
      path: '/edit-pizza/:id/:nome/:prezzo/:descrizione/:foto',
      name: 'editPizza',
      component: EditPizza,
      props: true
    }
  ]
})

export {router}