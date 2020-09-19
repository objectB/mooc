package com.yudear.mooc;

import com.alibaba.fastjson.JSON;
import com.yudear.mooc.auth.shiro.JWTToken;
import com.yudear.mooc.auth.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@SpringBootTest
@RunWith(SpringRunner.class)
public class MoocApplicationTests {




    @Test
    public void contextLoads() throws ClassNotFoundException, SQLException {
        //获取连接

        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306?useUnicode=true&characterEncoding=utf8";
        String username="root";
        String password="123456";
        Properties properties = new Properties();
        properties.put("user",username);
        properties.put("password",password);
        properties.put("remarksReport","true");//获取数据或的备注
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(url,properties);
        DatabaseMetaData metaData = connection.getMetaData();
       System.out.print(metaData.getUserName());

    }


    @Test
    public void  yy(){

    }

}
