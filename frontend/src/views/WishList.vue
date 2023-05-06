
<template>

    <div>

        <div class="nav-container">

            <div class="avatar-container">
                <v-avatar color="primary" @mouseover="isHoverAvatar = true" rounded>{{avatarText != null ? avatarText : ""}}</v-avatar>
            </div>


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

                        <v-list-item link @click="goHome">
                            <v-list-item-icon>
                                <v-icon color="success">{{ icon.mdiAccountStar }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Menu principale</v-list-item-title>
                            </v-list-item-content>
                        </v-list-item>

                        <v-list-item link @click="goHeart">
                            <v-list-item-icon>
                                <v-icon color="error">{{ icon.mdiHeart }}</v-icon>
                            </v-list-item-icon>

                            <v-list-item-content>
                                <v-list-item-title>Liste de souhaits</v-list-item-title>
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

        <div style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; color: darkslategrey" v-if="!isFetching && inWishList.length === 0">
            <v-card-title> Aucun produit dans votre liste de souhait 💦️ </v-card-title>
        </div>

        <div v-if="!isFetching && inWishList.length !== 0" class="home-content-container-search spacer-8">

            <v-card-title style="color: darkslategray">Votre liste de souhait️s</v-card-title>

            <div class="home-content-container-search">
                <v-card v-for="produit in inWishList" min-width="250" max-width="250" elevation="1" class="card-produit spacer-2">
                    <v-img height="170" src="../assets/no-img.png"></v-img>
                    <v-card-title style="text-align: center !important;">{{produit.name}}</v-card-title>
                    <v-divider></v-divider>
                    <v-card-text>{{produit.price}}€</v-card-text>
                    <v-card-text style="color: darkred">{{produit.stock}} restant(s)</v-card-text>
                    <v-card-text> Depuis le {{formatDate(produit.insertDate)}}</v-card-text>

                    <v-card-actions style="position:absolute; bottom: 0; right: 0">
                        <v-spacer></v-spacer>

                        <v-btn icon>
                            <v-icon color="error" @click="removeToWishList(produit.idProduit)">{{icon.mdiClose}}</v-icon>
                        </v-btn>
                        <v-btn icon>
                            <v-icon color="primary" @click="addToCart(produit.idProduit)">{{icon.mdiCartArrowDown}}</v-icon>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </div>
        </div>

    </div>

</template>

<script>
import {mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown, mdiClose } from '@mdi/js'
import ProduitSync from "@/api/produitSync";
import ClientSync from "@/api/clientSync";

export default {
    name: 'Home',

    data () {
        return {
            icon: { mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown, mdiClose },
            user: null,
            avatarText: "",
            usernameText: "",
            isHoverAvatar: false,
            inWishList: [],
            isFetching: false,
            client: null
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
            return;
        },

        goProfile: function () {
            this.$router.push({name: "Profile"});
        },

        getClientByLogin: function () {
            this.isFetching = true;
            ClientSync.getClientByLogin(this.user.login, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .then((result) => {
                    this.client = result;
                    this.getProductInHisWishList();
                })
                .catch((error) => {
                    console.error(error);
                })
                .finally(() => this.isFetching = false);
        },

        getProductInHisWishList: function () {
            this.isFetching = true;
            ProduitSync.getProductsByIdList(this.client != null ? this.client.wishCompositions : [], this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .then((result) => {
                    this.inWishList = result;
                })
                .catch((error) => {
                    console.error(error);
                })
                .finally(() => this.isFetching = false);
        },

        formatDate: function (date) {
            if(date == null) return "";
            return date.slice(0, 10)
        },

        addToCart: function (idProduit) {
            ClientSync.addProductToCart(this.user.login, idProduit, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .finally(() => this.isFetching = false);
        },

        removeToWishList: function (idProduit) {
            ClientSync.removeToWishList(this.user.login, idProduit, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .then(() => {
                    this.getClientByLogin();
                })
                .finally(() => this.isFetching = false);
        },

    },
    mounted() {
        this.$store.commit("loadStoreFormCache");
        this.$store.dispatch('updatePage', 'WishList');

        this.user = this.$store.getters.user;

        const user = this.user;
        if(user != null && user.firstname != null && user.lastname != null) {
            this.avatarText = user.firstname.slice(0, 1).toUpperCase() + "" + user.lastname.slice(0, 1).toUpperCase();
            this.usernameText = user.firstname.slice(0, 1).toUpperCase() + "" + user.firstname.slice(1, user.firstname.length) + " " + user.lastname.slice(0, 1) + "" + user.lastname.slice(1, user.lastname.length);
            this.getClientByLogin();
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

.spacer-8 {
    margin-top: 8vh;
}

.home-content-container {
    width: 100%;
    display: flex;
    flex-direction: column;
    margin-top: 10vh;
}

.home-content-container-search {
    width: 100%;
    display: flex;
    flex-direction: row;
    justify-content: space-evenly;
    flex-wrap: wrap;
}

.card-produit {
    margin-left: 1vw;
    margin-right: 1vw;
}

.spacer-2 {
    margin-top: 2vh;
}

</style>
