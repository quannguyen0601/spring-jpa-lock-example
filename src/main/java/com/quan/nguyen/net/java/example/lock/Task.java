package com.quan.nguyen.net.java.example.lock;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service
public class Task {
    private static final Logger logger = LoggerFactory.getLogger(Task.class);

    @Autowired
    AccountRepository repository;

    @Autowired
    PlatformTransactionManager transactionManager;

    @Transactional
    public void updateAccount(){
        logger.info("--------- USER 1 access account ----------");

        Account account = null;
        TransactionTemplate transactionTemplate = new TransactionTemplate(transactionManager);

        try {

            account = repository.getAccountByAddressLockForWrite("XAX1010123123");

            try {
                Thread.sleep(500);
                logger.info("User 1 - hardWorking in 2 seconds ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("User 1 - get current balance: " + account.toString());

            account.setBalance(new BigDecimal("2500.43"));
//
            repository.save(account);

            logger.info("User 1 updated balance: " + account.toString());

        } catch (Exception e) {
            logger.info("User 1 got exception while acquiring the database lock:\n " + e);
            e.printStackTrace();
            return;
        }
    }


    @Transactional
    public void readAccount(){
        logger.info("--------- USER 2 access account ----------");

        try {
            Thread.sleep(200);
            logger.info("User 2 - hardWorking in 3seconds ");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Account account = null;
        try {

            account = repository.getAccountByAddressLock("XAX1010123123");

            try {
                Thread.sleep(500);
                logger.info("User 2 - hardWorking in 3seconds ");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("User 2 - get current balance: " + account.toString());



//                account.setBalance(account.getBalance().subtract(new BigDecimal(550)));
//
//                repository.save(account);
//
//                logger.info("User 2 update balance: " + account.toString());

        } catch (Exception e) {
            logger.info("User 2 got exception while acquiring the database lock:\n " + e);
            e.printStackTrace();
            return;
        }
    }
}
