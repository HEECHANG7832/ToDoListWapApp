package org.example.services;

import lombok.AllArgsConstructor;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.model.TodoUserEntity;
import org.example.model.TodoUserRequest;
import org.example.repository.TodoRepository;
import org.example.repository.TodoUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TodoUserService {

    private final TodoUserRepository todoUserRepository;

    /**
     * Userid에 해당하는 Todo 아이템 추가
     */
    public TodoUserEntity userAdd(TodoUserRequest request) {
        TodoUserEntity todoUserEntity = new TodoUserEntity();
        todoUserEntity.setTitle(request.getTitle());
        todoUserEntity.setOrder(request.getOrder());
        todoUserEntity.setCompleted(request.getCompleted());
        todoUserEntity.setRepeated(request.getRepeated());
        todoUserEntity.setDate(request.getDate());
        todoUserEntity.setUserid(request.getUserid());
        return this.todoUserRepository.save(todoUserEntity);
    }

    /**
     * Userid, Id에 해당하는 Todo 아이템 조회
     */
    public TodoUserEntity searchByUseridAndId(String userid, Long id) {
        return this.todoUserRepository.findByUseridAndId(userid, id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    /**
     * Userid 전체 Todo 아이템 목록 조회
     */
    public List<TodoUserEntity> searchUseridAll(String userid) {
        return this.todoUserRepository.findByUserid(userid);
    }

    /**
     * Userid, id Todo 아이템 수정
     */
    public TodoUserEntity updateByUseridAndId(String userid, Long id, TodoUserRequest request) {
        Optional<TodoUserEntity> opt = this.todoUserRepository.findByUseridAndId(userid, id);

        //Userid, Id로 검색되는 내용이 없을경우 throw Exception
        TodoUserEntity todoUserEntity = opt.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (request.getTitle() != null) {
            todoUserEntity.setTitle(request.getTitle());
        }

        if (request.getOrder() != null) {
            todoUserEntity.setOrder(request.getOrder());
        }

        if (request.getCompleted() != null) {
            todoUserEntity.setCompleted(request.getCompleted());
        }

        if (request.getRepeated() != null) {
            todoUserEntity.setRepeated(request.getRepeated());
        }

        if (request.getDate() != null) {
            todoUserEntity.setDate(request.getDate());
        }

        return this.todoUserRepository.save(todoUserEntity);
    }

    /**
     * Userid Id Todo 아이템 삭제
     */
    @Transactional
    public void deleteByUseridAndId(String userid, Long id) {
        this.todoUserRepository.deleteByUseridAndId(userid, id);
    }

    /**
     * Userid 전체 Todo 아이템 목록 삭제
     */
    @Transactional
    public void deleteUseridAll(String userid) {
        this.todoUserRepository.deleteByUserid(userid);
    }
}
