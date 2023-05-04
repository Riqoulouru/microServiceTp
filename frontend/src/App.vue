/* eslint-disable */
<template>

    <v-app>
        <v-main>
            <router-view> </router-view>
        </v-main>
    </v-app>

</template>

<script>
import SignIn from './views/SignIn'

export default {
    name: 'App',

    components: {
        SignIn
    }
};
</script>