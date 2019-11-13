<template>

    <div class="container-main">

        <main-header></main-header>

        <template v-if="isLoaded">
            <div class="new-doc new-doc_invoice">
                <span class="link"
                   @click="storeNewInvoice"
                >
                    New document
                </span>
            </div>
            <invoice-document></invoice-document>
            <payment-list></payment-list>
        </template>
        <div v-else>
            is loading
        </div>

        <total-info></total-info>

    </div>

</template>

<script>
    import MainHeader from './MainHeader.vue';
    import InvoiceDocument from './Invoice/InvoiceDocument.vue';
    import PaymentList from './Payments/PaymentList.vue';
    import TotalInfo from './TotalInfo/TotalInfo.vue';
    import InvoiceApi from '../api/InvoiceApi';
    import { mapState } from 'vuex';
    import storeInvoice from '../utils/storeInvoice';
    import hardcodedInvoices from '../assets/db/invoices';

    export default {
        props: {
            invoiceId: [String, Number]
        },
        mixins: [storeInvoice],
        data () {
            return {
                isLoaded: false
            }
        },
        computed: {
            config () {
                return {headers: {Authorization: 'Bearer ' + localStorage.getItem('userToken')}};
            }
        },
        mounted () {
            this.fetchData();
        },
        methods: {
            fetchData () {
                this.isLoaded = false;
                InvoiceApi.show(this.invoiceId, this.config)
                    .then(response => {
                        this.$store.commit('setInvoice', hardcodedInvoices[0]);
                        // this.$store.commit('setInvoice', response.data.data);
                        this.isLoaded = true;
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                        this.isLoaded = true;
                    })
            }
        },
        components: {
            InvoiceDocument,
            MainHeader,
            PaymentList,
            TotalInfo
        }
    }
</script>
