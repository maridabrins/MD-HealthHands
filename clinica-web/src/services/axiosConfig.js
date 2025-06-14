import axios from "axios";

const api = axios.create({
  baseURL: "https://gestao-clinicamedica-c2edatdwbcace3gw.brazilsouth-01.azurewebsites.net",
});

// Adiciona token no header Authorization antes de cada requisição
api.interceptors.request.use(config => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
}, error => {
  return Promise.reject(error);
});

export default api;
