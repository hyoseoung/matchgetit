import React, { useState } from 'react';
import Header from "./user/components/Header";
import Main from "./user/components/Main";


function App() {
    const [isLoggedIn, setIsLoggedIn] = useState(false);

    const handleLogin = () => {
        setIsLoggedIn(true);
    };

    const handleLogout = () => {
        setIsLoggedIn(false);
    };

    return (
        <>
            <Header isLoggedIn={isLoggedIn} onLogout={handleLogout} />
            <Main isLoggedIn={isLoggedIn} onLogin={handleLogin} onLogout={handleLogout} />
        </>
    );
}

export default App;
