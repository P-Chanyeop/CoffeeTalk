import axios from "axios";
import { useEffect, useState } from "react";

const Login = () => {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");

    // const defaultValues = {
    //     email: "",
    //     password: "",
    // };

    const Sign_in = async (e) => {
        e.preventDefault();

        const response = await axios
            .post("/user/sign_in", {username, password})
            .then((response) => console.log(response))
            .catch((error) => console.error(error));

        return response;
    };

    // useEffect(() => {}, []);

    return (
        <div>
            <form
                name="login"
                action="/user/sign_in"
                method="post"
                onSubmit={Sign_in}
            >
                <input
                    type="text"
                    name="username"
                    value={username}
                    onChange={(e) => setUsername(e.target.value)}
                    placeholder="username"
                    required
                ></input>
                <input
                    type="password"
                    name="password"
                    value={password}
                    onChange={(e) => setPassword(e.target.value)}
                    placeholder="password"
                    required
                ></input>
                <button type="submit">로그인</button>
            </form>
        </div>
    );
};

export default Login;
