import axios from 'axios';

class InvoiceApi {
    // GET: /api/invoice/:id
    static show (id, config) {
        // return new Promise((resolve, reject) => {            // TODO: Add loader
        //     setTimeout(() => {
        //         resolve(axios.get(`/api/invoice/${id}`))
        //     }, 10000)
        // })
        return axios.get(`/api/invoice/${id}`, config);
    }
    // GET: /api/invoice
    static showAll (config) {
        return axios.get('/api/invoice', config);
    }
    // POST: /api/invoice
    static store (data, config) {
        return axios.post('/api/invoice', data, config);
    }
    // PUT: /api/invoice/:id
    static update (id, data, config) {
        return axios.put(`/api/invoice/${id}`, data, config);
    }
    // DELETE: /api/invoice/:id
    static deleteData (id, config) {
        return axios.delete(`/api/invoice/${id}`, config);
    }
}

export default InvoiceApi;
