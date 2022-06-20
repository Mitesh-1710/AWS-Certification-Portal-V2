import {render,screen,fireEvent} from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect';
import Login from './Login'

//Input Fields test
//email
test("Email should be rendered", ()=>{
    render(<Login/>);
    const emailElement = screen.getByTestId('email-element');
    expect(emailElement).toBeInTheDocument()
})

test("Email input should change",()=>{
    render(<Login/>)
    const emailInput = screen.getByTestId('email-element');
    const testValue="mitesh@gmail.com"
    fireEvent.change(emailInput,{target:{value:testValue}});
    expect(emailInput.value).toBe(testValue)
})

//password
test("Password should be rendered", ()=>{
    render(<Login/>);
    const passwordElement = screen.getByTestId('pass-element');
    expect(passwordElement).toBeInTheDocument()
})
test("Password input should change",()=>{
    render(<Login/>)
    const passwordInput = screen.getByTestId('pass-element');
    const testValue="mitesh123"
    fireEvent.change(passwordInput,{target:{value:testValue}});
    expect(passwordInput.value).toBe(testValue)
})

//login button
test("Login button should be rendered", ()=>{
    render(<Login/>);
    const loginElement = screen.getByTestId('login-element');
    expect(loginElement).toBeInTheDocument()
})

test("Login button should have text LOG IN", ()=>{
    render(<Login/>);
    const loginElement = screen.getByTestId('login-element');
    expect(loginElement).toHaveTextContent("LOG IN");
})

//H1 tag Sign In text
test("H1 tag should have text SIGN IN", ()=>{
    render(<Login/>);
    const loginElement = screen.getByTestId('sign-in-element');
    expect(loginElement).toHaveTextContent("SIGN IN");
})


//jest.mock("axios");
// test("Credentials should be posted to the database on api call", async () => {
// //     render(<Login />);
// //     const loginElement = screen.getByTestId('sign-in-element');
// //     const emailElement = screen.getByTestId('email-element');
// //     const passwordElement = screen.getByTestId('pass-element');
  
  
// //     fireEvent.change(emailElement, { target: { name: "email", value: "mitesh@gmail.com" } });
// //     fireEvent.change(passwordElement, { target: { name:"password" , value: "mitesh123" } });
// //     fireEvent.click(loginElement);
// // console.log(emailElement);
// // console.log(passwordElement);
  
// //     expect(axios.post).toHaveBeenCalledWith(
// //         "http://localhost:8080/api/user/all/signin",
// //         expect.objectContaining({
// //             email:"mitesh@gmail.com",
// //             password:"mitesh123"
// //         })
// //     );

//     const message = "error";
//     axios.post.mockRejectedValueOnce(new Error (message));

//     const result = "mitesh@gmail.com"

//     expect(axios.post).toHaveBeenCalledWith('http://localhost:8080/api/user/all/signin');
//     expect(result).toEqual([]);

//   });

//mocking axios for post api request testing
// jest.mock("axios", () => ({
//    ...jest.requireActual('axios'),
//    post: jest.fn(),
// }))
// const request = require('./Login');
// const fs = require('fs');
// let userID;

// describe("POST request", () => {
  
//     try{
//       let userDetails;
//       beforeEach(function () {  
//           console.log("Input user details!")
//           userDetails = {
//             "email": "mitesh@gmail.com",
//             "password": "mitesh123"
//         }; //new user details to be created
//         });
      
//       afterEach(function () {
//         console.log("User is created with ID : ", userID)
//       });
  
//         it("Create user data", async done => {
  
//           return request.request.post(`http://localhost:8080/api/user/all/signin`) //post() of supertest
//                   //.set('Authorization', `Token $  {request.token}`) //Authorization token
//                   .send(userDetails) //Request header
//                   .expect(201) //response to be 201
//                   .then((res) => {
//                       expect(res.body).toBeDefined(); //test if response body is defined
//                       //expect(res.body.status).toBe("success")
//                       userID = res.body.id;
//                       let jsonContent = JSON.stringify({userId: res.body.id}); // create a json
//                       fs.writeFile("data.json", jsonContent, 'utf8', function (err) //write user id into global json file to be used 
//                       {
//                       if (err) {
//                           return console.log(err);
//                       }
//                       console.log("POST response body : ", res.body)
//                       done();
//                       });
//                     })
//                   })
//                 }
//                 catch(err){
//                   console.log("Exception : ", err)
//                 }
//           });

