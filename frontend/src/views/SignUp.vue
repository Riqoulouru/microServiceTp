
<template>

  <div class="global-signUp-container">

      <div class="signUp-container">

          <v-card outlined elevation="5" color="white" width="30vw">
              <v-row justify="center" class="spacer-1 spacer-bottom-1">
                  <img class="cosmo-logo" src="../assets/logo.png" alt="cosmo">
              </v-row>

              <v-divider></v-divider>

              <v-card-text class="spacer-1"> Inscrivez-vous pour un minimum de dépense 🥇 </v-card-text>

              <v-row justify="center" class="spacer-bottom-2">
                  <v-col xs="10" sm="10" md="10" lg="10" xl="10">
                      <v-text-field autofocus hide-details v-model="login" color="black" dense outlined placeholder="Login" v-on:keyup.enter="signUpProcess"></v-text-field>
                      <v-text-field class="spacer-1" hide-details type="password" autofocus v-model="password" color="black" dense outlined placeholder="Mot de passe" v-on:keyup.enter="signUpProcess"></v-text-field>
                      <v-text-field class="spacer-1" hide-details v-model="firstname" color="black" dense outlined placeholder="Nom" v-on:keyup.enter="signUpProcess"></v-text-field>
                      <v-text-field class="spacer-1" hide-details v-model="lastname" color="black" dense outlined placeholder="Prénom" v-on:keyup.enter="signUpProcess"></v-text-field>
                      <v-row justify="center" class="spacer-2">
                          <v-btn :loading="isFetching" @click="signUpProcess" outlined color="primary"> S'inscrire </v-btn>
                      </v-row>
                  </v-col>
              </v-row>

              <v-divider></v-divider>

              <v-card-text style="text-align: center;"> Déjà un compte ? <a @click="signIn">Connectez-vous ici</a></v-card-text>

          </v-card>

      </div>


      <div class="alert-container">
          <v-alert type="error" class="alert" transition="scale-transition" dismissible dense v-model="alert.errorSignUp"> Login déjà existant </v-alert>
          <v-alert type="warning" class="alert" transition="scale-transition" dismissible dense v-model="alert.fillAllField"> Merci de remplir tous les champs </v-alert>
      </div>

  </div>


</template>

<script>
import ClientSync from "@/api/clientSync";

export default {
    name: 'SignUp',

    data() {
        return {
            alert: {errorSignUp: false, fillAllField: false},
            isFetching: false,
            login: "",
            password: "",
            firstname: "",
            lastname: ""
        }
    },

    methods: {

        signUpProcess: function () {

            const fieldToCheck = [this.login, this.password, this.lastname, this.firstname];
            if(fieldToCheck.filter(element => element === "").length > 0) {
                this.alert.fillAllField = true;
                return;
            }
            else this.alert.fillAllField = false;

            this.isFetching = true;

            ClientSync.processSignUp(this.login, this.password, this.firstname, this.lastname)
                .then((response) => {
                    if (response.status === 200) return response.text();
                    throw response
                })
                .then((result) => {
                    this.$store.dispatch('updatePage', "signIn");
                    this.$router.push({name: "SignIn"})
                })
                .catch(() => {
                    this.alert.errorSignUp = true;
                })
                .finally(() => this.isFetching = false);

        },

        signIn: function () {
            this.$router.push({name: "SignIn"})
        }

    },
    mounted() {
        this.$store.dispatch('logout')
        this.$store.dispatch('updatePage', 'SignUp')
    }
}

</script>

<style scoped>

.global-signUp-container {
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

.signUp-container {
    width: 70vw !important;
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

.spacer-bottom-2 {
    margin-bottom: 2vh;
}

.spacer-bottom-1 {
    margin-bottom: 1vh;
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
