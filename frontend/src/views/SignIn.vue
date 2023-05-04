
<template>

  <div class="global-signIn-container">

      <div class="signIn-container">

          <v-card outlined elevation="0">
              <v-card-title> Bienvenu sur E-commerce de N</v-card-title>

              <v-divider></v-divider>

              <v-row justify="center" class="spacer-2 spacer-bottom-2">
                  <v-col xs="10" sm="10" md="10" lg="10" xl="10">
                      <v-text-field autofocus v-model="login" color="black" dense outlined placeholder="login"></v-text-field>
                      <v-text-field type="password" autofocus v-model="password" color="black" dense outlined placeholder="mot de passe"></v-text-field>
                      <v-row justify="center">
                          <v-btn :loading="isFetching" @click="signInProcess" outlined color="primary"> Se connecter </v-btn>
                      </v-row>
                  </v-col>
              </v-row>

          </v-card>

      </div>


      <div class="alert-container">
          <v-alert type="error" class="alert" transition="scale-transition" dismissible dense text v-model="alert.errorSignIn"> Login ou mot de passe incorrect </v-alert>
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

.spacer-bottom-2 {
    margin-bottom: 2vh;
}

.alert {
    padding-left: 2vw;
    padding-right: 2vw;
}


</style>
