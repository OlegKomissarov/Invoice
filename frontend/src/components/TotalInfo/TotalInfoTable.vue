<template>
    <div class="info-container">
        <div class="payment-header">
            Total payment information (
            <span>{{ currency }}</span>
            )
        </div>
        <div class="payment">
            <span>Total price</span>
            <div>{{ invoiceTotal }}</div>
        </div>
        <div class="payment">
            <span>Already paid</span>
            <div>{{ paymentTotal }}</div>
        </div>
        <div class="payment">
            <span>Price to pay</span>
            <div>{{ totalToPay }}</div>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            currency: String,
            invoiceTotal: Number,
            paymentTotal: Number,
            totalToPay: Number
        }
    };
</script>
