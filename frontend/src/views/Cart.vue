
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

        <div style="height: 100vh; width: 100vw; display: flex; justify-content: center; align-items: center; color: darkslategrey" v-if="!isFetching && inCart.length === 0">
            <v-card-title> Aucun produit dans votre liste de souhait 💦️ </v-card-title>
        </div>

        <div v-if="!isFetching && inCart.length !== 0" class="home-content-container-search spacer-8">

            <div style="display: flex; flex-direction: column; align-items: center; text-align: center">
                <v-card-title style="color: darkslategray">Votre panier</v-card-title>
                <v-btn outlined color="primary" @click="paiement"> Valider le panier </v-btn>
            </div>

            <div class="home-content-container-search">
                <v-card v-for="produit in inCart" min-width="250" max-width="250" elevation="1" class="card-produit spacer-2">
                    <v-img height="170" src="../assets/no-img.png"></v-img>
                    <v-card-title style="text-align: center !important;">{{produit.name}}</v-card-title>
                    <v-divider></v-divider>
                    <v-card-text>{{produit.price}}€</v-card-text>
                    <v-card-text style="color: darkred">{{produit.stock}} restant(s)</v-card-text>
                    <v-card-text> Depuis le {{formatDate(produit.insertDate)}}</v-card-text>

                    <v-card-actions style="position:absolute; bottom: 0; right: 0">
                        <v-spacer></v-spacer>

                        <v-btn icon>
                            <v-icon color="error" @click="removeToCart(produit.idProduit)">{{icon.mdiClose}}</v-icon>
                        </v-btn>
                    </v-card-actions>
                </v-card>
            </div>
        </div>


        <v-dialog v-model="modalPaiement" persistent max-width="50vw">
            <v-card>
                <v-card-title class="modal-title-users">
                    <span class="text-h5">  Attention informations paiements </span>
                </v-card-title>
                <v-divider></v-divider>

                <v-col class="flex-column-users">
                    <v-row justify="center">
                        <v-col xs="12" sm="12" md="12" lg="12" xl="12">
                            <v-card-text> Pour le bien du TP, il est impossible de payer de façon réelle. Le process va continuer comme si le paiement avait été effectué </v-card-text>
                        </v-col>
                        <v-col xs="12" sm="12" md="12" lg="12" xl="12">
                            <v-btn outlined color="success" @click="afterPaiement"> Continuer </v-btn>
                        </v-col>
                    </v-row>
                </v-col>

            </v-card>
        </v-dialog>


    </div>

</template>

<script>
import {mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown, mdiClose } from '@mdi/js'
import ProduitSync from "@/api/produitSync";
import ClientSync from "@/api/clientSync";
import CommandeSync from "@/api/commandeSync";
import PaiementSync from "@/api/paiementSync";

export default {
    name: 'Home',

    data () {
        return {
            icon: { mdiMagnify, mdiAccountStar, mdiHeart, mdiCart, mdiLogoutVariant, mdiCartArrowDown, mdiClose },
            user: null,
            avatarText: "",
            usernameText: "",
            isHoverAvatar: false,
            inCart: [],
            isFetching: false,
            client: null,
            modalPaiement: false,
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
            return;
        },

        goHeart: function () {
            this.$router.push({name: "WishList"});
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
                    this.getProductInHisCart();
                })
                .catch((error) => {
                    console.error(error);
                })
                .finally(() => this.isFetching = false);
        },

        getProductInHisCart: function () {
            this.isFetching = true;
            ProduitSync.getProductsByIdList(this.client != null ? this.client.cartCompositions : [], this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .then((result) => {
                    this.inCart = result;
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

        removeToCart: function (idProduit) {
            ClientSync.removeToCart(this.user.login, idProduit, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .then(() => {
                    this.getClientByLogin();
                })
                .finally(() => this.isFetching = false);
        },

        paiement: function () {
            this.modalPaiement = true;
            this.savePaiement();
        },

        savePaiement: function () {

            PaiementSync.addNewPaiement(this.inCart, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.json();
                    throw response
                })
                .then((result) => {
                    const paiement = result;
                    this.createCommand(result.idPaiement);
                })
                .catch((error) => {console.error(error);})

        },

        resetCart: function () {
            ClientSync.resetCart(this.user.login, this.user.token)
        },

        createCommand: function (idPaiement) {
            CommandeSync.validatePanier(this.user.login, this.inCart.map(ele => ele.idProduit), idPaiement, this.user.token)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .then((result) => {
                    this.resetCart();
                })
                .catch((error) => {console.error(error);})

        },

        afterPaiement: function () {
            this.modalPaiement = false;
            this.goHome();
        }

    },
    mounted() {
        this.$store.commit("loadStoreFormCache");
        this.$store.dispatch('updatePage', 'Cart');

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

.flex-column-users {
    display: flex;
    flex-direction: column;
    padding: 2vw;
    width: 100%;
}

</style>
