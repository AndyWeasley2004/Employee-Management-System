package com.hogwarts.EmManagebackend.mapper;

import com.hogwarts.EmManagebackend.dto.EmployeeDto;
import com.hogwarts.EmManagebackend.entity.Employee;

import java.util.stream.Collectors;

public class EmployeeMapper {

    public static EmployeeDto mapToEmployeeDto(Employee employee){

        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(employee.getId());
        employeeDto.setFirstName(employee.getFirstName());
        employeeDto.setLastName(employee.getLastName());
        employeeDto.setEmail(employee.getEmail());
        employeeDto.setDepartmentId(employee.getDepartment().getId());

        if(employee.getTodos() != null){
            employeeDto.setTodos(employee.getTodos().stream().map(todo ->
                    TodoMapper.mapToTodoDto(todo))
                    .collect(Collectors.toSet()));
        }

        return employeeDto;
    }

    public static Employee mapToEmployee(EmployeeDto employeeDto){
        Employee employee = new Employee();
        employee.setId(employeeDto.getId());
        employee.setEmail(employeeDto.getEmail());
        employee.setLastName(employeeDto.getLastName());
        employee.setFirstName(employeeDto.getFirstName());

        if(employeeDto.getTodos() != null){
            employee.setTodos(employeeDto.getTodos().stream().map(todoDto ->
                    TodoMapper.mapToTodo(todoDto)).collect(Collectors.toSet()));
        }

        return employee;
    }
}
