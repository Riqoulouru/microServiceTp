/* eslint-disable */
import Vue from 'vue'
import App from './App.vue'
import router from "@/router/router";
import store from './store/store'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false
export const URL_API_CLIENT = "http:/localhost:8087"
export const URL_API_COMMANDE = "http:/localhost:8085"
export const URL_API_PAIEMENT = "http:/localhost:8086"
export const URL_API_PRODUIT = "http:/localhost:8084"

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app')
