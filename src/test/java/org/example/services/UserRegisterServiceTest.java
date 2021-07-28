package org.example.services;

import org.example.model.AccountDto;
import org.example.model.AccountEntity;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRegisterServiceTest {

    @InjectMocks
    private UserRegisterService userRegisterService;

    @Mock
    private AccountRepository accountRepository;

    @Test
    public void registerUserTest(){
        AccountDto accountDto = new AccountDto();
        accountDto.setEmail("123");
        accountDto.setUserName("123");
        accountDto.setPassword("123");

        String actual = this.userRegisterService.registerUser(accountDto);

        assertEquals("success",  actual);

        accountDto.setEmail("");
        accountDto.setUserName("123");
        accountDto.setPassword("123");

        actual = this.userRegisterService.registerUser(accountDto);

        assertEquals("failed",  actual);

    }
}
