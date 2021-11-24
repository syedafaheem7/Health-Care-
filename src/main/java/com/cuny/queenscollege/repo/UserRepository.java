
  package com.cuny.queenscollege.repo;
  
  import java.util.Optional;
  
  import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cuny.queenscollege.entity.User;
  
  public interface UserRepository extends JpaRepository<User, Long> {
  
  Optional<User> findByUserName(String userName);
  
  @Modifying
  @Query("UPDATE User Set password=:encPwd WHERE id=:userId")
  void updateUserPwd(String encPwd, Long userId);
  }
 