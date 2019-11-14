<template>

    <div>
        <profile-list></profile-list>

        <div class="exit-button-container">
            <div class="exit-button-container__elem"
                 @click="deleteToken"
            >
                Exit
            </div>
        </div>

    </div>

</template>

<script>
    import ProfileList from './ProfileList.vue';
    import axios from 'axios';

    export default {
        components: {
            ProfileList
        },
        methods: {
            deleteToken () {
                localStorage.removeItem('userToken');
                this.moveToLogin();
            },
            logout () {
                axios.post(`/api/logout`)
                    .then(() => {
                        this.moveToLogin();
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            },
            moveToLogin () {
                this.$router.push({ name: 'login' });
            }
        }
    };
</script>
