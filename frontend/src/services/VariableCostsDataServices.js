import http from "../http-common.js";

class VariableCostsDataServices {
  // get by id
  getById(id) {
    return http.get(`/variableCosts/${id}`);
  }

  // get all
  get() {
    return http.get(`/variableCosts`);
  }

  // add
  post(data) {
    return http.post(`/variableCosts`, data);
  }

  // edit
  put(id, data) {
    return http.put(`/variableCosts/${id}`, data);
  }

  // delete one
  deleteById(id) {
    return http.delete(`/variableCosts/${id}`);
  }

  // delete all
  delete() {
    return http.delete(`/variableCosts`);
  }
}

export default new VariableCostsDataServices();
