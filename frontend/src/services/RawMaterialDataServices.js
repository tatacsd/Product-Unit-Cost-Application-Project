import http from "../http-common.js";

class RawMaterialDataService {
  // get by id
  getById(id) {
    return http.get(`/rawMaterials/${id}`);
  }

  // get all
  get() {
    return http.get(`/rawMaterials`);
  }

  // add
  post(data) {
    return http.post(`/rawMaterials`, data);
  }

  // edit
  put(id, data) {
    return http.put(`/rawMaterials/${id}`, data);
  }

  // delete one
  deleteById(id) {
    return http.delete(`/rawMaterials/${id}`);
  }

  // delete all
  delete() {
    return http.delete(`/rawMaterials`);
  }
}

export default new RawMaterialDataService();
