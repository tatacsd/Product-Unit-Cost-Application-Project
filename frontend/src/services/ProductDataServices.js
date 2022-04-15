import http from "../http-common.js";

class ProductDataService {
  // get by id
  getById(id) {
    return http.get(`/products/${id}`);
  }

  // get all
  get() {
    return http.get(`/products`);
  }

  // add
  post(data) {
    return http.post(`/products`, data);
  }

  // edit
  put(id, data) {
    return http.put(`/products/${id}`, data);
  }

  // delete one
  deleteById(id) {
    return http.delete(`/products/${id}`);
  }

  // delete all
  delete() {
    return http.delete(`/products`);
  }
}

export default new ProductDataService();
