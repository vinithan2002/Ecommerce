package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {

}
