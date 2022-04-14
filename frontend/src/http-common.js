import axios from "axios";

// create a axios instance
export default axios.create({
  baseURL: "http://localhost:8080/api/", // anotation added in the controller (There allows it)
  headers: {
    // specify that we are waiting for a json file
    "Content-type": "application/json",
  },
});
