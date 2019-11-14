<template>

    <div>
        <div class="new-doc new-doc_collection">
            <span class="link"
                  @click="storeNewInvoice"
            >
                    New document
                </span>
        </div>

        <div class="collection-up-panel">
            <div class="collection-up-panel-left">
                <span>Sort by:</span>
                <select class="collection-up-panel-left__select">
                    <option value="">Title</option>
                    <option value="">Number</option>
                </select>
            </div>
            <div class="collection-up-panel-right">
                <span
                        class="collection-up-panel__elem"
                        @click=""
                >
                    Total
                </span>
                <span class="collection-up-panel__elem">Paid</span>
                <span class="collection-up-panel__elem">To pay</span>
            </div>
        </div>

        <invoices-list></invoices-list>
    </div>

</template>

<script>
    import InvoicesList from './InvoicesList.vue';
    import storeInvoice from '../../utils/storeInvoice';

    export default {
        mixins: [storeInvoice],
        components: {
            InvoicesList
        }
    };
</script>
