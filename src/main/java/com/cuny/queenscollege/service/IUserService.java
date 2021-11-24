
  package com.cuny.queenscollege.service;
  
  import java.util.Optional;
  
  import com.cuny.queenscollege.entity.User;
  
  public interface IUserService {
  
  Long saveUser(User user); 
  Optional<User> findByUsername(String username);
  void updateUserPwd(String pwd, Long userId);
  
  }
 