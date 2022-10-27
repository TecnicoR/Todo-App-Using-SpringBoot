package com.todo.service;

import com.todo.dto.TodoDTO;
import com.todo.entities.Todo;
import com.todo.enums.TodoStatus;

import java.util.List;

public interface TodoService {
    TodoDTO create(TodoDTO todoDTO);
    List<TodoDTO> createMany(List<TodoDTO> todoDTOS);
    List<TodoDTO> getAll(Integer page, Integer size, String sortBy, String sortDirection);
    TodoDTO getById(String id);
    TodoDTO getByName(String name);
    TodoDTO delete(String id);
    TodoDTO update(TodoDTO todoDTO, String  id);
    TodoDTO updateStatus(String id, TodoStatus status);
}
