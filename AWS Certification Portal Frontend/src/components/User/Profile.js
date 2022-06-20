import React, { useState , useContext} from "react";
import '../Global.css'
import AuthContext from '../context/AuthProvider'; 
import axios from 'axios';
  
const Profile = () => {

      const { auth } = useContext(AuthContext);
      const [empID,setEmpID]= useState(0);
      const [empName,setEmpName]= useState(' ');
      const [email,setEmail] = useState(' ');
      const [contactNumber,setContactNumber] = useState(0);
      const [grade,setGrade]= useState(' ');
      const [password,setPassword]= useState(' ');
      const [joiningDate,setJoiningDate]= useState(' ');
      const [batchName,setBatchName]= useState(' ');
      const [staticName,setStaticName] = useState(' ');
      const [empIdError,setEmpIDError]= useState(' ');
      const [empNameError,setEmpNameError]= useState(' ');
      const [emailError,setEmailError] = useState(' ');
      const [contactNumberError,setContactNumberError] = useState(' ');
      const [gradeError,setGradeError]= useState(' ');
      const [passwordError,setPasswordError]= useState(' ');
      const [joiningDateError,setJoiningDateError]= useState(' ');
      const [batchNameError,setBatchNameError]= useState(' ');
  
      const response={
          empID : empID,
          empName : empName,
          email : email,
          contactNumber : contactNumber,
          grade : grade,
          joiningDate : joiningDate,
          batchName : batchName,
          password: password
      }

    let getEmail = auth.email;
    let getToken = auth.accessToken;
      
    function getEmpDataByEmail(e){ 
      e.preventDefault();
      axios.get('http://localhost:8080/api/user/public/getUserByEmail/'+getEmail, {
      headers: {
            'Authorization':'Bearer '+ getToken,
            'Content-Type': 'application/json',
            'Access-Control-Allow-Origin' : 'http://localhost:3000/'
      }
            })
              .then((res) => {
                console.log(res);
                setEmpID(res.data.empID);
                setEmpName(res.data.empName);
                setEmail(res.data.email);
                setContactNumber(res.data.contactNumber);
                setGrade(res.data.grade);
                setPassword(auth.password);
                setJoiningDate(res.data.joiningDate);
                setBatchName(res.data.batchName);
                setStaticName(res.data.empName);
              })
              .catch((error) => {
                console.error(error)
            })
    }
    
    function updateEmpDataById(e){
      e.preventDefault();
      console.log(empID);
      console.log(JSON.stringify(response));

      const isValid = Validate();
      console.log(isValid);
      if(isValid){
        console.log(empID);
          fetch(`http://localhost:8080/api/user/public/updateUser/`+empID,{
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
    
    const Validate = () => {
      
      let isValid = true;
  
      const empIdError = {};
      const empNameError = {};
      const emailError = {};
      const contactNumberError = {};
      const gradeError = {};
      const passwordError = {};
      const joiningDateError = {};
      const batchNameError = {};
     
      console.log("Inside validate : " + isValid);
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
  
      if (empName.trim().length===0) {
          empNameError.empNameIsEmpty = "Employee name is required!";
          isValid = false;
         
      }
      else if (empName.trim().length <= 5){
          empNameError.empNameTooShort = "Please enter your full name";
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
  
      if (!contactNumber) {
          contactNumberError.contactNumberIsEmpty = "Contact Number is required!";
          isValid = false;
          
      }
      else if ((""+contactNumber).length < 10){
          contactNumberError.contactNumberMustNotBeLt10 = "Contact number must not be Less than 10 digit";
          isValid = false;
         
      }
      else if ((""+contactNumber).length > 10){
        contactNumberError.contactNumberMustNotBeGt10 = "Contact number must not be Greater than 10 digit";
        isValid = false;
       
    }
     
      if (password.trim().length===0) {
          passwordError.passwordIsEmpty = "Password is required";
          isValid = false;
    
      } else if (password.trim().length < 8) {
          passwordError.passwordTooShort = "Password must be equal to or more than 8 characters";
          isValid = false;
         
      } else if (password.trim().length > 15) {
          passwordError.passwordTooLong = "Password cannot exceed more than 15 characters";
          isValid = false;
         
      }
     
      if (grade.trim().length===0) {
          gradeError.gradeIsEmpty = "Grade is required";
          isValid = false;
      } else if (grade.trim().length > 2) {
          gradeError.gradeTooLong = "Grade must be equal 2 characters";
          isValid = false;
      }
  
      if (joiningDate.trim().length===0) {
          joiningDateError.joiningDateIsEmpty = "Please select date";
          isValid = false;
      }
  
      if (batchName.trim().length===0) {
          batchNameError.batchNameIsEmpty = "Please select Batch name";
          isValid = false;
      }
      
      setEmpIDError(empIdError);
      setEmpNameError(empNameError);
      setEmailError(emailError);
      setContactNumberError(contactNumberError);
      setGradeError(gradeError);
      setJoiningDateError(joiningDateError);
      setPasswordError(passwordError);
      setBatchNameError(batchNameError);
  
      return isValid;
    };
    
    return (
      <div className="login">
        <h1 onMouseEnter={getEmpDataByEmail}>Welcome Back {staticName}</h1>
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
          <br/><br/>

          <label>Employee Full Name : </label>
          <input
              type="text"
              name="empName"
              autoComplete="off"
              placeholder="Employee Name"
              value={empName}
              onChange={e =>  setEmpName(e.target.value)}
              onMouseEnter={getEmpDataByEmail}
          />
          <br/>

          {Object.keys(empNameError).map((key)=>{
              return   <span className="alerts" >{empNameError[key]}</span> 
          })}
          <br/><br/>
          <label>Email : </label>
          <input
                type="text"
                name="email"
                placeholder="Email"
                autoComplete="off"
                value={email}
                onChange={e =>  setEmail(e.target.value)}
          />
          <br/>
          {Object.keys(emailError).map((key)=>{
              return   <span className="alerts" >{emailError[key]}</span> 
          })}
          <br/><br/>

          <label>Contact Number :</label>
          <input
              type="number"
              name="contactNumber"
              autoComplete="off"
              placeholder="Contact Number"
              value={contactNumber}
              onChange={e =>  setContactNumber(e.target.value)}
          />
          <br/>

          {Object.keys(contactNumberError).map((key)=>{
              return   <span className="alerts" >{contactNumberError[key]}</span> 
          })}
          <br/><br/>

          <label>Grade : </label>
          <select className="select" value={grade}  onChange={e => setGrade(e.target.value)} disabled>
                <option value="">Select Grade</option>
                <option value="A1">A1</option>
                <option value="A2">A2</option>
                <option value="A3">A3</option>
                <option value="A4">A4</option>
          </select >
          <br/>

          {Object.keys(gradeError).map((key)=>{
              return   <span className="alerts" >{gradeError[key]}</span> 
          })}
          <br/><br/>

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
          <br/><br/>

          <label>Joining Date : </label>
          <input
                type="text"
                name="joiningDate"
                autoComplete="off"
                value={joiningDate}
                onChange={e =>  setJoiningDate(e.target.value)}
                disabled
          />
          <br/>

          {Object.keys(joiningDateError).map((key)=>{
              return   <span className="alerts" >{joiningDateError[key]}</span> 
          })}
          <br/><br/>

          <label>Batch Name : </label>
          <select className="select" value={batchName} onChange={e => setBatchName(e.target.value)} disabled>
                <option value="">Select Batch</option>
                <option value="JFS React">JFS React</option>
                <option value="JFS Angular">JFS Angular</option>
                <option value="DevOps">DevOps</option>
          </select >
          <br/>

          {Object.keys(batchNameError).map((key)=>{
              return   <span className="alerts" >{batchNameError[key]}</span> 
          })}
              
          <button className="sign-up" onClick={updateEmpDataById}>Update</button>
            
        </form>
        <br/>
      </div>
    );
}
export default Profile