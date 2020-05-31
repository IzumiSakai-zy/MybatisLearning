

import dao.IAccountDao;
import dao.IUserDao;
import domain.Account;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class AccountDaoTest {
    private SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    private InputStream is=null;
    private SqlSessionFactory factory=null;
    private SqlSession sqlSession=null;
    private IAccountDao accountDao=null;

    @Before
    public void init() throws IOException {
        is=Resources.getResourceAsStream("SqlMapConfig.xml");
        factory=builder.build(is);
        sqlSession=factory.openSession();
        accountDao=sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws IOException {
        is.close();
        sqlSession.close();
    }

    @Test
    public void findAllTest(){
        List<Account> accounts = accountDao.findAll();
        for(Account account:accounts){
            System.out.print(account+"______________");
            System.out.println(account.getUser());
        }
    }
}
