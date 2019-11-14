import InvoiceApi from '../api/InvoiceApi';

const DEFAULT_INVOICE = {
    user_id: 0
};

export default {
    computed: {
        config () {
            return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
        },
        invoice () {
            return Object.assign({}, DEFAULT_INVOICE);
        }
    },
    methods: {
        storeNewInvoice () {
            InvoiceApi.store(this.invoice, this.config)
                .then(response => {
                    this.updateVuex(response.data.data);
                    this.$router.push({ name: 'invoice:show', params: { invoiceId: response.data.data.id } });
                    // this.$router.push({ name: 'invoice:show', params: { invoiceId: '100' } });
                })
                .catch(err => {
                    this.$toasted.error('Whoops. Something went wrong: ' + err);
                });
        },
        updateVuex (invoice) {
            this.$store.commit('setInvoice', invoice);
        }
    }
};
