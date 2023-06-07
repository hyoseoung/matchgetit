import React, {useEffect, useState} from "react";
import Header from "./user/components/Header";
import Main from "./user/components/Main";
import Footer from "./user/components/Footer";
import logo from './logo.svg';
import './App.css';

function App () {
    return (
     <>
        <Header/>
        <Main/>
        <Footer/>
      </>
  );
}

export default App