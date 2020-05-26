

import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    public static void main(String[] args) throws IOException {
        //读取配置文件
        InputStream is= Resources.getResourceAsStream("SqlMapConfig.xml");
        //创建工厂
        SqlSessionFactoryBuilder builder=new SqlSessionFactoryBuilder();
        SqlSessionFactory factory=builder.build(is);
        //使用工厂生产对象
        SqlSession sqlSession = factory.openSession();
        //创建dao接口的代理对象
        IUserDao userDao= sqlSession.getMapper(IUserDao.class);
        //用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user:users) {
            System.out.println(user);
        }
        //释放资源
        is.close();
        sqlSession.close();
    }
}
