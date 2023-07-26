package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;

public interface DepartmentService {
    DepartmentDto createDepartment(DepartmentDto departmentDto);

    DepartmentDto getDepartmentById(Long departmentId);
}
