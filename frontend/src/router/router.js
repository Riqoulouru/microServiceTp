/* eslint-disable */
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'SignIn',
            component: () => import('../views/SignIn.vue'),
            props: true
        },
        {
            path: '/signUp',
            name: 'SignUp',
            component: () => import('../views/SignUp.vue'),
            props: true
        },
        {
            path: '/home',
            name: 'Home',
            component: () => import('../views/Home.vue'),
            props: true
        },
    ]
})
