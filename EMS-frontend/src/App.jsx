import { useState } from 'react'

import './App.css'
import ListEmployeeComponent from './components/ListEmployeeComponent'
import HeaderComponent from './components/HeaderComponent'
import FooterComponent from './components/FooterComponent'
import {BrowserRouter, Routes, Route} from 'react-router-dom'
import EmployeeComponent from './components/EmployeeComponent'

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

      </Routes>

     <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
