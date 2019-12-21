package com.yudear.mooc.service;


import com.baomidou.mybatisplus.service.IService;
import com.yudear.mooc.mapper.UserMapper;
import com.yudear.mooc.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements IUserService {

   @Autowired
   UserMapper uerMapper ;


    @Cacheable(value = "user", key = "#username")
    @Override
    public User login(String username,String password){
       User user =new User();
       user.setName(username);
       user.setPassword(password);
      return uerMapper.selectOne(user);
   }


}
