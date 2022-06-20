import React, { useState} from "react";
import '../Global.css'
const Signup = () => {
    const [empID,setEmpID]= useState('');
    const [empName,setEmpName]= useState('');
    const [email,setEmail] = useState('');
    const [contactNumber,setContactNumber] = useState('');
    const [grade,setGrade]= useState('');
    const [password,setPassword]= useState('');
    const [joiningDate,setJoiningDate]= useState('');
    const [batchName,setBatchName]= useState('');

    const [empIdError,setEmpIDError]= useState('');
    const [empNameError,setEmpNameError]= useState('');
    const [emailError,setEmailError] = useState('');
    const [contactNumberError,setContactNumberError] = useState('');
    const [gradeError,setGradeError]= useState('');
    const [passwordError,setPasswordError]= useState('');
    const [joiningDateError,setJoiningDateError]= useState('');
    const [batchNameError,setBatchNameError]= useState('');

    const response={
        empID : empID,
        empName : empName,
        email : email,
        contactNumber : contactNumber,
        grade : grade,
        password : password,
        joiningDate : joiningDate,
        batchName : batchName
    }
    function handleSubmit(e){ 
        e.preventDefault()
        const isValid=Validate();
        if(isValid){
           fetch('http://localhost:8080/api/user/all/signup',{
                method:"POST",
                headers: {
                //  "Authorization":"Bearer "+auth.accessTokens,
                "Content-Type": "application/json; charset=utf-8"
            },
            body:JSON.stringify(response)
               }).then((resp)=>{
                   
                if(!resp)
                {
                 alert('No Server Response');
                }else if(resp.status === 409){
                 alert('Employee ID or Email Already Exist');
                }
                   resp.json().then(()=>{
                      alert("Successfully Registered");
                      window.location.reload();
                   })
               }).catch((error)=>{ 
                   console.log(error);
                   
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
   

    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/i;
    
    if (!empID) {
        empIdError.empIdIsEmpty = "Employee ID is required!";
        isValid = false;
    }
    else if((""+empID) >= 101)
    {
        empIdError.empIdMustBeInBetween1To100 = "Employee ID must be in between 1 to 100";
        isValid = false;
        
    }
    else if((""+empID) <= 0)
    {
      empIdError.empIdMustBeInBetweenNegativeOrZero = "Employee ID must be Negative or Zero";
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
    setPasswordError(passwordError);
    setJoiningDateError(joiningDateError);
    setBatchNameError(batchNameError);

    return isValid;
  };

  return (
    <div className="login">
        <h1>SIGN UP</h1>
      <form onSubmit={handleSubmit}>   
        <input
          type="number"
          name="empID"
          placeholder="Employee ID"
          autoFocus="1" 
          autoComplete="off"
          onChange={e => setEmpID(e.target.value)}
          data-testid="empId-element"
          value={empID}
        />
        <br/>

        {Object.keys(empIdError).map((key)=>{
          return  <span className="alerts">{empIdError[key]}</span> 
        })}
        <br/><br/>
        
        <input
          type="text"
          name="empName"
          autoComplete="off"
          placeholder="Employee Name"
          onChange={e =>  setEmpName(e.target.value)}
          data-testid="empName-element"
          value={empName}
        />
        <br/>

        {Object.keys(empNameError).map((key)=>{
          return  <span className="alerts" >{empNameError[key]}</span> 
        })}
        <br/><br/>

        <input
          type="text"
          name="email"
          placeholder="Email"
          autoComplete="off"
          onChange={e =>  setEmail(e.target.value)}
          data-testid="email-element"
          value={email}
        />
        <br/>

        {Object.keys(emailError).map((key)=>{
          return  <span className="alerts" >{emailError[key]}</span> 
        })}
        <br/><br/>

        <input
          type="number"
          name="contactNumber"
          autoComplete="off"
          placeholder="Contact Number"
          onChange={e =>  setContactNumber(e.target.value)}
          data-testid="contactNo-element"
          value={contactNumber}
        />
        <br/>

        {Object.keys(contactNumberError).map((key)=>{
          return  <span className="alerts" >{contactNumberError[key]}</span> 
        })}
        <br/><br/>

        <select className="select" role="grades" value={grade} onChange={e => setGrade(e.target.value)}>
          <option role="grade" value="" defaultValue="Select Grade">Select Grade</option>
          <option value="A1">A1</option>
          <option value="A2">A2</option>
          <option value="A3">A3</option>
	        <option value="A4">A4</option>
        </select >
        <br/>

        {Object.keys(gradeError).map((key)=>{
           return  <span className="alerts" >{gradeError[key]}</span> 
        })}
        <br/><br/>

        <input
          type="password"
          name="password"
          autoComplete="off"
          placeholder="Password"
          onChange={e =>  setPassword(e.target.value)}
          data-testid="pass-element"
          value={password}
        />
        <br/>

        {Object.keys(passwordError).map((key)=>{
          return  <span className={passwordError[key] ? "alerts" : "offscreen"} aria-live="assertive"  >{passwordError[key]}</span> 
        })}
        <br/><br/>

        <input
          type="date"
          name="joiningDate"
          autoComplete="off"
          onChange={e =>  setJoiningDate(e.target.value)}
          data-testid="date-element"
          value={joiningDate}
        />
        <br/>
        {Object.keys(joiningDateError).map((key)=>{
          return   <span className="alerts" >{joiningDateError[key]}</span> 
        })}
        <br/><br/>

        <select className="select" role="batches" value={batchName} onChange={e => setBatchName(e.target.value)}>
          <option role="batch" value=""  defaultValue="Select Batch">Select Batch</option>
          <option value="JFS React">JFS React</option>
          <option value="JFS Angular">JFS Angular</option>
          <option value="DevOps">DevOps</option>
        </select>
        <br/>

        {Object.keys(batchNameError).map((key)=>{
          return  <span className="alerts" >{batchNameError[key]}</span> 
        })}
        <br/>

        <input type="submit" value="Submit" data-testid="submit-element"/>  
      </form>
      <br/>
    </div>
  );
}
export default Signup