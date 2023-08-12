package com.hogwarts.EmManagebackend.service;

import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.dto.EmployeeDto;
import com.hogwarts.EmManagebackend.entity.Department;
import com.hogwarts.EmManagebackend.exception.ResourceNotFoundException;
import com.hogwarts.EmManagebackend.mapper.DepartmentMapper;
import com.hogwarts.EmManagebackend.mapper.EmployeeMapper;
import com.hogwarts.EmManagebackend.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    @Override
    public List<DepartmentDto> getAllDepartments() {

        List<Department> departments = departmentRepository.findAll();

        return departments.stream().map(DepartmentMapper::mapToDepartmentDto)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentDto updateDepartment(Long departmentId, DepartmentDto departmentDto) {
        Department department = departmentRepository.findById(departmentId).
                orElseThrow(() ->
                        new ResourceNotFoundException("Department does not exist with given id: " + departmentId));

        department.setDepartmentName(departmentDto.getDepartmentName());
        department.setDepartmentDescription(departmentDto.getDepartmentDescription());

        Department savedDepartment = departmentRepository.save(department);
        return DepartmentMapper.mapToDepartmentDto(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long departmentId) {
        Department department = departmentRepository.findById(departmentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Department does not exist given Id: " + departmentId));

        departmentRepository.delete(department);
    }

    @Override
    public Set<EmployeeDto> listEmployeesByDeptId(Long departmentId) {
        Department department = departmentRepository.findById(departmentId).
                orElseThrow(() ->
                    new ResourceNotFoundException("Department does not exist given Id: " + departmentId));

        Set<EmployeeDto> employees = department.getEmployees().stream()
                .map(employee -> EmployeeMapper.mapToEmployeeDto(employee))
                .collect(Collectors.toSet());

        return employees;
    }
}
