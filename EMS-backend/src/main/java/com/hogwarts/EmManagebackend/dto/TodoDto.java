package com.hogwarts.EmManagebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TodoDto {
    private Long id;
    private String name;
    private String description;
    private Character status;
    private Long employeeId;
}
