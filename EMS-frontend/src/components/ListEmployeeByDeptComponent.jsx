import React, {useEffect, useState} from "react";
import {useNavigate, useParams} from "react-router-dom";
import {getAllEmployeesInDept} from "../services/DepartmentService.js";
import {deleteEmployee} from "../services/EmployeeService.js";

const ListEmployeeByDeptComponent = () => {
    const [employees, setEmployees] = useState([])

    const navigator = useNavigate();

    const {id} = useParams();

    useEffect(() => {
        getAllEmployees(id);
    }, [])

    function getAllEmployees() {
        getAllEmployeesInDept(id).then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }
    function showTodos(employeeId){
        navigator(`/employee/${employeeId}/todos`)
    }

    function updateEmployee(id) {
        navigator(`/edit-employee/${id}`)
    }

    function removeEmployee(id){
        console.log(id);

        deleteEmployee(id).then((response) =>{
            getAllEmployees();
        }).catch(error => {
            console.error(error);
        })
    }

    return (
        <div className='container'>
            <br/>
            <h2 className='text-center'>List of Employees</h2>
            <br/>
            <table className='table table-striped table-bordered'>
                <thead>
                <tr>
                    <th>Employee Id</th>
                    <th>Employee First Name</th>
                    <th>Employee Last Name</th>
                    <th>Employee Email Id</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {
                    employees.map(employee =>
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>
                                <button className='btn btn-success' onClick={() => showTodos(employee.id)}>Todos</button>
                                <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}
                                        style={{marginLeft: '15px'}}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeEmployee(employee.id)}
                                        style={{marginLeft: '10px'}}>Delete</button>
                            </td>
                        </tr>)
                }
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeByDeptComponent