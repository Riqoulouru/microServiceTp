
<template>

    <div>

        <div class="nav-container">

            <div class="avatar-container">
                <v-avatar color="primary" @mouseover="isHoverAvatar = true" rounded>{{avatarText != null ? avatarText : ""}}</v-avatar>
            </div>

            <v-col xs="4" sm="4" md="4" lg="4" xl="4">
                <v-text-field :append-icon="icon.mdiMagnify" placeholder="Rechercher"
                    filled rounded dense hide-details></v-text-field>

            </v-col>

            <div class="logo-container" @click="goHome">
                <img class="logo-img" src="../assets/logo-img.png" alt="logo">
                <img class="logo-write" src="../assets/logo-write.png" alt="cosmo">
            </div>

        </div>

        <v-slide-x-transition>
            <v-card class="menu-card" v-if="isHoverAvatar" @mouseleave="isHoverAvatar = false" color="white" width="20vw">

                <div class="avatar-container-menu">
                    <div style="width: 100%; display: flex; align-items: center; justify-content: center">
                        <v-avatar style="color: white" color="primary" rounded size="5vw">{{avatarText != null ? avatarText : ""}}</v-avatar>
                    </div>
                    <div style="width: 100%; display: flex; align-items: center; justify-content: center">
                        <v-card-title>{{usernameText}}</v-card-title>
                    </div>
                </div>


                <v-navigation-drawer permanent style="width: 100%;">
                    <v-list dense nav rounded>

                        <v-divider></v-divider>

                        <v-list-item link @click="goProfile">
                            <v-list-item-icon>
                                <v-icon color="success">{{ icon.mdiAccountStar }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Profile</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-list-item link @click="goHeart">
                            <v-list-item-icon>
                                <v-icon color="error">{{ icon.mdiHeart }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Liste de souhait</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-list-item link @click="goCart">
                            <v-list-item-icon>
                                <v-icon color="primary">{{ icon.mdiCart }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Panier</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-divider></v-divider>

                        <v-list-item link @click="logout">
                            <v-list-item-icon>
                                <v-icon color="warning">{{ icon.mdiLogoutVariant }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Se deconnecter</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                    </v-list>
                </v-navigation-drawer>


            </v-card>
        </v-slide-x-transition>

        <div v-if="isFetching" class="loading-container spacer-10">
            <v-fade-transition v-for="i in 4">
                <v-skeleton-loader elevation="1" v-if="isFetching" class="loading-element" type="card"> </v-skeleton-loader>
            </v-fade-transition>
        </div>

        <div v-if="isFetching" class="loading-container spacer-3">
            <v-fade-transition v-for="i in 4">
                <v-skeleton-loader elevation="1" v-if="isFetching" class="loading-element" type="date-picker"> </v-skeleton-loader>
            </v-fade-transition>
        </div>

        <div v-if="isFetching" class="loading-container spacer-3">
            <v-fade-transition v-for="i in 4">
                <v-skeleton-loader elevation="1" v-if="isFetching" class="loading-element" type="card"> </v-skeleton-loader>
            </v-fade-transition>
        </div>

        <div class="home-content-container">

            <v-card-title style="color: darkslategray">Catégories qui pourraient vous plaire</v-card-title>

            <div v-for="category in produitsByCategoryForHomePage.keys()">
                <v-card-title class="category-type">{{category}}</v-card-title>

                <div class="produit-container">
                    <v-card v-for="produit in produitsByCategoryForHomePage.get(category).content" min-width="250" elevation="1" class="card-produit">
                        <v-img height="170" src="../assets/no-img.png"></v-img>
                        <v-card-title style="text-align: center !important;">{{produit.name}}</v-card-title>
                        <v-divider></v-divider>
                        <v-card-text>{{produit.price}}€</v-card-text>
                        <v-card-text style="color: darkred">{{produit.stock}} restant(s)</v-card-text>
                        <v-card-text> Depuis le {{formatDate(produit.insertDate)}}</v-card-text>

                        <v-card-actions style="position:absolute; bottom: 0; right: 0">
                            <v-spacer></v-spacer>

                            <v-btn icon>
                                <v-icon color="error" @click="addToWishList(produit.idProduit)">{{icon.mdiHeart}}</v-icon>
                            </v-btn>
                            <v-btn icon>
                                <v-icon color="primary" @click="addToCart(produit.idProduit)">{{icon.mdiCartArrowDown}}</v-icon>
                            </v-btn>
                        </v-card-actions>
                    </v-card>
                </div>
            </div>

        </div>


        <div class="alert-container">
            <v-alert type="error" class="alert" transition="scale-transition" dismissible dense v-model="alert.errorProduitByCategoryForHomePage"> Impossible de récupérer les produits à afficher </v-alert>
        </div>

    </div>

</template>

<script>
import {mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown } from '@mdi/js'
import ProduitSync from "@/api/produitSync";
import ClientSync from "@/api/clientSync";

export default {
    name: 'Home',

    data() {
        return {
            icon: { mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown },
            alert: {
                errorProduitByCategoryForHomePage: false
            },
            isFetching: true,
            user: this.$store.getters.user,
            avatarText: "",
            usernameText: "",
            isHoverAvatar: false,
            produitsByCategoryForHomePage: new Map()
        }
    },

    methods: {

        goHome: function () {
            this.$router.push({name: "Home"});
        },

        logout: function () {
            this.$router.push({name: "SignIn"});
        },

        goCart: function () {
            this.$router.push({name: "Cart"});
        },

        goHeart: function () {
            this.$router.push({name: "Heart"});
        },

        goProfile: function () {
            this.$router.push({name: "Profile"});
        },

        getAllProduitsByCategoryForHomePage: function () {
            this.isFetching = true;
            ProduitSync.getAllProduitByCategoryForHomePage(this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .then((result) => {
                   console.log(result)
                    this.produitsByCategoryForHomePage = new Map(Object.entries(result));
                    this.alert.errorProduitByCategoryForHomePage = false;
                })
                .catch(() => {
                    this.alert.errorProduitByCategoryForHomePage = true;
                })
                .finally(() => this.isFetching = false);
        },

        formatDate: function (date) {
            if(date == null) return "";
            return date.slice(0, 10)
        },

        addToWishList: function (idProduit) {
            ClientSync.addProductToWishList(this.user.login, idProduit, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .finally(() => this.isFetching = false);
        },

        addToCart: function (idProduit) {
            ClientSync.addProductToCart(this.user.login, idProduit, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .finally(() => this.isFetching = false);
        },

    },
    mounted() {
        this.$store.commit("loadStoreFormCache");
        this.$store.dispatch('updatePage', 'Home');

        this.user = this.$store.getters.user;

        const user = this.user;
        if(user != null && user.firstname != null && user.lastname != null) {
            this.avatarText = user.firstname.slice(0, 1).toUpperCase() + "" + user.lastname.slice(0, 1).toUpperCase();
            this.usernameText = user.firstname.slice(0, 1).toUpperCase() + "" + user.firstname.slice(1, user.firstname.length) + " " + user.lastname.slice(0, 1) + "" + user.lastname.slice(1, user.lastname.length);

            this.getAllProduitsByCategoryForHomePage();
        }
        else this.$router.push({name: "SignIn"});
    }
}

</script>


<style scoped>


.nav-container {
    opacity: 1 !important;
    position: fixed;
    height: 7vh;
    width: 100%;
    top: 0;
    color: white;
    box-shadow: rgba(0, 0, 0, 0.05) 0px 6px 24px 0px, rgba(0, 0, 0, 0.08) 0px 0px 0px 1px;
    display: flex;
    justify-content: center;
    z-index: 10;
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

.menu-card {
    position: fixed;
    z-index: 10;
    top: .5vh;
    left: .5vw;
}

.avatar-container-menu {
    width: 100%;
    display: flex;
    flex-direction: column;
    padding-top: 2vh;
}

.spacer-bottom-2 {
    margin-bottom: 2vh;
}

.spacer-bottom-1 {
    margin-bottom: 1vh;
}

.spacer-1 {
    margin-top: 1vh;
}

.spacer-2 {
    margin-top: 2vh;
}

.spacer-3 {
    margin-top: 3vh;
}

.spacer-10 {
    margin-top: 10vh;
}

.alert-container {
    display: flex;
    flex-direction: column;
    width: fit-content;
    position: absolute;
    bottom: 1vh;
    left: 1vw;
}


.loading-container {
    display: flex;
    justify-content: space-evenly;
    width: 100%;
}

.loading-element {
    width: 23%;
}


.home-content-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 10vh;
}

.category-type {
    margin-left: 5vw;
    color: darkred;
}

.produit-container {
    padding: 1vh;
    width: 100%;
    display: flex;
    overflow: scroll;
}

.card-produit {
    margin-left: 1.5vw;
    margin-right: 1.5vw;
}

</style>
