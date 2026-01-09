package com.ecommerce.online.Repository;

import com.ecommerce.online.Entity.UserAddress;
import com.ecommerce.online.dto.UserAddressDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress,Long> {


    List<UserAddress> findAllByUserId(Long userId);

    @Transactional
    @Modifying
    @Query(
    """
    UPDATE UserAddress u SET u.isDefault = false WHERE u.userId = :tempId
    """
    )
    void setFalseToDefault(Long tempId);
}
