package org.example.controller;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.*;
import org.example.services.TodoService;
import org.example.services.TodoUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/todo")
public class TodoUserController {

    private final TodoUserService todoUserService;

    @PostMapping("/{userid}")
    public ResponseEntity<TodoUserResponse> create(@PathVariable String userid, @RequestBody TodoUserRequest request) {
        log.info("CREATE TODO userid {}", userid);

        if (ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();

        if (ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);

        if (ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        if (ObjectUtils.isEmpty(request.getDate())){
            //default 현재 날짜
            SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            request.setDate(format.format(time));
        }

        if (ObjectUtils.isEmpty(request.getRepeated()))
            request.setRepeated(false);

        request.setUserid(userid);


        TodoUserEntity result = this.todoUserService.userAdd(request);
        return ResponseEntity.ok(new TodoUserResponse(result));
    }

    @GetMapping("/{userid}/{id}")
    public ResponseEntity<TodoUserResponse> readOne(@PathVariable String userid, @PathVariable Long id) {
        log.info("userid {} id {} READ", userid, id);
        TodoUserEntity result = this.todoUserService.searchByUseridAndId(userid, id);
        return ResponseEntity.ok(new TodoUserResponse(result));
    }

    @GetMapping("/{userid}")
    public ResponseEntity<List<TodoUserResponse>> readAll(@PathVariable String userid) {
        log.info("READ ALL userid {}", userid);
        List<TodoUserEntity> result = this.todoUserService.searchUseridAll(userid);
        List<TodoUserResponse> response = result.stream().map(TodoUserResponse::new).collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/{userid}/{id}")
    public ResponseEntity<TodoUserResponse> update(@PathVariable String userid, @PathVariable Long id, @RequestBody TodoUserRequest request) {
        log.info("UPDATE userid {} id {}", userid, id);
        TodoUserEntity result = this.todoUserService.updateByUseridAndId(userid, id, request);
        return ResponseEntity.ok(new TodoUserResponse(result));
    }

    @DeleteMapping("/{userid}")
    public ResponseEntity<?> deleteAll(@PathVariable String userid) {
        log.info("DELETE ALL userid {}", userid);
        this.todoUserService.deleteUseridAll(userid);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{userid}/{id}")
    public ResponseEntity<?> deleteOne(@PathVariable String userid, @PathVariable Long id) {
        log.info("DELETE userid {} id {}", userid, id);
        this.todoUserService.deleteByUseridAndId(userid, id);
        return ResponseEntity.ok().build();
    }
}
