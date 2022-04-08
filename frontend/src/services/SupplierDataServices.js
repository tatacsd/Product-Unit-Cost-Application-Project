import http from "../http-common.js";

class SupplierDataService {
    // get by id
    getById(id) {
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
    put(id, data) {
        return http.put(`/suppliers/${id}`, data)
    }

    // delete one
    deleteById(id) {
        return http.delete(`/suppliers/${id}`)
    }

    // delete all
    delete() {
        return http.delete(`/suppliers`)
    }
}

export default new SupplierDataService;