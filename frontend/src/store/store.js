/* eslint-disable */
import Vue from 'vue'
import Vuex from 'vuex'
import JwtUtils from "@/plugins/JwtUtils";

Vue.use(Vuex)

export default new Vuex.Store({
    state: {
        user: {},
        page: 'home'
    },
    getters: {
        user: state => {
            return state.user;
        },

        page: state => {
            return state.page;
        }
    },
    mutations: {
        setPage: (state, page) => {
            state.page = page;
        },
        setUser: (state, user) => {
            state.user = user
        },
        setRoad: (state, road) => {
            if(state.user != null) state.user.road = road;
        },
        saveStoreInCache: (state) => {
            localStorage.setItem('user', JSON.stringify(state.user))
        },
        resetCache: (state) => {
            localStorage.removeItem('user')
        },
        invalidateToken: (state) => {
            state.user = null
        },
        loadStoreFormCache: (state) => {
            const user = localStorage.getItem('user')
            if (user != null) {
                console.debug('Load store form cache')
                state.user = JSON.parse(user)
            }
        },
    },
    actions: {
        login: ({ state, commit }, token) => {
            state.user = JwtUtils.parseJwt(token.replace('Bearer ', ''));
            state.user.token = token;
            commit('setUser', state.user);
            commit('saveStoreInCache');
        },
        logout: ({ commit }) => {
            commit('invalidateToken')
            commit('resetCache')
        },
        updatePage: ({state, commit}, page) => {
            commit('setPage', page);
        }
    }
})