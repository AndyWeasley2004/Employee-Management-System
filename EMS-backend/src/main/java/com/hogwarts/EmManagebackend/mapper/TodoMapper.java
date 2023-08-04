package com.hogwarts.EmManagebackend.mapper;

import com.hogwarts.EmManagebackend.dto.TodoDto;
import com.hogwarts.EmManagebackend.entity.Employee;
import com.hogwarts.EmManagebackend.entity.Todo;

public class TodoMapper {

    public static Todo mapToTodo(TodoDto todoDto){
        Todo todo = new Todo();
        todo.setId(todoDto.getId());
        todo.setName(todoDto.getName());
        todo.setDescription(todoDto.getDescription());
        todo.setStatus(todoDto.getStatus());

        if(todoDto.getEmployeeId() != null){
            Employee employee = new Employee();
            employee.setId(todoDto.getEmployeeId());
            todo.setEmployee(employee);
            employee.getTodos().add(todo);
        }

        return todo;
    }

    public static TodoDto mapToTodoDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.setId(todo.getId());
        todoDto.setName(todo.getName());
        todoDto.setDescription(todo.getDescription());
        todoDto.setStatus(todo.getStatus());

        if(todo.getEmployee() != null)
            todoDto.setEmployeeId(todo.getEmployee().getId());

        return todoDto;
    }
}
