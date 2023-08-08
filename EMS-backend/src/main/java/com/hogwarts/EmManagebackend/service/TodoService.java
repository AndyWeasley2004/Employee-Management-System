package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.TodoDto;

public interface TodoService {
    TodoDto findByEmployeeIdAndTodoId(Long employeeId, Long todoId);

    TodoDto createTodo(Long employeeId, TodoDto todoDto);

    TodoDto updateTodo(Long employeeId, Long todoId, TodoDto todoDto);

    void deleteById(Long employeeId, Long id);

    TodoDto completeTodo(Long id);

    TodoDto inCompleteTodo(Long id);

    TodoDto inProgressTodo(Long id);

}
