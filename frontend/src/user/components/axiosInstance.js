import axios from 'axios';

const axiosInstance = axios.create({
    // 기본 설정
});

// Request 인터셉터 추가
axiosInstance.interceptors.request.use(
    (config) => {
        const token = sessionStorage.getItem("JwtToken"); // 세션에서 토큰을 가져옴
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`; // 헤더에 토큰을 설정
            console.log(token);
        }
        return config;
    },
    (error) => {
        return Promise.reject(error);
    }
);

export default axiosInstance;