import {render,screen,fireEvent} from '@testing-library/react'
import '@testing-library/jest-dom/extend-expect';
import Signup from './Signup'

//Input Fields test
//empID
test("Employee ID should be rendered", ()=>{
    render(<Signup/>);
    const empIdElement = screen.getByTestId('empId-element');
    expect(empIdElement).toBeInTheDocument()
})

//empName
test("Employee Name should be rendered", ()=>{
    render(<Signup/>);
    const empNameElement = screen.getByTestId('empName-element');
    expect(empNameElement).toBeInTheDocument()
})

test("Email should be rendered", ()=>{
    render(<Signup/>);
    const emailElement = screen.getByTestId('email-element');
    expect(emailElement).toBeInTheDocument()
})

test("Contact Number should be rendered", ()=>{
    render(<Signup/>);
    const contactNumberElement = screen.getByTestId('contactNo-element');
    expect(contactNumberElement).toBeInTheDocument()
})


//select option for Grade
test('Select should correctly set default option', () => {
    render(<Signup/>)
    expect(screen.getByRole('grade').selected).toBe(true)
})

test('Select should display 5 number of options', () => {
    render(<Signup/>)
    expect(screen.getByRole('grades').length).toBe(5)
})



test("Password should be rendered", ()=>{
    render(<Signup/>);
    const passwordElement = screen.getByTestId('pass-element');
    expect(passwordElement).toBeInTheDocument()
})


test("Joining Date should be rendered", ()=>{
    render(<Signup/>);
    const joiningDateElement = screen.getByTestId('date-element');
    expect(joiningDateElement).toBeInTheDocument()
})

//select option for Batch
test('Select should correctly set default option', () => {
    render(<Signup/>)
    expect(screen.getByRole('batch').selected).toBe(true);
})

test('Select should display 4 number of options', () => {
    render(<Signup/>)
    expect(screen.getByRole('batches').length).toBe(4)
})

test("Joining Date should be rendered", ()=>{
    render(<Signup/>);
    const joiningDateElement = screen.getByTestId('submit-element');
    expect(joiningDateElement).toBeInTheDocument()
})

test("Sign up text should be rendered", ()=>{
    render(<Signup/>);
    const h1Element = screen.getByText(/sign up/i)
    expect(h1Element).toBeInTheDocument()
})
