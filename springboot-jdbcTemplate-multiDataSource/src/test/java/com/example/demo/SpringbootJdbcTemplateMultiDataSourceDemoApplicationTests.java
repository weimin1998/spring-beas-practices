package com.example.demo;

import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootJdbcTemplateMultiDataSourceDemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    @Qualifier("jdbcTemplateOne")
    JdbcTemplate jdbcTemplateOne;
    @Resource(name = "jdbcTemplateTwo")
    JdbcTemplate jdbcTemplateTwo;

    @Test
    public void add(){
        User user = new User("1","weimin","shanghai");
        jdbcTemplateOne.update("insert into tb_user (id, name,address) values (?,?,?);", user.getId(), user.getName(), user.getAddress());
        jdbcTemplateTwo.update("insert into tb_user (id, name,address) values (?,?,?);", user.getId(), user.getName(), user.getAddress());

    }

    @Test
    public void getAllUser() {
        List<User> list = jdbcTemplateOne.query("select * from tb_user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list);
    }

    @Test
    public void getAllUser2() {
        List<User> list = jdbcTemplateTwo.query("select * from tb_user", new BeanPropertyRowMapper<>(User.class));
        System.out.println(list);
    }
}
