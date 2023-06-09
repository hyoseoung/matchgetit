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
