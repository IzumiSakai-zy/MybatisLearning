

import dao.IUserDao;
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

public class UserDaoTest {
    private SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    private InputStream is=null;
    private SqlSessionFactory factory=null;
    private SqlSession sqlSession=null;
    private IUserDao userDao=null;

    @Before
    public void init() throws IOException {
        is=Resources.getResourceAsStream("SqlMapConfig.xml");
        factory=builder.build(is);
        sqlSession=factory.openSession();
        userDao=sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        is.close();
        sqlSession.close();
    }

    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user:users){
            System.out.print(user);
            System.out.println(user.getAccounts());
        }
    }
}
