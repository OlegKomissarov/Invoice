import axios from 'axios';

class ExpenseApi {
    // POST: /api/invoice/:id/expense  // TODO: create endpoint
    static store (invoiceId, data, config) {
        return axios.post(`/api/invoice/${invoiceId}/expense`, data, config);
    }
    // PUT: /api/invoice/:id/expense  // TODO: create endpoint
    static update (id, data, config) {
        return axios.put(`/api/expense/${id}`, data, config);
    }
    // DELETE: /api/invoice/:id/expense  // TODO: create endpoint
    static deleteData (id, config) {
        return axios.delete(`/api/expense/${id}`, config);
    }
}

export default ExpenseApi
