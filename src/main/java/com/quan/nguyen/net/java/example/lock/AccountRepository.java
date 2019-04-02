package com.quan.nguyen.net.java.example.lock;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("Select a From Account a where a.address = ?1")
    Account getAccountByAddressLock(String address);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("Select a From Account a where a.address = ?1")
    Account getAccountByAddressLockForWrite(String address);

}
