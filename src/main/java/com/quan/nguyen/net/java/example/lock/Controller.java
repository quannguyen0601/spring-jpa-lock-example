package com.quan.nguyen.net.java.example.lock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    private AccountRepository repository;

    @Autowired
    private PessimisticLockService service;

    @RequestMapping(value = "/api/account/{id}",method = RequestMethod.GET)
    ResponseEntity<Account> getAccount(@PathVariable("id") Long id){
      return ResponseEntity.ok(repository.findById(id).orElse( new Account()));
    }


    @RequestMapping(value = "/api/account",method = RequestMethod.PUT, consumes = "application/json")
    ResponseEntity<Account> updateAccount(@RequestBody Account account){
        return ResponseEntity.ok(repository.save(account));
    }

    @RequestMapping(value = "/api/demo2",method = RequestMethod.GET)
    ResponseEntity<String> runDemo(){
        service.runDemo();
        return ResponseEntity.ok("OK");
    }

}
