import axios from 'axios';

class ExpenseApi {
    // POST: /api/expense
    static store (data, config) {
        return axios.post('/api/expense', data, config);
    }
    // PUT: /api/expense/:id
    static update (id, data, config) {
        return axios.put(`/api/expense/${id}`, data, config);
    }
    // DELETE: /api/expense/:id
    static deleteData (id, config) {
        return axios.delete(`/api/expense/${id}`, config);
    }
}

export default ExpenseApi;
