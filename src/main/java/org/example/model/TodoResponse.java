package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private Boolean repeated;
    private String date;
    private String userid;
    private String url;

    public TodoResponse(TodoEntity todoEntity) {
        this.id = todoEntity.getId();
        this.title = todoEntity.getTitle();
        this.order = todoEntity.getOrder();
        this.completed = todoEntity.getCompleted();
//        this.repeated = todoEntity.getRepeated();
//        this.date = todoEntity.getDate();
//        this.userid = todoEntity.getUserid();
        this.url = "http://localhost:8080/" + this.id;
    }
}
