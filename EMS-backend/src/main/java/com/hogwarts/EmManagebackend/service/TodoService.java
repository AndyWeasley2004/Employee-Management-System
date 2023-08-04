package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.TodoDto;

public interface TodoService {
    TodoDto findByEmployeeIdAndTodoId(Long employeeId, Long todoId);

    TodoDto saveTodoDto(TodoDto todoDto);

    void deleteById(Long employeeId, Long id);
}
