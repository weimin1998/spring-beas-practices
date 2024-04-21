package com.example.springbootjdbcTemplatedemo.service;

import com.example.springbootjdbcTemplatedemo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public int addUser(User user) {
        return jdbcTemplate.update("insert into tb_user (id, name,address) values (?,?,?);", user.getId(), user.getName(), user.getAddress());
    }

    public int deleteUserById(String id) {
        return jdbcTemplate.update("delete from tb_user where id=?", id);
    }

    public int updateUserById(User user) {
        return jdbcTemplate.update("update tb_user set name=?,address=? where id=?", user.getName(), user.getAddress(),user.getId());
    }

    // 如果数据库列和javabean的属性一样，可以使用下面的方式
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from tb_user", new BeanPropertyRowMapper<>(User.class));
    }
}