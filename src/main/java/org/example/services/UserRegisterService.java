package org.example.services;

import lombok.AllArgsConstructor;
import org.example.model.AccountDto;
import org.example.model.AccountEntity;
import org.example.repository.AccountRepository;
import org.example.tools.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

@Service
@AllArgsConstructor
public class UserRegisterService {

    @Autowired
    AccountRepository accountRepository;

    public String registerUser(AccountDto accountDto){
        String userName = accountDto.getUserName();
        String passward = Hashing.hashingPassword(accountDto.getPassword());
        String email = accountDto.getEmail();

        if(userName.equals("") || passward.equals("") || email.equals("")){
            return "failed";
        }

        AccountEntity accountEntity = new AccountEntity();
        accountEntity.setUserName(userName);
        accountEntity.setEmail(email);
        accountEntity.setPassword(passward);

        if(accountRepository.findByUserName(userName) != null){
            return "failed";
        }

        accountRepository.save(accountEntity);

        return "success";
    }

    public String loginUser(AccountDto accountDto){

        String userName = accountDto.getUserName();
        String passward = Hashing.hashingPassword(accountDto.getPassword());
        String email = accountDto.getEmail();

        if(userName.equals("") || passward.equals("") || email.equals("")){
            return "failed";
        }

        if(accountRepository.findByUserNameAndPassword(userName, passward) != null){
            return "failed";
        }

        return "success";
    }
}
