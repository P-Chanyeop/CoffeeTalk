// src/main/frontend/src/App.js

import React, {useEffect, useState} from 'react';
import axios from 'axios';
import Login from "./user/login";

function App() {
    const [hello, setHello] = useState('')

    /*useEffect(() => {
        // /api/test로 요청시 localhost:8080/test로 요청이 전달되는 것과 같아짐 ㅇㅋ?
        axios.get('/api/test')
            .then(response => setHello(response.data))
            .catch(error => console.log(error))
    }, []);

    return (
        <div>
            백엔드에서 가져온 데이터입니다 : {hello}
        </div>
    );*/

    return <Login/>;
}

export default App;