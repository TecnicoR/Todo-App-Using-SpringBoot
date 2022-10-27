package com.todo.entities;

import com.todo.enums.TodoStatus;
import com.todo.utils.TodoIdGenerator;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Parameter;
import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Todo {
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "todo_sequence")
    @GenericGenerator(
            name = "todo_sequence",
            strategy = "com.todo.utils.TodoIdGenerator",
            parameters = {
                    @Parameter(name = TodoIdGenerator.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = TodoIdGenerator.VALUE_PREFIX_PARAMETER, value = "TODO_"),
                    @Parameter(name = TodoIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d") })
    @Id
    private String id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TodoStatus status;

    private LocalDate date;

    @CreationTimestamp
    private LocalDate createdDate;

    @UpdateTimestamp
    private LocalDate modifiedDate;

    private Boolean isArchive = Boolean.FALSE;
}
