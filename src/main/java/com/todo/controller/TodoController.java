package com.todo.controller;


import com.todo.dto.TodoDTO;
import com.todo.enums.TodoStatus;
import com.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.todo.utils.AppConstants.*;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public TodoDTO create(@RequestBody TodoDTO todoDTO){
        return todoService.create(todoDTO);
    }

    @PostMapping("/many")
    public List<TodoDTO> createMany(@RequestBody List<TodoDTO> todoDTOS){
        return todoService.createMany(todoDTOS);
    }

    @GetMapping
    public List<TodoDTO> getAll(@RequestParam(required = false, defaultValue = PAGE_NUMBER) Integer page,
                                @RequestParam(required = false, defaultValue = PAGE_SIZE) Integer size,
                                @RequestParam(required = false, defaultValue = SORT_BY) String sortBy,
                                @RequestParam(required = false, defaultValue = SORT_DIRECTION) String sortDirection){
        return todoService.getAll(page, size, sortBy, sortDirection);
    }

    @GetMapping("{id}")
    public TodoDTO getById(@PathVariable String id){
        return todoService.getById(id);
    }

    @PutMapping("{id}")
    public TodoDTO update(@RequestBody TodoDTO todoDTO, @PathVariable String id){
        return todoService.update(todoDTO,id);
    }

    @PatchMapping("{id}")
    public TodoDTO updateStatus(@PathVariable String  id, @RequestParam TodoStatus status){
        return todoService.updateStatus(id, status);
    }

    @DeleteMapping("{id}")
    public TodoDTO delete(@PathVariable String id){
        return todoService.delete(id);
    }
}
