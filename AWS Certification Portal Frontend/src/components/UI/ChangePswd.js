// import React from 'react'
// import '../Global.css'


// function ChangePswd() {
//   const requestOptionsGet = {
//     method: 'GET',
//     headers: {
//       'Content-Type': 'application/json',
//       'Authorization' : 'Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtaXRlc2hAZ21haWwuY29tIiwiaWF0IjoxNjUzOTE1MjY5LCJleHAiOjE2NTM5MTg4Njl9.JrY-d3N3Ub5Vs-DuZprbkL44Gq4-K0OVuIY9nvdPRsn7GdVXe032n9dOUoNRYW2y91gsQDh0xVW9JLbJxQwxXw'
//     }, }
//   const fetchData=async()=>{
//     try{
//     let response = await fetch(`http://localhost:8080/api/user/public/getUserByEmail/vaibhav.kam@gmail.com`,requestOptionsGet)
//     let data = await response.json();
//           console.log(data)
//     }catch(err){
//          console.log(err)
//     }
//   }

//   return (
//     <section className="container">
//         <div className="change-pswd">
//             <h1>Change Password</h1>
//             <button onClick={fetchData}>click</button>
//         </div>
//     </section>
//   )
// }

// export default ChangePswd