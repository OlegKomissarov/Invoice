<template>
    <div class="expense-with-button">
        <div class="expense">
            <input class="expense__elem"
                   placeholder="description"
                   @click="$refs.description.select()"
                   v-model="expense.description"
                   ref="description"
                   @blur="sendEmit('description')"
            />
            <div class="expense__right">
                <input class="expense__elem expense__elem_margin"
                       type="number"
                       placeholder="count"
                       @click="$refs.count.select()"
                       v-model="expense.count"
                       ref="count"
                       @blur="sendEmit('count')"
                />
                <input class="expense__elem expense__elem_margin"
                       type="number"
                       placeholder="price"
                       @click="$refs.price.select()"
                       v-model="expense.price"
                       ref="price"
                       @blur="sendEmit('price')"
                />
                <span class="expense__elem">{{ total }}</span>
            </div>
        </div>
        <div class="remove-button"
             @click="$emit('delete')"
        >
            X
        </div>
    </div>
</template>

<script>
    export default {
        props: {
            expense: Object
        },
        computed: {
            total() {
                return this.expense.price * this.expense.count
            }
        },
        methods: {
            sendEmit (type) {
                if ((type === 'count' || type === 'price') && this.expense[type] === '') {
                    this.expense[type] = 0;
                }
                if (this.expense.description === '' && this.expense.count === 0 && this.expense.price === 0) {
                    this.$emit('delete');
                    return;
                }
                this.$emit('update');
            }
        }
    };
</script>
