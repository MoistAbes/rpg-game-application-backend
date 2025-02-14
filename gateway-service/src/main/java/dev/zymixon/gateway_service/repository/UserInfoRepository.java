package dev.zymixon.gateway_service.repository;

import dev.zymixon.gateway_service.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    @Query("select u from UserInfo u where u.username = :username")
    UserInfo findByUsername(@Param("username") String username);

}
