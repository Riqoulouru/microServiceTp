<template>

    <div class="nav-container">

        <div class="avatar-container">
            <v-avatar color="primary" rounded>{{avatarText != null ? avatarText : ""}}</v-avatar>
        </div>

        <v-col xs="4" sm="4" md="4" lg="4" xl="4" v-if="page === 'Home'">
            <v-text-field
                :append-icon="icon.mdiMagnify"
                placeholder="Rechercher"
                filled
                rounded
                dense
                hide-details
            ></v-text-field>

        </v-col>

        <div class="logo-container" @click="goHome">
            <img class="logo-img" src="../assets/logo-img.png" alt="logo">
            <img class="logo-write" src="../assets/logo-write.png" alt="cosmo">
        </div>

    </div>

</template>

<script>

import {mdiMagnify} from '@mdi/js'

export default {
    name: "Navbar",
    props: ['page'],
    data() {
        return {
            icon: { mdiMagnify },
            user: this.$store.getters.user,
            avatarText: "",
        }
    },
    methods: {

        changePage: function(page) {
            if(this.$store.getters.page !== page) this.$router.push({name: page});
        },

        goHome: function () {
            this.$router.push({name: "Home"});
        }

    },
    mounted() {

        this.user = this.$store.getters.user;

        const user = this.user;
        if(user != null && user.firstname != null && user.lastname != null) {
            this.avatarText = this.user.firstname.slice(0, 1).toUpperCase() + "" + this.user.lastname.slice(0, 1).toUpperCase();
        }
    }
}
</script>

<style scoped>

.nav-container {
    position: fixed;
    height: 7vh;
    width: 100%;
    top: 0;
    color: white;
    box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
    display: flex;
    justify-content: center;
}

.avatar-container {
    position: absolute;
    height: 7vh;
    display: flex;
    align-items: center;
    top: 0;
    left: 1vw;
}

.logo-container {
    height: 7vh;
    width: 15vw;
    position: absolute;
    display: flex;
    justify-content: center;
    right: 0;
    top: 0;
    align-items: center;
    cursor: pointer;
}

.logo-write {
    width: 5vw;
    height: auto;
}

.logo-img {
    margin-right: 1vw;
    height: 5vh;
    width: auto;
}

</style>
