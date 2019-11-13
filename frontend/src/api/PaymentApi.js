import axios from 'axios';

class PaymentApi {
    // POST: /api/invoice/:id/payment  // TODO: create endpoint
    static store (invoiceId, data, config) {
        return axios.post(`/api/invoice/${invoiceId}/payment`, data, config);
    }
    // PUT: /api/invoice/:id/payment  // TODO: create endpoint
    static update (id, data, config) {
        return axios.put(`/api/payment/${id}`, data, config);
    }
    // DELETE: /api/invoice/:id/payment  // TODO: create endpoint
    static deleteData (id, config) {
        return axios.delete(`/api/payment/${id}`, config);
    }
}

export default PaymentApi
