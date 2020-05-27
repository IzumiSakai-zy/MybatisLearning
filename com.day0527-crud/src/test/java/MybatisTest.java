

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
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream is=null;
    private SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
    private SqlSession sqlSession=null;
    private IUserDao userDao=null;

    @Before
    public void init() throws IOException{
        is=Resources.getResourceAsStream("SqlMapConfig.xml");
        sqlSession=builder.build(is).openSession();
        userDao=sqlSession.getMapper(IUserDao.class);
    }

    @After
    public void destroy() throws IOException {
        is.close();
        sqlSession.close();
    }

    @Test
    public void basicTest() throws IOException {
        //用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user:users) {
            System.out.println(user);
        }
    }

    @Test
    public void saveUserTest() throws IOException {
        User user=new User();
        user.setUsername("最后一个");
        user.setBirthday(new Date());
        user.setAddress("武当");
        user.setSex("男");
        System.out.println(user.getId());
        userDao.saveUser(user);
        System.out.println(user.getId());
        sqlSession.commit();

    }

    @Test
    public void updateUserTest() throws IOException {
        User user=new User();
        user.setUsername("王也");
        user.setBirthday(new Date());
        user.setAddress("武当");
        user.setSex("女");
        user.setId(52);
        userDao.updateUser(user);
        sqlSession.commit();
    }

    @Test
    public void deleteUserTest() throws IOException {
        userDao.deleteUser(53);
        sqlSession.commit();
    }

    @Test
    public void findByIdTest(){
        System.out.println(userDao.findById(52));
    }

    @Test
    public void findByNameTest(){
        List<User> users = userDao.findByName("%王%");
        for (User user: users) {
            System.out.println(user);
        }
    }
}
