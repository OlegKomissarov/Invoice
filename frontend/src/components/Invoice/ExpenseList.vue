<template>
    <div class="invoice-table">
        <div class="expense">
            <span class="expense__elem">Description</span>
            <div class="expense__right">
                <span class="expense__elem">Count</span>
                <span class="expense__elem">Price</span>
                <span class="expense__elem">Total</span>
            </div>
        </div>
        <transition-group name="list">
            <expense-list-item v-for="(expense, index) in expenseList"
                               :key="expense.id"
                               :expense="expense"
                               @delete="deleteExpense(index, expense.id)"
                               @update="updateData(expense)"
            ></expense-list-item>
        </transition-group>
        <div class="invoice-under-table">
            <span class="add-button link"
               :class="{ 'link_disabled': isAllowToAddExpense }"
               @click="addBlock()"
            >+ Add</span>
            <span>
                Total: {{ total }}
            </span>
        </div>
    </div>
</template>

<script>
    import ExpenseListItem from './ExpenseListItem.vue';
    import Bus from '../../bus';
    import { mapState } from 'vuex';
    import ExpenseApi from '../../api/ExpenseApi';
    import hardcodedExpense from '../../assets/db/expense'

    export default {
        components: {
            ExpenseListItem
        },
        computed: {
            ...mapState({
                expenseList: state => state.invoice.expenses,
                id: state => state.invoice.id,
                invoice: state => state.invoice
            }),
            isAllowToAddExpense () {
                return this.expenseList[this.expenseList.length - 1] && !this.expenseList[this.expenseList.length - 1].description;
            },
            total () {
                return this.expenseList.reduce((sum, expense) => sum + expense.price * expense.count, 0);
            },
            config () {
                return {headers: {Authorization: 'Bearer ' + localStorage.getItem('userToken')}};
            }
        },
        watch: {
            total: {
                handler (total) {
                    Bus.$emit('invoiceTotal', total);
                },
                immediate: true
            },
            id: {
                handler () {
                    if (this.expenseList.length === 0) {
                        this.addBlock();
                    }
                },
                immediate: true
            }
        },
        methods: {
            updateData (expense) {
                let expenseData = { description: expense.description, count: expense.count, price: expense.price };
                ExpenseApi.update(expense.id, expenseData, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    })
            },
            deleteExpense(index, id) {
                ExpenseApi.deleteData(id, this.config)
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
                this.expenseList.splice(index, 1);
            },
            addBlock () {
                ExpenseApi.store(this.id, 0, this.config)
                    .then(response => {
                        this.expenseList.push(hardcodedExpense);
                        // this.expenseList.push(response.data);
                    })
                    .catch(err => {
                        this.$toasted.error('Whoops. Something went wrong: ' + err);
                    });
            }
        }
    }
</script>
