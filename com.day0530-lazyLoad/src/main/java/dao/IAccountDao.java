package dao;

import domain.Account;

import java.util.List;

public interface IAccountDao {
    List<Account> findAll();
    List<Account> findByUId(Integer uId);
}
