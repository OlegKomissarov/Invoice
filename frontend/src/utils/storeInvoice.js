import InvoiceApi from '../api/InvoiceApi';
import moment from 'moment';
import { mapState } from 'vuex';

const DEFAULT_INVOICE = {
    number: 0,
    date: moment().format('YYYY-MM-DD'),
    title: ''
};

export default {
    computed: {
        config () {
            return { contentType: 'application/json;charset=UTF-8' };
            // return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
        },
        invoice () {
            return Object.assign({}, DEFAULT_INVOICE);
        }
    },
    methods: {
        storeNewInvoice () {
            InvoiceApi.store(this.invoice, this.config)
                .then(response => {
                    this.goToNewInvoice();
                })
                .catch(err => {
                    this.$toasted.error('Whoops. Something went wrong: ' + err);
                });
        },
        goToNewInvoice() {
            InvoiceApi.showAll(this.config)
                .then(response => {
                    this.$router.push({
                        name: 'invoice:show',
                        params: { invoiceId: response.data[response.data.length - 1].id }
                    });
                })
                .catch(err => {
                    this.$toasted.error('Whoops. Something went wrong: ' + err);
                });
        }
    }
};
