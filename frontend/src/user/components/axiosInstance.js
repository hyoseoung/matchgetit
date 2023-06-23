import axios from 'axios';

const axiosInstance = axios.create({
    // 기본 설정
});

// Axios 인터셉터 추가
axiosInstance.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem('X-XSRF-TOKEN');
        if (token) {
            config.headers['X-XSRF-TOKEN'] = token;
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;
