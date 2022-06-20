import axios from 'axios';
import React, {useState, useEffect,useContext} from 'react'
import Thumbnail from '../certificate.JPG'
import '../Global.css'
import AuthContext from '../context/AuthProvider'

function Certification() {

    const [certification, setCertification] = useState([])
    const { auth } = useContext(AuthContext);
    const [empID,setEmpID]= useState('');
    const [empName,setEmpName]= useState('');
    const [email,setEmail] = useState('');
    const [contactNumber,setContactNumber] = useState('');
    const [grade,setGrade]= useState('');
    const [password,setPassword]= useState('');
    const [joiningDate,setJoiningDate]= useState('');
    const [batchName,setBatchName]= useState('');
    const [deadlineDate,setDeadlineDate]= useState('');
    const [certificationName,setCertificationName]= useState('');
    const [firstAttempt,setFirstAttempt]= useState('');
    const [numberOfAttempts,setNumberOfAttempts]= useState('');
    const [certificationStatus,setCertificationStatus]= useState('');

    useEffect(() => {
        getAllCertification();
        getEmpDataByEmail();
    }, [])

    const getAllCertification = () => {
        axios.get('http://localhost:8080/api/certification/public/displayAllCertification').then((response) => {
            setCertification(response.data)
            console.log(response.data);
        }).catch(error =>{
            console.log(error);
        })
    }
 
    const response={
      empID : empID,
      empName : empName,
      email : email,
      contactNumber : contactNumber,
      grade : grade,
      joiningDate : joiningDate,
      batchName : batchName,
      password: password,
      deadlineDate : deadlineDate,
      certificationName:certificationName,
      numberOfAttempts : numberOfAttempts,
      firstAttempt : firstAttempt,
      certificationStatus : certificationStatus
    
    }

    let getEmail = auth.email;
    let getToken = auth.accessToken;
      
    function getEmpDataByEmail(){ 
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
            setNumberOfAttempts(res.data.numberOfAttempts);
            setCertificationStatus(res.data.certificationStatus);
          })
          .catch((error) => {
            console.error(error)
          })
    }
    
    function updateCertificationDataById(){
      console.log(empID);
      console.log(JSON.stringify(response));
      if(window.confirm("Are you sure you want to enroll?")){
        console.log(empID);
        fetch(`http://localhost:8080/api/user/public/updateUserCertification/`+empID,{
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
            alert("Successfully Enrolled");
          })
        })
        .catch((error) => {
          console.error(error)
        })
      }
    }
       
    function updateAssessmentDataById()
    {
      console.log(JSON.stringify(response));
      if(window.confirm("Are you sure you want to give Assessment?")){
        if(certificationStatus===null)
        {
          if(firstAttempt >= 85)
          {
            alert("Congratulations you have passed the assessment test , your voucher has been emailed to you.");
          }
          else
          {
            alert("Need to do more hard work , prepare well before next attempt");
          }
            
            fetch(`http://localhost:8080/api/user/public/updateUserAssessment/`+empID,{
              method:"PUT",
              headers: {
                'Authorization':'Bearer '+ getToken,
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin' : 'http://localhost:3000/'
            },
            body:JSON.stringify(response)
          }).then((resp)=>{
              console.log("inside update: " + resp)
              if(!resp)
              {
                  alert('No Server Response');
              }
                resp.json().then(()=>{
                  alert("Successfully Gave the assessment");
              })
            }).catch((error) => {
                console.error(error)
          })
        }
        else
        {
            alert(`You cannot give Assessment : ${certificationStatus}`)
        }
        
      }
    }   
   
  return (
    <section className='section-cert'>
        {
          certification.map(
            certs => 
               
            <div className="card">
              <img src={Thumbnail} alt="Certification" title={certs.certificationName} />
              <div className="padding">
                <h2>{certs.certificationName}</h2> 
                <p>Completion Time : {certs.completionTime} days</p> 
                <a href={certs.learningPlanLink} target="_blank">Follow This Learning Pathway</a>
                <button className='enroll-btn' onMouseOver={()=>{setDeadlineDate(certs.completionTime); setCertificationName(certs.certificationName);}} onClick={updateCertificationDataById}>Enrollment</button>
                <button className='enroll-btn' onMouseEnter={getEmpDataByEmail}>Assessment</button>
                <div style={{marginTop: "10px"}}>
                  <input
                    type="number"
                    name="marks"
                    placeholder="Enter you marks"
                    autoComplete="off"
                    onChange={e =>{setFirstAttempt(e.target.value);}}
                  />
                </div>

                <button style={{color: "#0070AD"}} onClick={updateAssessmentDataById}>SUBMIT</button>

              </div>
            </div> 
          )
        }
    </section>
  )
}

export default Certification