<<<<<<< HEAD:backend/frontend/src/App.js
import React, {useEffect, useState} from "react";
import Header from "./user/components/Header";
import Main from "./user/components/Main";
import Footer from "./user/components/Footer";
import logo from './logo.svg';
import './App.css';
import UserRecruitment from "./user/pages/UserRecruitment/UserRecruitment.js";

function App () {
    return (
     <>
        <Header/>
        <UserRecruitment/>
        <Footer/>
      </>
  );
}

export default App
=======
import React, { useState } from 'react';
import Header from "./user/components/Header";
import MenuBar from "./user/components/MenuBar";
import Main from "./user/components/Main";

function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLogin = () => {
        setIsLoggedIn(true);
        console.log("로그인 성공");
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
        console.log("로그아웃 성공");
    };

    return (
        <>
            <Header isLoggedIn={isLoggedIn} onLogout={handleLogout} />
            <Main isLoggedIn={isLoggedIn} onLogin={handleLogin} onLogout={handleLogout} />
        </>
    );
}

export default App;
>>>>>>> df25ba6cc8eeaf7a12266cd896a34ba1685279e9:frontend/src/App.js
