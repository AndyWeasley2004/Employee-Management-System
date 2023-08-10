import axios from "axios";

const TODO_REST_API_BASE = 'http://localhost:8080/api/employees/todos';

export const listEmployeeTodo = (employeeId) => axios.get(TODO_REST_API_BASE + "/" + employeeId + "/lists");

export const addTodo = (employeeId, todo) => axios.post(TODO_REST_API_BASE + "/" + employeeId + "/add", todo);

export const updateTodo = (employeeId, todoId, todo) => axios.put(TODO_REST_API_BASE + "/" + employeeId + "/update/" + todoId, todo);

export const deleteTodo = (employeeId, todoId) => axios.delete(TODO_REST_API_BASE + "/" + employeeId + "/delete/" + todoId);

export const completeTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/complete');

export const inCompleteTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/incomplete');

export const inProgressTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/progress');

export const getTodo = (employeeId, todoId) => axios.get("http://localhost:8080/api/employees/todos/" + employeeId + "/todo/" + todoId);



