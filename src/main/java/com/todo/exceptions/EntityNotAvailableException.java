package com.todo.exceptions;

public class EntityNotAvailableException extends RuntimeException{
    private String entity;
    private String field;
    private Object value;
    public EntityNotAvailableException(String entity, String field, Object value){
        super(entity + " with " + field + " " + value + " not found");
    }
}
