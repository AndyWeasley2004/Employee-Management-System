package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.TodoDto;
import com.hogwarts.EmManagebackend.entity.Employee;
import com.hogwarts.EmManagebackend.entity.Todo;
import com.hogwarts.EmManagebackend.exception.ResourceNotFoundException;
import com.hogwarts.EmManagebackend.mapper.TodoMapper;
import com.hogwarts.EmManagebackend.repository.EmployeeRepository;
import com.hogwarts.EmManagebackend.repository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class TodoServiceImpl implements TodoService {

    private EmployeeRepository employeeRepository;
    private TodoRepository todoRepository;

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Todo does not exist for id: " + id));

        todo.setStatus('3');

        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto inProgressTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Todo does not exist for id: " + id));

        todo.setStatus('2');

        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Todo does not exist for id: " + id));

        todo.setStatus('1');

        Todo savedTodo = todoRepository.save(todo);
        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto findByEmployeeIdAndTodoId(Long employeeId, Long todoId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException("employee does not exist for id: " + employeeId);
        }

        Employee employee = employeeOptional.get();

        Optional<TodoDto> todoDtoOptional = employee.getTodos().stream()
                .filter(todo -> todo.getId().equals(todoId))
                .map(todo -> TodoMapper.mapToTodoDto(todo)).findFirst();

        if (!todoDtoOptional.isPresent()) {
            throw new ResourceNotFoundException("todo does not exist for id: " + todoId);
        }

        return todoDtoOptional.get();
    }

    @Override
    public TodoDto createTodo(Long employeeId, TodoDto todoDto) {
        Optional<Employee> employeeOptional = employeeRepository.findById(todoDto.getEmployeeId());

        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException("employee does not exist for id: " + todoDto.getEmployeeId());
        }

        Employee employee = employeeOptional.get();

        Todo todo = TodoMapper.mapToTodo(todoDto);
        todo.setEmployee(employee);
        employee.addTodo(todo);

//        employeeRepository.save(employee);

        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public TodoDto updateTodo(Long employeeId, Long todoId, TodoDto todoDto) {
        TodoDto getTodoDto = findByEmployeeIdAndTodoId(employeeId, todoId);
        Todo todo = TodoMapper.mapToTodo(getTodoDto);
        todo.setName(todoDto.getName());
        todo.setStatus(todoDto.getStatus());
        todo.setDescription(todoDto.getDescription());

        Todo savedTodo = todoRepository.save(todo);

        return TodoMapper.mapToTodoDto(savedTodo);
    }

    @Override
    public void deleteById(Long employeeId, Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);

        if (!employeeOptional.isPresent()) {
            throw new ResourceNotFoundException("employee does not exist for id: " + employeeId);
        }

        Employee employee = employeeOptional.get();
        Optional<Todo> todoToDelete = employee.getTodos().stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst();

        if (todoToDelete.isPresent()) {
            Todo todo = todoToDelete.get();
            todo.setEmployee(null);
            employee.getTodos().remove(todo);
            employeeRepository.save(employee);
        }
    }
}
