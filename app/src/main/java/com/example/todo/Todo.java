package com.example.todo;

public class Todo {
    String todoName;
    int flag=0;

    public Todo(String todoName, int flag) {
        this.todoName = todoName;
        this.flag = flag;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public Todo(String todoName) {
        this.todoName = todoName;
    }
}
