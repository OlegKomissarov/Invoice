<template>
    <div>

        <total-info-table
                :currency="currencyFrom"
                :invoiceTotal="totalPayCurrencyFrom"
                :paymentTotal="totalPaidCurrencyFrom"
                :totalToPay="totalToPayCurrencyFrom"
        ></total-info-table>

        <currency-block source="from"
                        :neighbour="currencyTo"
                        @currencyChange="currencyHandler"
                        :currency="currencyFrom"
                        :currencies="currencies"
        ></currency-block>

        <div class="reverse-currency">
            <span class="add-button" @click="reverseCurrencies">
              ↑↓
            </span>
        </div>

        <currency-block source="to"
                        :neighbour="currencyFrom"
                        @currencyChange="currencyHandler"
                        :currency="currencyTo"
                        :currencies="currencies"
        ></currency-block>

        <div class="currency-date">
            <span>Choose the date:</span>
            <input type="date"
                   v-model="selectedDate"
                   @blur="dateHandler"
            />
        </div>

        <total-info-table
                :currency="currencyTo"
                :invoiceTotal="totalPayCurrencyTo"
                :paymentTotal="totalPaidCurrencyTo"
                :totalToPay="totalToPayCurrencyTo"
        ></total-info-table>

    </div>
</template>

<script>
    import Bus from '../../bus';
    import TotalInfoTable from './TotalInfoTable.vue';
    import CurrencyBlock from './CurrencyBlock.vue';

    export default {
        components: {
            TotalInfoTable,
            CurrencyBlock
        },
        watch: {
            currencyFrom () {
                this.countCoefficient();
            },
            currencyTo () {
                this.countCoefficient();
            }
        },
        data () {
            return {
                currencyFrom: 'EUR',
                currencyTo: 'USD',
                invoiceTotal: 0,
                paymentTotal: 0,
                currencyCoefficient: 1,
                currencies: null,
                selectedDate: null
            }
        },
        computed: {
            totalPayCurrencyFrom () {
                return +this.invoiceTotal.toFixed(2);
            },
            totalPaidCurrencyFrom () {
                return +this.paymentTotal.toFixed(2);
            },
            totalToPayCurrencyFrom () {
                return +(this.invoiceTotal - this.paymentTotal).toFixed(2);
            },
            totalPayCurrencyTo () {
                return +(this.invoiceTotal * this.currencyCoefficient).toFixed(2);
            },
            totalPaidCurrencyTo () {
                return +(this.paymentTotal * this.currencyCoefficient).toFixed(2);
            },
            totalToPayCurrencyTo () {
                return +((this.invoiceTotal - this.paymentTotal) * this.currencyCoefficient).toFixed(2);
            }
        },
        mounted () {
            this.getCurrencyRate();
            Bus.$on('invoiceTotal', this.updateInvoiceTotal);
            Bus.$on('paymentTotal', this.updatePaymentTotal);
        },
        methods: {
            dateHandler () {
                let currentDate = new Date();
                let startDate = 2000;
                if (
                    this.selectedDate && +this.selectedDate.slice(0, 4) >= startDate
                    && +this.selectedDate.slice(0, 4) <= currentDate.getFullYear()
                ) {
                    this.getCurrencyRateByDate();
                }
                if (
                    +this.selectedDate.slice(0, 4) < startDate
                    || +this.selectedDate.slice(0, 4) > currentDate.getFullYear()
                ) {
                    this.$toasted.error('No information about this date');
                }
            },
            currencyHandler (currency) {
                currency.source === 'from' ? this.currencyFrom = currency.value : this.currencyTo = currency.value;
            },
            updateInvoiceTotal (total) {
                this.invoiceTotal = total;
            },
            updatePaymentTotal (total) {
                this.paymentTotal = total;
            },
            countCoefficient () {
                this.currencyCoefficient = this.currencies[this.currencyTo] / this.currencies[this.currencyFrom];
            },
            getCurrencyRate () {
                fetch('http://data.fixer.io/api/latest?access_key=5702be60249da0124bb6c9ed200830b3&format=1')
                    .then(response => {
                        return response.json();
                    })
                    .then(object => {
                        this.selectedDate = object.date;
                        this.currencies = Object.assign(object.rates, {'EUR': 1});
                        this.countCoefficient();
                    })
                    .catch(err => {
                        this.$toasted.error('Parsing failed');
                    });
            },
            getCurrencyRateByDate () {
                fetch(`http://data.fixer.io/api/${this.selectedDate}?access_key=5702be60249da0124bb6c9ed200830b3&format=1`)
                    .then(response => {
                        return response.json();
                    })
                    .then(object => {
                        this.currencies = Object.assign(object.rates, {'EUR': 1});
                        this.countCoefficient();
                    })
                    .catch(err => {
                        switch (err.status) {
                            case 404:
                                this.$toasted.error('Data no found');
                                break;
                            case 429:
                                this.$toasted.error('Too many requests');
                                break;
                            case 500:
                                this.$toasted.error('Internal Server Error');
                                break;
                            default:
                                this.$toasted.show('Unknown error');
                        }
                    });
            },
            reverseCurrencies () {
                [this.currencyFrom, this.currencyTo] = [this.currencyTo, this.currencyFrom]
            }
        }
    };
</script>
