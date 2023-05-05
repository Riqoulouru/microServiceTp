/* eslint-disable */
import Vue from 'vue'
import App from './App.vue'
import router from "@/router/router";
import store from './store/store'
import vuetify from './plugins/vuetify'

Vue.config.productionTip = false
export const URL_API = "http://localhost:9004"

new Vue({
  router,
  store,
  vuetify,
  render: h => h(App),
}).$mount('#app')
