import { useRef, useState, useEffect, useContext } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from "../context/AuthProvider";
import axios from 'axios';
import '../Global.css'

const LoginAdmin = () => {
    const { setAuth } = useContext(AuthContext);
    const userRef = useRef();
    const errRef = useRef();

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const [errMsg, setErrMsg] = useState('');
    const [success, setSuccess] = useState(false);

    
    const navigate = useNavigate();
    useEffect(() => {
        userRef.current.focus();
    }, [])

    useEffect(() => {
        setErrMsg('');
    }, [email, password])

    function getCred(email){ 
        axios.get('http://localhost:8080/api/user/cred/getCredDataById/'+email, {
        headers: {
              'Content-Type': 'application/json',
        }
            })
            .then((res) => {
                if(res.data.role !== "ROLE_ADMIN")
                {
                    navigate("/admin");
                    window.location.reload();
                    
                }
            })
                .catch((error) => {
                  console.error(error)
            })
      }

    const handleSubmit = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/user/all/signin",
                
                JSON.stringify({ email, password }),
                {

                    headers: { 'Content-Type': 'application/json' },
                    withCredentials: true
                }
            );
            console.log(JSON.stringify(response.data.accessToken));
           // console.log("Login" + JSON.stringify(response));
            const accessToken = response?.data?.accessToken;
            getCred(email);
            setAuth({ email, password, accessToken,currentUser:true,user:false,admin:true});
            setEmail('');
            setPassword('');
            setSuccess(true);
            
            // if(accessToken){
            //     localStorage.setItem("user", JSON.stringify(response.data.accessToken));
            //     localStorage.setItem("login", "true");
            // }
           
        } catch (err) {
            if (!err?.response) {
                setErrMsg('No Server Response');
            }else if (err.response?.status === 401) {
                setErrMsg('Wrong Email or Password');
            } else {
                setErrMsg('Login Failed');
            }
            errRef.current.focus();
        }
    }

    return (
        <>
            {success ? (
               
                navigate("/adminProfile")
                
            ) : (
                <section style={{height: "100%"}}>
                    <div className="login" style={{height: "100%",overflow:'hidden'}}>
                        <br/>
                        <span ref={errRef} className={errMsg ? "alert" : "offscreen"} aria-live="assertive">{errMsg}</span>
                        <h1 id='check'>SIGN IN</h1>
                        <form onSubmit={handleSubmit}>
                            <input
                                type="text"
                                id="email"
                                ref={userRef}
                                placeholder="Email" 
                                autofocus="1" 
                                autocomplete="off"
                                onChange={(e) => setEmail(e.target.value)}
                                value={email}
                                required
                            />
                            <br/>
                            <input
                                type="password"
                                id="password"
                                placeholder="Password" 
                                autocomplete="off"
                                onChange={(e) => setPassword(e.target.value)}
                                value={password}
                                required
                            />
                            <br/>
                            <button class="sign-in">LOG IN</button>
                        </form>
                    </div>
                </section>
            )}
        </>
    )
}

export default LoginAdmin