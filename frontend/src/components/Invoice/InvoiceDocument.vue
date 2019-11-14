<template>
    <div>

        <div class="invoice" v-if="invoice">

            <div class="invoice-header-container-main">
                <div class="invoice-header-container-column">
                    <input class="invoice-header-container-main__input invoice__header"
                           placeholder="invoice name"
                           @click="$refs.name.select()"
                           v-model="invoice.name"
                           ref="name"
                           @blur="updateData"
                    >
                    <!--<span>{{ $store.state.company.value }}</span>-->
                </div>
                <div class="invoice-header-container-row">
                    <span>№</span>
                    <input class="invoice-header-container-main__input invoice__number"
                           type="number"
                           placeholder="number"
                           @click="$refs.number.select()"
                           v-model="invoice.number"
                           ref="number"
                           @blur="updateData"
                    >
                    <input class="invoice-header-container-main__input invoice__date"
                           type="text"
                           placeholder="date"
                           @click="$refs.date.select()"
                           v-model="invoice.date"
                           ref="date"
                           @blur="updateData"
                    >
                </div>
            </div>

            <expense-list></expense-list>

            <div class="send-button-container">
                <div class="send-button-container__send-button">Send to Email</div>
            </div>

        </div>
    </div>
</template>

<script>
    import ExpenseList from './ExpenseList.vue';
    import InvoiceApi from '../../api/InvoiceApi';
    import { mapState } from 'vuex';

    export default {
        components: {
            ExpenseList
        },
        computed: {
            ...mapState({
                id: state => state.invoice.id,
                invoice: state => state.invoice
            }),
            config () {
                return { invoice: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
            }
        },
        methods: {
            updateData () {
                let invoice = {name: this.invoice.name, date: this.invoice.date, number: this.invoice.number};
                InvoiceApi.update(this.id, invoice, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            }
        }
    };
</script>
