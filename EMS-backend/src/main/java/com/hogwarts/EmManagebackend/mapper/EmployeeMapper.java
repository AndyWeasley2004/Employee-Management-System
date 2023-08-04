package com.hogwarts.EmManagebackend.mapper;

import com.hogwarts.EmManagebackend.dto.EmployeeDto;
import com.hogwarts.EmManagebackend.entity.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){

        return new EmployeeDto(employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getDepartment().getId(),
                employee.getTodos().stream().map(todo ->
                        TodoMapper.mapToTodoDto(todo)).collect(Collectors.toSet()));
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setEmail(employeeDto.getEmail());
        employee.setLastName(employeeDto.getLastName());
        employee.setFirstName(employeeDto.getFirstName());

        employee.setTodos(employeeDto.getTodos().stream().map(todoDto ->
                TodoMapper.mapToTodo(todoDto)).collect(Collectors.toSet()));

        return employee;
    }
}
