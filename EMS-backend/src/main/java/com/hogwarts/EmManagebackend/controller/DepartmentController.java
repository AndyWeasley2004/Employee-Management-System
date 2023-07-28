package com.hogwarts.EmManagebackend.controller;


import com.hogwarts.EmManagebackend.dto.DepartmentDto;
import com.hogwarts.EmManagebackend.service.DepartmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
@AllArgsConstructor
@CrossOrigin("*")
public class DepartmentController {

    private DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<DepartmentDto> createDepartment(@RequestBody DepartmentDto departmentDto){

        DepartmentDto department = departmentService.createDepartment(departmentDto);

        return new ResponseEntity<>(department, HttpStatus.CREATED);
    }

    @GetMapping({"/{id}"})
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long departmentId){

        DepartmentDto departmentDto = departmentService.getDepartmentById(departmentId);

        return ResponseEntity.ok(departmentDto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> getAllDepartments(){

        List<DepartmentDto> departments = departmentService.getAllDepartments();

        return ResponseEntity.ok(departments);
    }

    @PutMapping({"{id}"})
    public ResponseEntity<DepartmentDto> updateDepartment(@PathVariable("id") Long departmentId,
                                                          @RequestBody DepartmentDto updatedDepartment){

        DepartmentDto departmentDto = departmentService.updateDepartment(departmentId, updatedDepartment);

        return ResponseEntity.ok(departmentDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") Long departmentId){
        departmentService.deleteDepartment(departmentId);

        return ResponseEntity.ok("Department Delete Successfully");
    }
}
