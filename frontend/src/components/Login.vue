<template>
    <div class="login">
        <form action="" autocomplete="off">
            <input type="text"
                   placeholder="email"
                v-model="data.email"
            >
        </form>
        <input type="password"
               placeholder="password"
               v-model="data.password"
        >
        <span class="link navigation-buttons__elem navigation-buttons__elem_profile"
              @click="submit()"
        >
            Submit
        </span>
    </div>
</template>

<script>
    import axios from 'axios';

    export default {
        data () {
            return {
                data: {
                    email: '',
                    password: ''
                }
            }
        },
        computed: {
            config () {
                return {
                    headers: {Authorization: 'Bearer ' + localStorage.getItem('userToken')}
                };
            }
        },
        methods: {
            submit () {
                axios.post(`api/login`, this.data)
                    .then(response => {
                        localStorage.setItem('userToken', response.data['access_token']);
                        this.$router.push({ name: 'all-invoices' });
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            }
        }
    }
</script>
