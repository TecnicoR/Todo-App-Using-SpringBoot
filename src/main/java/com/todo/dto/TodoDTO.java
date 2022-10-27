package com.todo.dto;

import com.todo.enums.TodoStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoDTO {
    private String id;
    private String name;
    private TodoStatus status;
    private LocalDate date;
}
