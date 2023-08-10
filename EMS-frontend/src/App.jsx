import {useState} from 'react'

import './App.css'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import {BrowserRouter, Route, Routes} from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'
import ListDepartmentComponent from "./components/ListDepartmentComponent.jsx";
import DepartmentComponent from "./components/DepartmentComponent.jsx";
import ListTodoComponent from "./components/ListTodoComponent.jsx";
import TodoComponent from "./components/TodoComponent.jsx";

function App() {
  const [count, setCount] = useState(0)

  return (
    <>
    <BrowserRouter>
     <HeaderComponent />

      <Routes>
        <Route path='/' element = { <ListEmployeeComponent />}></Route>
        {/* // http://localhost:3000/employees */}
        <Route path='/employees' element = { <ListEmployeeComponent />}></Route>
        <Route path='/add-employee' element = { <EmployeeComponent />}></Route>
        <Route path='/edit-employee/:id' element = { <EmployeeComponent /> }></Route>
          <Route path='/departments' element = { <ListDepartmentComponent />}></Route>
          <Route path='/add-department' element = { <DepartmentComponent />}></Route>
          <Route path='/edit-department/:id' element = { <DepartmentComponent />}></Route>
          <Route path='/employee/:employeeId/todos' element = { <ListTodoComponent />}></Route>
          <Route path='/employee/:employeeId/add-todo' element = { <TodoComponent />}></Route>
          <Route path='/employee/:employeeId/edit-todo/:todoId' element = { <TodoComponent />}></Route>
          <Route path='/employee/:employeeId/edit-todo/:todoId' element = { <TodoComponent />}></Route>

      </Routes>

     <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
