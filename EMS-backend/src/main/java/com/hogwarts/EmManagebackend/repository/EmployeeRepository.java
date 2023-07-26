package com.hogwarts.EmManagebackend.repository;

import com.hogwarts.EmManagebackend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
