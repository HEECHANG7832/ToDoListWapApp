package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoUserResponse {
    private Long id;
    private String title;
    private Long order;
    private Boolean completed;
    private Boolean repeated;
    private String date;
    private String userid;
    private String url;

    public TodoUserResponse(TodoUserEntity todoUserEntity) {
        this.id = todoUserEntity.getId();
        this.title = todoUserEntity.getTitle();
        this.order = todoUserEntity.getOrder();
        this.completed = todoUserEntity.getCompleted();
        this.repeated = todoUserEntity.getRepeated();
        this.date = todoUserEntity.getDate();
        this.userid = todoUserEntity.getUserid();
        this.url = "http://localhost:8080/" + this.userid + "/" + this.id;
    }
}
