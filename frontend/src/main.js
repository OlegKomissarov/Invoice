import style from './assets/sass/style.scss'
import Vue from 'vue'
import App from './App.vue'
import router from './router/Index'
import store from './store/store'
import Toasted from 'vue-toasted'

require('dotenv').config();

Vue.use(Toasted, {
    duration: 3000
});

new Vue({
    el: '#app',
    style,
    render: h => h(App),
    router,
    store
});