package dao;

import domain.User;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IUserDao {
    @Select(value = "select * from user")
    @Results(id = "userMap",value = {
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "userName",column = "username"),
            @Result(property = "address",column = "address"),
            @Result(property = "sex",column = "sex"),
            @Result(property = "birthday",column = "birthday"),
            @Result(property = "accounts",column = "id",many = @Many(select = "dao.IAccountDao.findByUId",fetchType=FetchType.LAZY))
    })
    List<User> findAll();

    @Select("select * from user where id=#{id}")
    User findById();
}
