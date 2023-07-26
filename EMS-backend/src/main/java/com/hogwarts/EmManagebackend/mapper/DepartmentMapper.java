package com.hogwarts.EmManagebackend.mapper;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.entity.Department;

public class DepartmentMapper {
    // convert department to departmentDto
    public static DepartmentDto mapToDepartmentDto(Department department){
        return new DepartmentDto(
                department.getId(),
                department.getDepartmentName(),
                department.getDepartmentDescription()
        );
    }

    public static Department mapToDepartment(DepartmentDto departmentDto){
        return new Department(
                departmentDto.getId(),
                departmentDto.getDepartmentName(),
                departmentDto.getDepartmentDescription()
        );
    }
}
