import React, {useEffect, useState} from 'react'
import {completeTodo, deleteTodo, inCompleteTodo, inProgressTodo, listEmployeeTodo} from "../services/TodoService.js";
import {useNavigate, useParams} from "react-router-dom";


const ListTodoComponent = () => {
    const [todos, setTodos] = useState([]);
    const navigate = useNavigate();
    const { employeeId } = useParams();

    useEffect(() => {
        listTodosForEmployee(employeeId);
    }, [employeeId]);

    function listTodosForEmployee(employeeId) {
        listEmployeeTodo(employeeId)
            .then(response => {
                setTodos(response.data);
            })
            .catch(error => {
                console.error("Error fetching todos:", error);
            });
    }

    function addNewTodo() {
        navigate(`/employee/${employeeId}/add-todo`)
    }

    function updateTodo(employeeId, todoId){
        navigate(`/employee/${employeeId}/edit-todo/${todoId}`);
    }

    function removeTodo(employeeId, id){
        deleteTodo(employeeId, id).then(() => {
            listTodosForEmployee(employeeId);
        }).catch(error => {
            console.error(error);
        });
    }

    function markComplete(employeeId, todoId){
        completeTodo(todoId).then(() => {
            listTodosForEmployee(employeeId);
        }).catch(error => {
            console.error(error);
        });
    }

    function markInComplete(employeeId, todoId){
        inCompleteTodo(todoId).then(() => {
            listTodosForEmployee(employeeId);
        }).catch(error => {
            console.error(error);
        });
    }

    function markInProgress(employeeId, todoId){
        inProgressTodo(todoId).then(() => {
            listTodosForEmployee(employeeId);
        }).catch(error => {
            console.error(error);
        });
    }


    return (
        <div className='container'>
            <h2 className='text-center'>List of Todos</h2>
            <button className='btn btn-primary mb-2' onClick={addNewTodo}>Add Todos</button>
            <table className='table table-striped table-bordered'>
                <thead>
                <tr>
                    <th>Title</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {todos.map(todo => {
                    return (
                        <tr key={todo.name}>
                            <td>{todo.name}</td>
                            <td>{todo.description}</td>
                            <td>
                                {todo.status === '1' ? 'Incomplete' :
                                todo.status === '2' ? 'In Progress' :
                                    todo.status === '3' ? 'Completed' : 'Unknown'}
                            </td>

                            <td>
                                <button className='btn btn-info' onClick={() => updateTodo(employeeId, todo.id)}>Update</button>
                                <button className='btn btn-danger' onClick={() => removeTodo(employeeId, todo.id)}
                                        style={{marginLeft: '15px'}}>Delete
                                </button>
                                <button className='btn btn-success' onClick={() => markComplete(employeeId, todo.id)}
                                        style={{marginLeft: "10px"}}>Complete</button>
                                <button className='btn btn-warning' onClick={() => markInComplete(employeeId, todo.id)}
                                        style={{marginLeft: "10px"}}>In Complete</button>
                                <button className='btn btn-primary' onClick={() => markInProgress(employeeId, todo.id)}
                                        style={{marginLeft: "10px"}}>In Progress</button>
                            </td>
                        </tr>
                    );
                })}
                </tbody>
            </table>
        </div>
    )
}

export default ListTodoComponent;