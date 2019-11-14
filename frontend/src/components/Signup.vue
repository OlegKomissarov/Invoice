<template>
    <div class="login">
        <form action="" autocomplete="off">
            <input type="text"
                   placeholder="name"
                   v-model="data.name"
            />
        </form>
        <form action="" autocomplete="off">
            <input type="text"
                   placeholder="email"
                   v-model="data.email"
            />
        </form>
        <input type="password"
               placeholder="password"
               v-model="data.password"
        />
        <span class="link navigation-buttons__elem navigation-buttons__elem_profile"
              @click="submit()"
        >
            Submit
        </span>
    </div>
</template>

<script>
    import axios from 'axios';
    import storeProfile from '../utils/storeProfile';

    export default {
        data () {
            return {
                data: {
                    name: '',
                    email: '',
                    password: ''
                }
            }
        },
        mixins: [storeProfile],
        computed: {
            config () {
                // return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
            }
        },
        methods: {
            submit () {
                axios.post(`/api/user`, this.data)
                    .then(() => {
                        axios.post(`api/user/login`, this.data)
                            .then(response => {
                                localStorage.setItem('userToken', response.data['access_token']);
                                this.storeNewProfile();
                            })
                            .catch(err => {
                                this.$toasted.error('Whoops. Something went wrong: ' + err);
                            });

                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            }
        }
    };
</script>
