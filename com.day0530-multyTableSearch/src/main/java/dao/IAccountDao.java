package dao;

import domain.Account;
import domain.AccountUser;
import domain.AccountWithUserProperty;

import java.util.List;

public interface IAccountDao {
    List<Account> findAllAccount();
    List<AccountUser> findAllAccountUser();
    List<AccountWithUserProperty> findAccountWithUserProperty();
}
