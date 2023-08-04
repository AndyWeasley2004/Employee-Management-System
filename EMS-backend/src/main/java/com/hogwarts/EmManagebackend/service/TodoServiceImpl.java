package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.TodoDto;
import com.hogwarts.EmManagebackend.entity.Employee;
import com.hogwarts.EmManagebackend.entity.Todo;
import com.hogwarts.EmManagebackend.exception.ResourceNotFoundException;
import com.hogwarts.EmManagebackend.mapper.TodoMapper;
import com.hogwarts.EmManagebackend.repository.EmployeeRepository;
import lombok.AllArgsConstructor;

import java.util.Optional;

@AllArgsConstructor
public class TodoServiceImpl implements TodoService {

    private EmployeeRepository employeeRepository;

    @Override
    public TodoDto findByEmployeeIdAndTodoId(Long employeeId, Long todoId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if(!employeeOptional.isPresent()){
            throw new ResourceNotFoundException("employee does not exist for id: " + employeeId);
        }

        Employee employee = employeeOptional.get();

        Optional<TodoDto> todoDtoOptional = employee.getTodos().stream()
                .filter(todo -> todo.getId().equals(todoId))
                .map(todo -> TodoMapper.mapToTodoDto(todo)).findFirst();

        if(!todoDtoOptional.isPresent()){
            throw new ResourceNotFoundException("todo does not exist for id: " + todoId);
        }

        return todoDtoOptional.get();
    }

    @Override
    public TodoDto saveTodoDto(TodoDto todoDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(todoDto.getEmployeeId());

        if(!employeeOptional.isPresent()){
            throw new ResourceNotFoundException("employee does not exist for id: " + todoDto.getEmployeeId());
        }

        Employee employee = employeeOptional.get();

        Optional<Todo> todoOptional = employee.getTodos().stream()
                .filter(todo -> todo.getId().equals(todoDto.getId()))
                .findFirst();

        if(todoOptional.isPresent()){
            // update
            Todo todoFound = todoOptional.get();
            todoFound.setName(todoDto.getName());
            todoFound.setDescription(todoDto.getDescription());
            todoFound.setStatus(todoDto.getStatus());
        }else{
            Todo todo = TodoMapper.mapToTodo(todoDto);
            todo.setEmployee(employee);
            employee.addTodo(todo);
        }

        Employee savedEmployee = employeeRepository.save(employee);

        Optional<Todo> savedTodo = savedEmployee.getTodos().stream()
                .filter(todo -> todo.getId().equals(todoDto.getId()))
                .findFirst();
        if(!savedTodo.isPresent()){
            savedTodo = savedEmployee.getTodos().stream()
                    .filter(todo -> todo.getDescription().equals(todoDto.getDescription()))
                    .filter(todo -> todo.getStatus().equals(todoDto.getStatus()))
                    .filter(todo -> todo.getName().equals(todoDto.getName()))
                    .findFirst();
        }
        return TodoMapper.mapToTodoDto(savedTodo.get());
    }

    @Override
    public void deleteById(Long employeeId, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if(!employeeOptional.isPresent()){
            throw new ResourceNotFoundException("employee does not exist for id: " + employeeId);
        }

        Employee employee = employeeOptional.get();
        Optional<Todo> todoToDelete = employee.getTodos().stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();

        if(todoToDelete.isPresent()){
            Todo todo = todoToDelete.get();
            todo.setEmployee(null);
            employee.getTodos().remove(todo);
            employeeRepository.save(employee);
        }
    }
}
