<template>
    <div>
        <div class="collection"
             v-if="isLoaded"
        >
            <div class="collection-item-with-button"
                 v-for="(invoice, index) in invoiceList"
                 >
                <router-link :key="invoice.id"
                             :to="{ name: 'invoice:show', params: { invoiceId: invoice.id } }"
                >
                    <invoice-list-elem :invoice="invoice"></invoice-list-elem>
                </router-link>
                <div class="remove-button"
                     @click="deleteInvoice(index)"
                >
                    X
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import InvoiceListElem from './InvoiceListElem.vue';
    import InvoiceApi from '../../api/InvoiceApi';
    import { mapState } from 'vuex';

    export default {
        components: {
            InvoiceListElem
        },
        data () {
            return {
                isLoaded: false
            }
        },
        computed: {
            ...mapState({
                id: state => state.invoice.id,
                invoiceList: state => state.invoiceList
            }),
            config () {
                return {
                    // headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken'), SecFetchSite: 'none' }
                };
            }
        },
        mounted () {
            this.fetchData();
        },
        methods: {
            deleteData (id) {
                InvoiceApi.deleteData(id, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            },
            fetchData () {
                InvoiceApi.showAll(this.config)
                    .then(response => {
                        this.$store.commit('setInvoiceList', response.data);
                        this.isLoaded = true;
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            },
            deleteInvoice (index) {
                this.deleteData(this.invoiceList[index].id);
                this.invoiceList.splice(index, 1);
            }
        }
    };
</script>
