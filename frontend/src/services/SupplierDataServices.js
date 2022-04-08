import http from "../http-common.js";

class SupplerDataService {
    // get by id
    get(id) {
        return http.get(`/suppliers/${id}`)
    }

    // get all
    get() {
        return http.get(`/suppliers`)
    }

    // add 
    post(data) {
        return http.post(`/suppliers`, data)
    }

    // edit
    put(data) {
        return http.put(`/suppliers/${id}`, data)
    }

    // delete one
    delete(id) {
        return http.delete(`/suppliers/${id}`)
    }

    // delete all
    delete() {
        return http.delete(`/suppliers`)
    }
}