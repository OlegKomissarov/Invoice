import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex);

const store = new Vuex.Store({
    state: {
        company: {},
        invoice: {},
        invoiceList: []
    },
    mutations: {
        setCompanyToLocalStorage (state) {
            localStorage.setItem('company', JSON.stringify(state.company));
        },
        setInvoice (state, invoice) {
            state.invoice = invoice;
        },
        setProfile (state, profile) {
            state.profile = profile;
        },
        setInvoiceList (state, invoiceList) {
            state.invoiceList = invoiceList;
        }
    }
});

export default store;
