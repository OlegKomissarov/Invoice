import axios from 'axios';

class InvoiceApi {
    // GET: /api/invoice/:id
    static show (id, config) {
        // return new Promise((resolve, reject) => {            // TODO: Add loader
        //     setTimeout(() => {
        //         resolve(axios.get(`/api/invoice/${id}`))
        //     }, 10000)
        // })
        return new Promise((resolve)=>{resolve();});
        // return axios.get(`/api/invoice/${id}`, config);
    }
    // GET: /api/invoice
    static showAll (config) {
        // TODO: use .env var API_URL
        return new Promise((resolve)=>{resolve();});
        // return axios.get(`http://localhost:8080/api/invoice`, config);
    }
    // POST: /api/invoice
    static store (data, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.post(`/api/invoice`, data, config)
    }
    // PUT: /api/invoice/:id  // TODO: create endpoint
    static update (id, data, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.put(`/api/invoice/${id}`, data, config)
    }
    // DELETE: /api/invoice/:id
    static deleteData (id, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.delete(`/api/invoice/${id}`, config)
    }
}

export default InvoiceApi;
