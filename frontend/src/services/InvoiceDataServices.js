import http from "../http-common.js";

class InvoiceDataServices {
    // get by id
    getById(id) {
        return http.get(`/invoices/${id}`)
    }

    // get all
    get() {
        return http.get(`/invoices`)
    }

    // add 
    post(data) {
        return http.post(`/invoices`, data)
    }

    // edit
    put(id, data) {
        return http.put(`/invoices/${id}`, data)
    }

    // delete one
    deleteById(id) {
        return http.delete(`/invoices/${id}`)
    }

    // delete all
    delete() {
        return http.delete(`/invoices`)
    }
}

export default new InvoiceDataServices;