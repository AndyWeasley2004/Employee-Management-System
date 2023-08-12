package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.dto.EmployeeDto;

import java.util.List;
import java.util.Set;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);

    List<DepartmentDto> getAllDepartments();

    DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto);

    void deleteDepartment(Long departmentId);

    Set<EmployeeDto> listEmployeesByDeptId(Long departmentId);
}
