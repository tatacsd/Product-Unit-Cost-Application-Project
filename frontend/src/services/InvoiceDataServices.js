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

    // add a invoice
    post(data) {
        return http.post(`/invoices`, data)
    }

    postInvoiceDetail(id, data) {
        return http.post(`/invoices/${id}/details`, data)
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