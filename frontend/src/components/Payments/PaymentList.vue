<template>
    <div class="payment-table-container">
        <span class="payment-header">Payments</span>
        <transition-group name="list">
            <payment-list-item v-for="(payment, index) in paymentList"
                               :key="payment.id"
                               :payment="payment"
                               @delete="deletePayment(index, payment.id)"
                               @update="updateData(payment)"
            >
            </payment-list-item>
        </transition-group>
        <div class="payment-under-table">
            <span class="add-button link"
                  :class="{'link_disabled': isAllowToAddPayment}"
                  @click="addBlock()"
            >
                + Add
            </span>
        </div>
    </div>
</template>

<script>
    import PaymentListItem from './PaymentListItem.vue';
    import Bus from '../../bus';
    import PaymentApi from '../../api/PaymentApi';
    import { mapState } from 'vuex';
    import hardcodedPayment from '../../assets/db/payment';

    export default {
        components: {
            PaymentListItem
        },
        computed: {
            ...mapState({
                paymentList: state => state.invoice.payments,
                id: state => state.invoice.id,
                invoice: state => state.invoice
            }),
            isAllowToAddPayment () {
                return this.paymentList[this.paymentList.length - 1]
                    && !this.paymentList[this.paymentList.length - 1].payment;
            },
            total () {
                return +this.paymentList.reduce((sum, payment) => sum + +payment.payment, 0);
            },
            config () {
                return { headers: { Authorization: 'Bearer ' + localStorage.getItem('userToken') } };
            }
        },
        watch: {
            total: {
                handler (total) {
                    Bus.$emit('paymentTotal', total);
                },
                immediate: true
            },
            id: {
                handler () {
                    if (this.paymentList.length === 0) {
                        this.addBlock();
                    }
                },
                immediate: true
            }
        },
        methods: {
            updateData (payment) {
                let paymentData = { payment: payment.payment, date: payment.date };
                PaymentApi.update(payment.id, paymentData, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            },
            deletePayment(index, id) {
                PaymentApi.deleteData(id, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
                this.paymentList.splice(index, 1);
            },
            addBlock () {
                PaymentApi.store(this.id, 0, this.config)
                    .then(response => {
                        this.paymentList.push(hardcodedPayment);
                        // this.paymentList.push(response.data);
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            }
        }
    };
</script>
