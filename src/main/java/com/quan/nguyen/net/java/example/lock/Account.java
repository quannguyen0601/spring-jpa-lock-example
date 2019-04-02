package com.quan.nguyen.net.java.example.lock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;
import java.math.BigDecimal;

@Entity
public class Account {

    public Account(){

    }

    @Override
    public String toString() {
        return "id="+id+" address="+address+" balance="+balance.toString();
    }

    public Account(Long id, String address, BigDecimal balance, long version) {
        this.id = id;
        this.address = address;
        this.balance = balance;
//        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

//    public long getVersion() {
//        return version;
//    }
//
//    public void setVersion(long version) {
//        this.version = version;
//    }

    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private BigDecimal balance;

//    @Version
//    private long version;
}
