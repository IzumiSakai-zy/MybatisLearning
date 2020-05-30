

import dao.IAccountDao;
import dao.IUserDao;
import domain.Account;
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
    public void findAll_AccountTest(){
        List<Account> accounts = accountDao.findAll();
        for (Account account:accounts){
            System.out.print(account+"         ");
            System.out.println(account.getUser());
        }
    }

    @Test
    public void findAll_UserTest(){
        List<User> users = userDao.findAll();
//        for (User user:users){
//            System.out.print(user+"         ");
//            System.out.println(user.getAccounts());
//        }
    }

    @Test
    public void findByUId_AccountTest(){
        List<Account> accounts = accountDao.findByUId(46);
        for (Account account:accounts)
            System.out.println(account);
    }
}
