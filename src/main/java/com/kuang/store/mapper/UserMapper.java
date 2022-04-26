package com.kuang.store.mapper;

import com.kuang.store.entity.User;

//用户模块的持久层接口
public interface UserMapper {
    /**
     * 用户插入数据
     * @param user 用户的数据
     * @return 受影响的行数
     */
    Integer insert(User user);

    /**
     * 通过用户名查询数据
     * @param username 用户名
     * @return 如果找到这个用户就返回这个用户，否则返回null值
     */
    User findByUsername(String username);
}
