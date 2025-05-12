import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080', // Adjust if your backend runs elsewhere
    headers: {
        'Content-Type': 'application/json',
    },
});

export const registerUser = (userData) => api.post('/auth/register', userData);
export const loginUser = (userData) => api.post('/auth/login', userData);

export default api;
