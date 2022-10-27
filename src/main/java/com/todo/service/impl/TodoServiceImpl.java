package com.todo.service.impl;

import com.todo.exceptions.EntityNotAvailableException;
import com.todo.repository.TodoRepository;
import com.todo.dto.TodoDTO;
import com.todo.entities.Todo;
import com.todo.enums.TodoStatus;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final ModelMapper modelMapper;
    private final TodoRepository todoRepository;

    @Override
    public TodoDTO create(TodoDTO todoDTO) {
        Todo savedTodo = todoRepository.save(modelMapper.map(todoDTO, Todo.class));
        return modelMapper.map(savedTodo,TodoDTO.class);
    }

    @Override
    public List<TodoDTO> createMany(List<TodoDTO> todoDTOS) {
        List<Todo> todos = todoDTOS.stream().map(todoDTO -> modelMapper.map(todoDTO, Todo.class)).toList();
        todos = todoRepository.saveAll(todos);
        return todos.stream().map(todo -> modelMapper.map(todo, TodoDTO.class)).toList();
    }


    @Override
    public List<TodoDTO> getAll(Integer page, Integer size, String sortBy, String sortDirection) {
        Sort sort = null;
        if(sortDirection.equalsIgnoreCase("ASC")) {
            sort = Sort.by(sortBy).ascending();
        }
        else {
            sort = Sort.by(sortBy).descending();
        }

        return todoRepository.findAllByIsArchiveFalse(PageRequest.of(page, size, sort)).stream()
                .map(todo -> modelMapper.map(todo,TodoDTO.class)).toList();
    }

    @Override
    public TodoDTO getById(String id) {
        Todo todo = todoRepository.findByIdAndIsArchiveFalse(id)
                .orElseThrow(() -> new EntityNotAvailableException("Todo", "id", id));
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public TodoDTO getByName(String name) {
        Todo todo = todoRepository.findByNameAndIsArchiveFalse(name)
                .orElseThrow(() -> new EntityNotAvailableException("Todo", "name", name));
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public TodoDTO delete(String id) {
        Todo todo = todoRepository.findByIdAndIsArchiveFalse(id)
                .orElseThrow(() -> new EntityNotAvailableException("Todo", "id", id));
        todo.setIsArchive(Boolean.TRUE);
        todo = todoRepository.save(todo);
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public TodoDTO update(TodoDTO todoDTO, String id) {
        Todo todo = todoRepository.findByIdAndIsArchiveFalse(id)
                .orElseThrow(() -> new EntityNotAvailableException("Todo", "id", id));
        if(todoDTO.getName() != null){
            todo.setName(todoDTO.getName());
        }
        if(todoDTO.getDate() != null){
            todo.setDate(todoDTO.getDate());
        }
        if(todoDTO.getStatus() != null){
            todo.setStatus(todoDTO.getStatus());
        }
        todo = todoRepository.save(todo);
        return modelMapper.map(todo, TodoDTO.class);
    }

    @Override
    public TodoDTO updateStatus(String id, TodoStatus status) {
        Todo todo = todoRepository.findByIdAndIsArchiveFalse(id)
                .orElseThrow(() -> new EntityNotAvailableException("Todo", "id", id));
        todo.setStatus(status);
        todo = todoRepository.save(todo);
        return modelMapper.map(todo, TodoDTO.class);
    }
}
