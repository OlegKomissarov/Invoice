<template>
    <div class="collection-item">
        <div class="collection-item-left">
            <span class="collection-item__header">{{ invoice.name }}</span>
            <span class="collection-item__sub-header">{{ invoice.number }}</span>
            <span class="collection-item__sub-header">{{ invoice.date }}</span>
        </div>
        <div class="collection-item-right">
            <span class="collection__elem">{{ total }}</span>
            <span class="collection__elem">{{ paid }}</span>
            <span class="collection__elem">{{ toPay }}</span>
        </div>
    </div>
</template>

<script>
    import { mapState } from 'vuex';

    export default {
        props:{
            invoice: Object
        },
        computed: {
            total () {
                let total = 0;
                for (let expense of this.invoice.expenses) {
                    total += expense.count * expense.price;
                }
                return total;
            },
            paid () {
                let paid = 0;
                for (let payment of this.invoice.payments) {
                    paid += payment.payment;
                }
                return paid;
            },
            toPay () {
                return this.total - this.paid;
            }
        }
    }
</script>