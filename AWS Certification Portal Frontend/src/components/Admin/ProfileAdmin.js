import React, { useState , useContext} from "react";
import '../Global.css'
import AuthContext from '../context/AuthProvider'; 
import axios from 'axios';

//Admin Profile Component
function ProfileAdmin() {

  const { auth } = useContext(AuthContext);
  const [empID,setEmpID]= useState('');
  const [email,setEmail] = useState('');
  const [password,setPassword]= useState('');
  const [empIdError,setEmpIDError]= useState('');
  const [emailError,setEmailError] = useState('');
  const [passwordError,setPasswordError]= useState('');


  //Response object for storing the retrieved data
  const response={
      empID : empID,
      email : email,
      password: password
  }

  //Storing info from auth context to variable
  let getEmail = auth.email;
  let getToken = auth.accessToken;
  
  //Method to make an API call for retrieving particular user data based on his/her email
  function getEmpDataByEmail(e){ 
    e.preventDefault();
    axios.get('http://localhost:8080/api/admin/private/getAdminByEmail/'+getEmail, {
    headers: {
          'Authorization':'Bearer '+ getToken,
          'Content-Type': 'application/json',
          'Access-Control-Allow-Origin' : 'http://localhost:3000/'
    }
          })
            .then((res) => {
              console.log(res);
              setEmpID(res.data.empID);
              setEmail(res.data.email);
              setPassword(auth.password);
            })
            .catch((error) => {
              console.error(error)
          })
  }

  //Method to make an API call for updating particular user data based on his/her ID
  function updateEmpDataById(e){
    e.preventDefault();
    console.log(empID);
    console.log(JSON.stringify(response));

    //Checking for validation 
    const isValid = Validate();

    //If no errors found execute the block for updating the user data
    if(isValid){
      console.log(empID);
        fetch(`http://localhost:8080/api/admin/private/updateAdmin/`+empID,{
          method:"PUT",
          headers: {
            'Authorization':'Bearer '+ getToken,
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin' : 'http://localhost:3000/'
        },
        body:JSON.stringify(response)
        }).then((resp)=>{
          if(!resp)
          {
              alert('No Server Response');
          }
            resp.json().then(()=>{
              alert("Successfully Updated");
          })
        }).catch((error) => {
            console.error(error)
        })
    }
  }
  
  // Method to validate the user input while submitting form
  const Validate = () => {
    
    let isValid = true;

    const empIdError = {};
    const emailError = {};
    const passwordError = {};
   
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    
    if (!empID) {
        empIdError.empIdIsEmpty = "Employee ID is required!";
        isValid = false;
    }
    else if((""+empID).length > 3)
    {
        empIdError.empIdMustBeInBetween1To100 = "Employee ID must be in between 1 to 100";
        isValid = false;
        
    }
    
    if (email.trim().length===0) {
        emailError.emailIsEmpty = "Email is required!";
        isValid = false;
       
    } 
    else if (!regex.test(email)) {
        emailError.emailIsInvalid = "This is not a valid email format!";
        isValid = false;
       
    }
   
    if (password.trim().length===0) {
        passwordError.passwordIsEmpty = "Password is required";
        isValid = false;
  
    } else if (password.length < 8) {
        passwordError.passwordTooShort = "Password must be equal to or more than 8 characters";
        isValid = false;
       
    } else if (password.length > 15) {
        passwordError.passwordTooLong = "Password cannot exceed more than 15 characters";
        isValid = false;
       
    }
   
    
    setEmpIDError(empIdError);
    setEmailError(emailError);
    setPasswordError(passwordError);

    return isValid;
  };
  
  return (

    // form for displaying/updating  retrieved/updated record of a user from database
    <div className="login">
       
        <h1 >Welcome Back Admin</h1>
      <form> 
           <label>Employee ID : </label>
            <input
              type="number"
              name="empID"
              placeholder="Employee ID"
              autoFocus="1" 
              autoComplete="off"
              value={empID}
              onChange={e => setEmpID(e.target.value)}
              disabled
             />
             <br/>
             {Object.keys(empIdError).map((key)=>{
                return   <span className="alerts">{empIdError[key]}</span> 
             })}
          <br/>
          <br/>
           <label>Email : </label>
            <input
              type="text"
              name="email"
              placeholder="Email"
              autoComplete="off"
              value={email}
              onChange={e =>  setEmail(e.target.value)}
              onMouseEnter={getEmpDataByEmail}
            />
        <br/>
             {Object.keys(emailError).map((key)=>{
                return   <span className="alerts" >{emailError[key]}</span> 
             })}
          <br/>
          <br/>
          <label>Password : </label>
          <input
            type="text"
            name="password"
            autoComplete="off"
            placeholder="Password"
            value={auth.password}
            onChange={e =>  setPassword(e.target.value)}
          />
      <br/>
          {Object.keys(passwordError).map((key)=>{
              return   <span className={passwordError[key] ? "alerts" : "offscreen"} aria-live="assertive"  >{passwordError[key]}</span> 
           })}
      
           {/* update button for updating the user data on the database */}
           <button className="sign-up" onClick={updateEmpDataById}>Update</button> 
          
      </form>
      <br/>
    </div>
  );
}

export default ProfileAdmin