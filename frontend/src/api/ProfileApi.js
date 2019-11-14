import axios from 'axios';

class ProfileApi {
    // GET: /api/profile
    static showAll (config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.get(`/api/profile`, config)
    }
    // POST: /api/profile
    static store (config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.post(`/api/profile`, 0, config)
    }
    // PUT: /api/profile
    static update (data, config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.put(`/api/profile`, data, config)
    }
    // DELETE: /api/profile
    static deleteData (config) {
        return new Promise((resolve)=>{resolve();});
        // return axios.delete(`/api/profile`, config)
    }
}

export default ProfileApi;
