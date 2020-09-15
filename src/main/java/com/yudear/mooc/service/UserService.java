package com.yudear.mooc.service;


import com.yudear.mooc.mapper.UserMapper;
import com.yudear.mooc.entiy.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IuserService {

   @Autowired
   UserMapper userMapper ;

    @Cacheable(value = "user", key = "#username")
    @Override
    public User login(String username,String password){
       User user =new User();
       user.setName(username);
       user.setPassword(password);
      return userMapper.selectOne(user);
   }


}
