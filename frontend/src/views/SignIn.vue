
<template>

  <div class="global-signIn-container">

      <div class="signIn-container">

          <v-card outlined elevation="5" color="white">
              <v-row justify="center" class="spacer-1 spacer-bottom-1">
                  <img class="cosmo-logo" src="../assets/logo.png" alt="cosmo">
              </v-row>

              <v-divider></v-divider>

              <v-card-text class="spacer-1"> Connectez-vous pour un maximum de bons plans 🥇 </v-card-text>

              <v-row justify="center" class="spacer-bottom-2">
                  <v-col xs="10" sm="10" md="10" lg="10" xl="10">
                      <v-text-field autofocus hide-details v-model="login" color="black" dense outlined placeholder="Login" v-on:keyup.enter="signInProcess"></v-text-field>
                      <v-text-field class="spacer-1" type="password" hide-details v-model="password" color="black" dense outlined placeholder="Mot de passe" v-on:keyup.enter="signInProcess"></v-text-field>
                      <v-row justify="center" class="spacer-2">
                          <v-btn :loading="isFetching" @click="signInProcess" outlined color="primary"> Se connecter </v-btn>
                      </v-row>
                  </v-col>
              </v-row>

              <v-divider></v-divider>

              <v-card-text style="text-align: center;"> Pas de compte ? <a @click="signUp">Inscrivez-vous ici</a></v-card-text>

          </v-card>

      </div>


      <div class="alert-container">
          <v-alert type="error" class="alert" transition="scale-transition" dismissible dense v-model="alert.errorSignIn"> Login ou mot de passe incorrect </v-alert>
      </div>

  </div>


</template>

<script>
import ClientSync from "@/api/clientSync";

export default {
    name: 'SignIn',

    data() {
        return {
            alert: {errorSignIn: false},
            isFetching: false,
            login: "",
            password: ""
        }
    },

    methods: {

        signInProcess: function () {
            this.isFetching = true;

            ClientSync.processLogin(this.login, this.password)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .then((jwt) => {
                    this.$store.dispatch('login', jwt)
                    this.$store.dispatch('updatePage', 'home');
                    this.$router.push({name: "Home"})
                })
                .catch(() => {
                    this.alert.errorSignIn = true;
                })
                .finally(() => this.isFetching = false);

        },

        signUp: function () {
            this.$router.push({name: "SignUp"})
        }

    },
    mounted() {
        this.$store.dispatch('logout')
        this.$store.dispatch('updatePage', 'SignIn')
    }
}

</script>

<style scoped>

.global-signIn-container {
    display: flex;
    align-content: center;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 100%;
    background: url("../assets/background.jpeg");
    background-position: center top;
    background-size: 150% auto;
}

.alert-container {
    display: flex;
    flex-direction: column;
    width: fit-content;
    position: absolute;
    bottom: 1vh;
    left: 1vw;
}

.signIn-container {
    width: 40vw !important;
    min-height: fit-content;
    height: 50vh !important;
    display: flex;
    justify-content: center;
    align-items: center;
}

.card-content-container {
    display: flex;
    flex-direction: column;
}


.spacer-2 {
    margin-top: 2vh;
}

.spacer-1 {
    margin-top: 1vh;
}

.spacer-05 {
    margin-top: .5vh;
}

.spacer-bottom-2 {
    margin-bottom: 2vh;
}

.spacer-bottom-1 {
    margin-bottom: 1vh;
}

.spacer-bottom-05 {
    margin-bottom: .5vh;
}

.alert {
    padding-left: 2vw;
    padding-right: 2vw;
}

.cosmo-logo {
    height: 13vh;
    width: auto;
}

</style>
