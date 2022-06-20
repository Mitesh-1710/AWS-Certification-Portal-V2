import React, {useState, useEffect,useContext} from 'react'
import AuthContext from '../context/AuthProvider'
import axios from 'axios';
import '../Global.css'

//AdminUser Component
function AdminUser() {
  const [users, setUsers] = useState([]); //States for storing retrieved user
  const { auth } = useContext(AuthContext); // Calling auth context

  //storing accessToken from auth context to getToken variable
  let getToken = auth.accessToken;
  console.log(getToken);

  //calling getAllUser after every render
  useEffect(() => {
    getAllUsers();
  }, [])

  //Method to make API call for retrieving all the user records from the database
  const getAllUsers = () => {
    axios.get('http://localhost:8080/api/user/private/displayAllUsers',
    {
      headers:{
        'Authorization':'Bearer '+ getToken,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin' : 'http://localhost:3000/'
      }

    }).then((response) => {
        setUsers(response.data)
        console.log(response.data);
    }).catch(error =>{
        console.log(error);
    })
  }

    //Method to make API call for downloading the excel sheet
    const downloadExcelSheet = () => {
      console.log("Inside Excel Sheet")
      axios.get('http://localhost:8080/api/user/private/download',
      {
        headers:{
          'Authorization':'Bearer '+ getToken,
          "Content-disposition" : 'attachment; filename=[EmployeesData.xlsx]' ,
          'Content-Type': 'application/vnd.ms-excel',
          'Access-Control-Allow-Origin' : 'http://localhost:3000/'
        },
        responseType: 'arraybuffer',
        
      }).then((response) => {
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement('a');
        link.href = url;
        link.setAttribute('download', 'EmployeesData.xlsx');
        document.body.appendChild(link);
        link.click();
      }).catch(error =>{
          console.log(error);
      })
    }

  //Method to make API call for deleting user based on ID
  const deleteUser = (userId) => {
    axios.delete('http://localhost:8080/api/user/private/deleteUser/' + userId ,
    {
      headers:{
        'Authorization':'Bearer '+ getToken,
        'Content-Type': 'application/json',
        'Access-Control-Allow-Origin' : 'http://localhost:3000/'
      }
    })
    .then((response) =>{
      getAllUsers();

    }).catch(error =>{
        console.log(error);
    })
        
  }
  return (
    //Creating table for displaying retrieved data in a tabular form
  <div className='admin-user'>
    <button className='generate-report' onClick={downloadExcelSheet}>Generate Report</button>
    <table className='user-table'>
            <th> EmpID </th>
            <th> EmpName </th>
            <th> Email </th>
            <th> Contact No.</th>
            <th> Grade </th>
            <th> Joining Date</th>
            <th> Batch Name</th>
            <th> Status </th>
            <th> Trained By </th>
            <th> Mentor Name </th>
            <th> Mentor Email </th>
            <th> Reporting Manager Name </th>
            <th> Reporting Manager Email </th>
            <th> Certification Name </th>
            <th> Enrolled Status </th>
            <th> Enrolled Date </th>
            <th> Deadline Date </th>
            <th> Number Of Attempts </th>
            <th> First Attempt </th>
            <th> Second Attempt </th>
            <th> Third Attempt </th>
            <th> Voucher Status</th>
            <th> Certification Status</th>
            <th></th>
            {     
                //Printing all the data retrieved from database onto table , rows will be created based on number of data records available
                users.map( 
                    user =>
                    <tr>
                        <td> {user.empID} </td>
                        <td> {user.empName} </td>
                        <td> {user.email} </td>
                        <td> {user.contactNumber} </td>
                        <td> {user.grade} </td>
                        <td> {user.joiningDate} </td>
                        <td> {user.batchName} </td>
                        <td> {user.status} </td>
                        <td> {user.trainedBy} </td>
                        <td> {user.mentorName} </td>
                        <td> {user.mentorEmail} </td>
                        <td> {user.reportingManagerName} </td>
                        <td> {user.reportingManagerEmail} </td>
                        <td> {user.certificationName} </td>
                        <td> {user.enrolledStatus} </td>
                        <td> {user.enrolledDate} </td>
                        <td> {user.deadlineDate} </td>
                        <td> {user.numberOfAttempts} </td>
                        <td> {user.firstAttempt} </td>
                        <td> {user.secondAttempt} </td>
                        <td> {user.thirdAttempt} </td>
                        <td> {user.voucherStatus} </td>
                        <td> {user.certificationStatus} </td>
                        <td>
                            <button onClick = {() => deleteUser(user.empID)} // delete button for deleting a particular record from the database
                            className="delete-btn"> Delete</button>
                        </td>
                    </tr>
                )
            }
    </table>
  </div>
)
  
}

export default AdminUser