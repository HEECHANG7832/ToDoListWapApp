package org.example.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.AccountDto;
import org.example.model.AccountEntity;
import org.example.repository.AccountRepository;
import org.example.tools.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserRegisterService {

    @Autowired
    AccountRepository accountRepository;

    public String registerUser(AccountDto accountDto){
        String userName = accountDto.getUserName();
        String password = Hashing.hashingPassword(accountDto.getPassword());
        String email = accountDto.getEmail();

        log.info(password);

        if(userName.equals("") || password.equals("") || email.equals("")){
            return "failed";
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserName(userName);
        accountEntity.setEmail(email);
        accountEntity.setPassword(password);

        if(accountRepository.findByUserName(userName) != null){
            return "failed";
        }

        accountRepository.save(accountEntity);

        return "success";
    }

    public Boolean loginUser(AccountDto accountDto){

        String userName = accountDto.getUserName();
        String password = Hashing.hashingPassword(accountDto.getPassword());
        String email = accountDto.getEmail();

        log.info(password);

        if(userName.equals("") || password.equals("") || email.equals("")){
            return false;
        }

        if(accountRepository.findByUserNameAndPassword(userName, password) == null){
            return false;
        }

        return true;
    }

    public void getUserList(){

        accountRepository.findAll().stream()
                .forEach(a -> System.out.println(a.getUserName() + a.getPassword()));
    }
}
