package com.youngfinance.dataservice.mapper;

import com.youngfinance.api.model.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    // 统计注册人数
    int selectUserCounts();

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByPhone(@Param("phone") String phone);

    int insertReturnPrimaryKey(User user);

    User selectLogin(@Param("phone") String phone, @Param("loginPassword") String newPassword);

    int updateRealName(@Param("phone") String phone, @Param("name") String name, @Param("idCard") String idCard);
}