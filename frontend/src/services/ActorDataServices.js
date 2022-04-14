import http from "../http-common.js";

class ActorDataServices {
    // get by id
    getById(id) {
        return http.get(`/actors/${id}`)
    }

    // get by email
    getById(email) {
        return http.get(`/actors/${email}`)
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

export default new ActorDataServices;