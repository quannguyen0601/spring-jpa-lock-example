package com.quan.nguyen.net.java.example.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class PessimisticLockService {
    private static Logger logger = LoggerFactory.getLogger(PessimisticLockService.class);

    @Autowired
    Task task;

    void runDemo(){

        ExecutorService es = Executors.newFixedThreadPool(2);

        //user 1, writer
        es.execute(task::updateAccount);

        //user 2, reader
        es.execute(task::readAccount);

    }


}
