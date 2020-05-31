package dao;

import domain.Account;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

public interface IAccountDao {
    @Select("select * from account")
    @Results(id="findAllMap",value = {
            @Result(id=true,property = "id",column = "ID"),
            @Result(property = "uid",column = "UID"),
            @Result(property = "money",column = "MONEY"),
            @Result(property = "user",column = "UID",one = @One(select = "dao.IUserDao.findById",fetchType = FetchType.EAGER))
    })
    List<Account> findAll();

    @Select("select * from account where UID=#{uId}")
    List<Account> findByUId(Integer uId);
}
