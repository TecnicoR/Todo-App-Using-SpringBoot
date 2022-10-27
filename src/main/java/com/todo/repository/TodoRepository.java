package com.todo.repository;

import com.todo.entities.Todo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo,Integer> {
    Optional<Todo> findByIdAndIsArchiveFalse(String id);
    List<Todo> findAllByIsArchiveFalse(Pageable pageable);
    Optional<Todo> findByNameAndIsArchiveFalse(String name);
}
