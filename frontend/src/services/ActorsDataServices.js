import http from "../http-common.js";

class ActorsDataServices {
    // get by id
    getById(id) {
        return http.get(`/actors/${id}`)
    }

    // get by email
    getByEmail(email) {
        return http.get(`/actor/${email}`)
    }

    // get all
    get() {
        return http.get(`/actors`)
    }

    // add 
    post(data) {
        return http.post(`/actors`, data)
    }

    // edit
    put(id, data) {
        return http.put(`/actors/${id}`, data)
    }

    // delete one
    deleteById(id) {
        return http.delete(`/actors/${id}`)
    }

    // delete all
    delete() {
        return http.delete(`/actors`)
    }
}

export default new ActorsDataServices;