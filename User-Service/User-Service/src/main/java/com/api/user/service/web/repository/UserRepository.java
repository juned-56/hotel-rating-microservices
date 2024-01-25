package com.api.user.service.web.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.api.user.service.web.entity.User;

public interface UserRepository extends JpaRepository<User, String>{

}
