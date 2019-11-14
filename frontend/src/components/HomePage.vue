<template>

    <div class="container-main">

        <main-header></main-header>

        <template v-if="isLoaded">
            <invoice-document :fetchInvoice="fetchData"></invoice-document>
            <payment-list :fetchInvoice="fetchData"></payment-list>
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

    export default {
        props: {
            invoiceId: [String, Number]
        },
        data () {
            return {
                isLoaded: false
            }
        },
        computed: {
            config () {
                // return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
            }
        },
        watch: {
            invoiceId: {
                handler () {
                    // I need to reload invoice because JPA cannot simply return created instance after saving in DB.
                    this.fetchData();
                },
                immediate: true
            }
        },
        // mounted () {
        //     this.fetchData();
        // },
        methods: {
            fetchData () {
                this.isLoaded = false;
                InvoiceApi.show(this.invoiceId, this.config)
                    .then(response => {
                        this.$store.commit('setInvoice', response.data);
                        this.isLoaded = true;
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                        this.isLoaded = true;
                    });
            }
        },
        components: {
            InvoiceDocument,
            MainHeader,
            PaymentList,
            TotalInfo
        }
    };
</script>
