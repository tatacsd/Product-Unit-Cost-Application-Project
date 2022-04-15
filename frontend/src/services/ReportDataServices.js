import http from "../http-common.js";

class ReportDataService {
  // get all report
  get() {
    return http.get(`/report`);
  }
}

export default new ReportDataService();
