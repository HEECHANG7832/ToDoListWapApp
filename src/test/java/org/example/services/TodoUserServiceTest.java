package org.example.services;

import org.example.model.TodoEntity;
import org.example.model.TodoUserEntity;
import org.example.model.TodoUserRequest;
import org.example.repository.TodoRepository;
import org.example.repository.TodoUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //Mockito에서 제공하는 목객체를 사용하기 위함
public class TodoUserServiceTest {

    //Mock 객체를 InjectMock 객체에 주입시킬 수 있다
    //InjectMocks - Service
    //Mock - DAO
    @InjectMocks
    private TodoUserService todoUserService;

    @Mock
    private TodoUserRepository todoUserRepository;

    @Test
    void userAdd() {
        //repository.save를 Mock할때
        //DB에 저장되지 않아도 save 한 것처럼
        when(this.todoUserRepository.save(any(TodoUserEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoUserRequest request = new TodoUserRequest();
        request.setTitle("test title");
        request.setUserid("test user");

        TodoUserEntity actual = this.todoUserService.userAdd(request);
        //System.out.println(actual.getId());
        assertEquals("test user",  actual.getUserid());
        assertEquals("test title",  actual.getTitle());
    }

    @Test
    void searchByUseridAndId() {
        TodoUserEntity todo = new TodoUserEntity();
        todo.setTitle("test");
        todo.setId(123L);
        todo.setUserid("test");
        todo.setOrder(0L);
        todo.setCompleted(false);
        Optional<TodoUserEntity> expected = Optional.of(todo);

        given(this.todoUserRepository.findByUseridAndId(anyString(), anyLong()))
                .willReturn(expected);

        TodoUserEntity actual = this.todoUserService.searchByUseridAndId("test",123L);

        assertEquals(actual.getId(), 123L);
        assertEquals(actual.getUserid(), "test");
        assertEquals(actual.getOrder(), 0L);
        assertFalse(actual.getCompleted());
        assertEquals(actual.getTitle(), "test");
    }

    @Test
    void searchUseridAll() {
    }

    @Test
    void updateByUseridAndId() {
    }

    @Test
    void deleteByUseridAndId() {
    }

    @Test
    void deleteUseridAll() {
    }
}
