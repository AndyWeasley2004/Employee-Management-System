import React, {useEffect, useState} from 'react'
import {deleteEmployee, listEmployees} from '../services/EmployeeService'
import {Link, useNavigate} from 'react-router-dom'
import {getAllDepartments} from "../services/DepartmentService.js";

const ListEmployeeComponent = () => {
    const [employees, setEmployees] = useState([]);
    const [departments, setDepartments] = useState([]);
    const navigator = useNavigate();

    useEffect(() => {
        // Fetch employees and departments simultaneously using Promise.all
        Promise.all([listEmployees(), getAllDepartments()])
            .then((responses) => {
                const employeesResponse = responses[0];
                const departmentsResponse = responses[1];
                setEmployees(employeesResponse.data);
                setDepartments(departmentsResponse.data);
            })
            .catch((error) => {
                console.error(error);
            });
    }, []);

    function getAllEmployees(){
        listEmployees().then((response) => {
            setEmployees(response.data);
        }).catch(error => {
            console.error(error);
        })
    }

    function addNewEmployee(){
        navigator('/add-employee')
    }

    function updateEmployee(id){
        navigator(`/edit-employee/${id}`)
    }

    function removeEmployee(id){
        deleteEmployee(id).then((response) => {
            getAllEmployees();
        }).catch(error => {
            console.error(error);
        })
    }

    return(
        <div className='container'>
            <br/>
            <h2 className='text-center'>List of Employees</h2>
            <button className='btn btn-primary mb-2' onClick={addNewEmployee}>Add Employee</button>
            <table className='table table-striped table-bordered'>
                <thead>
                    <tr>
                        <th>Employee Id</th>
                        <th>Employee First Name</th>
                        <th>Employee Last Name</th>
                        <th>Employee Email Id</th>
                        <th>Employee Department</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                {employees.map(employee => {
                    const departmentName = departments.find(department => department.id === employee.departmentId)?.departmentName || 'Unknown Department';

                    return (
                        <tr key={employee.id}>
                            <td>{employee.id}</td>
                            <td>{employee.firstName}</td>
                            <td>{employee.lastName}</td>
                            <td>{employee.email}</td>
                            <td>{departmentName}</td>
                            <td>
                                <button className='btn btn-info' onClick={() => updateEmployee(employee.id)}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeEmployee(employee.id)} style={{ marginLeft: '15px' }}>Delete</button>
                                <Link to={`/employee/${employee.id}/todos`}>
                                    <button className='btn btn-success' style={{ marginLeft: '15px' }}>View Todos</button>
                                </Link>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    )
}

export default ListEmployeeComponent