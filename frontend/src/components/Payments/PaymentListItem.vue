 <template>
     <div class="payment-with-button">
         <div class="payment payment_padding">
             <input class="payment__elem"
                    type="text"
                    placeholder="date"
                    @click="$refs.date.select()"
                    v-model="payment.date"
                    ref="date"
                    @blur="sendEmit('date')"
             />
             <input class="payment__elem payment__elem_right"
                    type="number"
                    placeholder="pay"
                    @click="$refs.pay.select()"
                    v-model="payment.payment"
                    ref="pay"
                    @blur="sendEmit('pay')"
             />
         </div>
         <div class="remove-button"
              @click="$emit('delete')"
         >
             X
         </div>
     </div>
 </template>

 <script>
     import moment from 'moment';

     export default {
         props: {
             payment: Object
         },
         mounted() {
             this.payment.date = moment(this.payment.date).format('YYYY-MM-DD');
         },
         methods: {
             sendEmit (type) {
                 if (type === 'pay' && this.payment[type] === '') {
                     this.payment[type] = 0;
                 }
                 if (this.payment.date === '' && this.payment.payment === 0) {
                     this.$emit('delete');
                     return;
                 }
                 this.$emit('update');
             }
         }
     };
 </script>
