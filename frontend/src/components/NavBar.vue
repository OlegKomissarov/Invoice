<template>
    <div class="navigation-buttons">
        <div class="navigation-buttons-left">
            <!--<select class="navigation-buttons__elem"-->
                    <!--@change="$router.push({ name: 'invoices:show', params: {invoiceId: id} })"-->
            <!--&gt;-->
                <!--<option disabled selected style='display:none;'>Your invoices</option>-->
                <!--<option value="Invoice_1">Invoice_1</option>-->
                <!--<option value="Invoice_2">Invoice_2</option>-->
            <!--</select>-->
            <!--<router-link v-if="id"-->
                         <!--class="link navigation-buttons__elem"-->
                         <!--:to="{ name: 'invoice:show', params: {invoiceId: id } }"-->
                         <!--&gt;-->
                <!--Back to last invoice-->
            <!--</router-link>-->
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
    }
</script>
