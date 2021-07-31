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
        log.info("CREATE");

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


        TodoUserEntity result = this.todoUserService.add(request);
        return ResponseEntity.ok(new TodoUserResponse(result));
    }

//    @GetMapping("/{userid}")
//    public ResponseEntity<List<TodoResponse>> readAll() {
//        log.info("READ ALL");
//        List<TodoEntity> result = this.todoService.searchAll();
//        List<TodoResponse> response = result.stream().map(TodoResponse::new).collect(Collectors.toList());
//        return ResponseEntity.ok(response);
//    }
//
//    @GetMapping("/{userid}/{id}")
//    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
//        log.info("READ");
//        TodoEntity result = this.todoService.searchById(id);
//        return ResponseEntity.ok(new TodoResponse(result));
//    }
//
//    @PatchMapping("/{userid}/{id}")
//    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request) {
//        log.info("UPDATE");
//        TodoEntity result = this.todoService.updateById(id, request);
//        return ResponseEntity.ok(new TodoResponse(result));
//    }
//
//    @DeleteMapping("/{userid}")
//    public ResponseEntity<?> deleteAll() {
//        log.info("DELETE ALL");
//        this.todoService.deleteAll();
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/{userid}/{id}")
//    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
//        log.info("DELETE");
//        this.todoService.deleteById(id);
//        return ResponseEntity.ok().build();
//    }
}
