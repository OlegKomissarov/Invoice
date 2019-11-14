<template>
    <div class="navigation-buttons">
        <div class="navigation-buttons-left">
            <router-link class="link navigation-buttons__elem"
                    :to="{ name: 'all-invoices' }"
            >
                See all invoices
            </router-link>
        </div>
        <div class="navigation-buttons-right">
            <router-link :to="{ name: 'signup' }"
                         class="link navigation-buttons__elem"
            >
                Sign up
            </router-link>
            <router-link :to="{ name: 'login' }"
                         class="link navigation-buttons__elem"
            >
                Log in
            </router-link>
            <router-link :to="{ name: 'profile:show' }"
                         class="link navigation-buttons__elem navigation-buttons__elem_profile"
            >
                John Doe
            </router-link>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex';

    export default {
        computed: {
            ...mapState({
                id: state => state.invoice.id
            })
        }
    };
</script>
