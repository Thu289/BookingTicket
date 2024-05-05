package com.example.bookingticket.controller;

import com.example.bookingticket.model.dto.response.AccountDTO;
import com.example.bookingticket.model.dto.response.ResponseDTO;
import com.example.bookingticket.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    public ResponseDTO getAccountDetail(@PathVariable Integer id){
        return accountService.getAccountDetailById(id);
    }

    @PostMapping()
    public ResponseDTO createAndUpdateAccount(AccountDTO accountDTO){
        return accountService.createAndUpdateStaff(accountDTO);
    }

    @PutMapping("/{id}")
    public ResponseDTO changePassword(@PathVariable int id,String oldPassword,String newPassword){
        return accountService.changePassword(id,oldPassword,newPassword);
    }

}
