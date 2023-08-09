import axios from "axios";

const TODO_REST_API_BASE = 'http://localhost:8080/api/employees/todos';

export const listEmployeeTodo = (employeeId) => axios.get(TODO_REST_API_BASE + "/" + employeeId + "/lists");

export const addTodo = (employeeId) => axios.post(TODO_REST_API_BASE + "/" + employeeId + "/add");

export const updateTodo = (employeeId, todoId) => axios.put(TODO_REST_API_BASE + "/" + employeeId + "/update/" + todoId);

export const deleteTodo = (employeeId, todoId) => axios.delete(TODO_REST_API_BASE + "/" + employeeId + "/delete/" + todoId);

export const completeTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/complete');

export const inCompleteTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/incomplete');

export const inProgressTodo = (todoId) => axios.patch(TODO_REST_API_BASE + "/" + todoId + '/progress');



