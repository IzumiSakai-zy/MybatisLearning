

import dao.IAccountDao;
import dao.IUserDao;
import domain.Account;
import domain.AccountUser;
import domain.AccountWithUserProperty;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private InputStream is=null;
    private SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    private SqlSession sqlSession=null;
    private IUserDao userDao=null;
    private IAccountDao accountDao=null;

    @Before
    public void init() throws IOException{
        is=Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSession=builder.build(is).openSession(true);//自动提交的事务
        userDao=sqlSession.getMapper(IUserDao.class);
        accountDao=sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        is.close();
        sqlSession.close();
    }

    @Test
    public void findAllTest(){
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.println(user);
            System.out.println(user.getAccounts());
        }
    }

    @Test
    public void findAllAccountTest(){
        List<Account> accounts = accountDao.findAllAccount();
        for (Account account:accounts) {
            System.out.println(account);
        }
    }

    @Test
    public void findAllAccountUserTest(){
        List<AccountUser> accountUsers = accountDao.findAllAccountUser();
        for (AccountUser accountUser:accountUsers) {
            System.out.println(accountUser);
        }
    }

    @Test
    public void findAccountWithUserPropertyTest(){
        List<AccountWithUserProperty> accountWithUserProperties = accountDao.findAccountWithUserProperty();
        for (AccountWithUserProperty accountWithUserProperty:accountWithUserProperties){
            System.out.print(accountWithUserProperty);
            System.out.println(accountWithUserProperty.getUser());
        }
    }
}
