package com.hogwarts.EmManagebackend.controller;


import com.hogwarts.EmManagebackend.dto.TodoDto;
import com.hogwarts.EmManagebackend.service.EmployeeService;
import com.hogwarts.EmManagebackend.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/api/employees/todos")
@RestController
public class TodoController {
    private EmployeeService employeeService;
    private TodoService todoService;

    @GetMapping("/{id}/lists")
    public ResponseEntity<Set<TodoDto>> listTodoOnEmployee(@PathVariable("id") Long employeeId){
        Set<TodoDto> todos = employeeService.getEmployeeById(employeeId).getTodos();

        return ResponseEntity.ok(todos);
    }

    @PostMapping("/{id}/add")
    public ResponseEntity<TodoDto> addTodoToEmployee(@PathVariable("id") Long employeeId,
                                                     @RequestBody TodoDto todo){
        // pass employeeId to service
        TodoDto savedTodo = todoService.createTodo(employeeId, todo);

        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @PutMapping("/{employeeId}/update/{id}")
    public ResponseEntity<TodoDto> updateTodo(@PathVariable Long employeeId, @PathVariable Long id,
                                              @RequestBody TodoDto todoDto){
        TodoDto updatedTodo = todoService.updateTodo(employeeId, id, todoDto);

        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/{employeeId}/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long employeeId, @PathVariable Long id){
        todoService.deleteById(employeeId, id);

        return ResponseEntity.ok("Todo Delete Successfully");
    }

    @PatchMapping("/{id}/complete")
    public ResponseEntity<TodoDto> completeTodo(@PathVariable Long id){
        TodoDto completed = todoService.completeTodo(id);

        return ResponseEntity.ok(completed);
    }

    @PatchMapping("/{id}/incomplete")
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable Long id){
        TodoDto todo = todoService.inCompleteTodo(id);

        return ResponseEntity.ok(todo);
    }

    @PatchMapping("/{id}/progress")
    public ResponseEntity<TodoDto> inProgressTodo(@PathVariable Long id){
        TodoDto todo = todoService.inProgressTodo(id);

        return ResponseEntity.ok(todo);
    }


}
