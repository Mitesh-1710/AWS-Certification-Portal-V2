import './App.css';
import React,{useContext} from "react";
import logo from './logo.png';
import Login from './components/UI/Login';
import AboutUs from './components/UI/AboutUs';
// import ChangePswd from './components/UI/ChangePswd';
import Page404 from './components/UI/Page404';
import { Routes,Route,NavLink,useNavigate} from 'react-router-dom';
import AuthContext from './components/context/AuthProvider';
import Signup from './components/UI/Signup';
import Profile from './components/User/Profile';
import Certification from './components/User/Certification';
import ProfileAdmin from './components/Admin/ProfileAdmin';
import LoginAdmin from './components/UI/LoginAdmin';
import AdminBatch from './components/Admin/AdminBatch';
import AdminUser from './components/Admin/AdminUser';
import AdminCertification from './components/Admin/AdminCertification';

function App() {
   
  const navigate = useNavigate();
  const { auth } = useContext(AuthContext);
  let currentUser = auth.currentUser;
  let admin = auth.admin;
  let user = auth.user;

  return (
    <div>
      <header>
        <nav>
              <div className="logo">
                <img src={logo} alt="Capgemini Logo" title="Capgemini"/>
              </div>
              <div className="links">
               {user && (
                  <div className="links">
                    <li><NavLink to="/userProfile">PROFILE</NavLink></li>
                    <li><NavLink to="/certification">CERTIFICATION</NavLink></li>
                  </div>
                )} : {admin && (
                  <div className="links">
                    <li><NavLink to="/adminProfile">PROFILE</NavLink></li>
                    <li><NavLink to="/adminUser">USER</NavLink></li>
                    <li><NavLink to="/adminCertification">CERTIFICATION</NavLink></li>
                    <li><NavLink to="/adminBatch">BATCH</NavLink></li>
                  </div>
                )}
              
                { currentUser ? (
                    <div className="links">
                     <li><NavLink to="/" onClick={()=>{currentUser = false;admin=false;user=false; navigate("/"); localStorage.clear(); window.location.reload();}} >LOGOUT</NavLink></li>
                   </div>

                   ) : (
                    <div className="links">
                      <li><NavLink to="/about">ABOUTUS</NavLink></li>
                      <li><NavLink to="/">LOGIN</NavLink></li>
                      <li><NavLink to="/signup">SIGNUP</NavLink></li>
                      <li><NavLink to="/admin">ADMIN</NavLink></li>
                    </div>
                  )
                }
              </div>
        </nav>
      </header>
      <main>
        <Routes>
          <Route path='/' element={<Login/>} />
          <Route path='/about' element={<AboutUs/>} />
          {/* <Route path='/changePswd' element={<ChangePswd/>} /> */}
          <Route path='/*' element={<Page404/>} />
          <Route path='/userProfile' element={<Profile/>} />
          <Route path='/signup' element={<Signup/>} />
          <Route path='/certification' element={<Certification/>} />
          <Route path='/adminProfile' element={<ProfileAdmin/>} />
          <Route path='/admin' element={<LoginAdmin/>} />
          <Route path='/adminBatch' element={<AdminBatch/>} />
          <Route path='/adminUser' element={<AdminUser/>} />
          <Route path='/adminCertification' element={<AdminCertification/>} />
        </Routes>
      </main>
      <footer>
        <p>Copyright © Capgemini private limited 2022 . All rights reserved. </p>
      </footer>
    </div>
  );
}

export default App;
