<template>
    <div class="currency">
        <span class="currency__header">Choose new currency:</span>
        <div>
            <span class="currency__description">{{source}}:</span>
            <select v-if="currencies"
                    :value="currency"
                    @input="inputHandler"
            >
                <option v-for="(value, key) in currencies"
                        :key="key"
                        :value="key"
                        :disabled="key === neighbour"
                >{{ key }}</option>
            </select>
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            currency: String,
            source: String,
            neighbour: String,
            currencies: Object
        },
        methods: {
            inputHandler (event) {
                this.$emit('currencyChange', {value: event.target.value, source: this.source})
            }
        }
    }
</script>
