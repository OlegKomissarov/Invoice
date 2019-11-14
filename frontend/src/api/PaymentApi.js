import axios from 'axios';

class PaymentApi {
    // POST: /api/payment
    static store (data, config) {
        return axios.post('/api/payment', data, config);
    }
    // PUT: /api/payment/:id
    static update (id, data, config) {
        return axios.put(`/api/payment/${id}`, data, config);
    }
    // DELETE: /api/payment/:id
    static deleteData (id, config) {
        return axios.delete(`/api/payment/${id}`, config);
    }
}

export default PaymentApi;
