import axios from 'axios';

class PaymentApi {
    // POST: /api/invoice/:id/payment  // TODO: create endpoint
    static store (invoiceId, data, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.post(`/api/invoice/${invoiceId}/payment`, data, config);
    }
    // PUT: /api/invoice/:id/payment  // TODO: create endpoint
    static update (id, data, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.put(`/api/payment/${id}`, data, config);
    }
    // DELETE: /api/invoice/:id/payment  // TODO: create endpoint
    static deleteData (id, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.delete(`/api/payment/${id}`, config);
    }
}

export default PaymentApi;
