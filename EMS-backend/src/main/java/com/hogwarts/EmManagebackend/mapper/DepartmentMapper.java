package com.hogwarts.EmManagebackend.mapper;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.entity.Department;

import java.util.stream.Collectors;

public class DepartmentMapper {
    // convert department to departmentDto
    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription(),
                department.getEmployees().stream()
                        .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                        .collect(Collectors.toSet())
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription(),
                departmentDto.getEmployees().stream()
                        .map(employeeDto -> EmployeeMapper.mapToEmployee(employeeDto))
                        .collect(Collectors.toSet())
        );
    }
}
