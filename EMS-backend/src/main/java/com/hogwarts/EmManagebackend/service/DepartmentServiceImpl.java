package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.entity.Department;
import com.hogwarts.EmManagebackend.exception.ResourceNotFoundException;
import com.hogwarts.EmManagebackend.mapper.DepartmentMapper;
import com.hogwarts.EmManagebackend.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private DepartmentRepository departmentRepository;

    @Override
    public DepartmentDto createDepartment(DepartmentDto departmentDto) {

        Department department = DepartmentMapper.mapToDepartment(departmentDto);
        Department savedDepartment = departmentRepository.save(department);

        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public DepartmentDto getDepartmentById(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).orElseThrow(
                () -> new ResourceNotFoundException("Department does not exist with given id: " + departmentId)
        );

        return DepartmentMapper.mapToDepartmentDto(department);
    }
}
