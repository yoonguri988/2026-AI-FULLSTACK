package com.thejoa703.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thejoa703.entity.AppUser;

@Repository                                             //Entity, PK의 자료형
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

	Optional<AppUser> findByEmail(String email);
}
// create - save: insert into app_user (컬럼,,,) values (?,,,)
// read   - findAll  : select * from app_user
//          findById : select * from app_user where id=?
// update - save : update app_user set 컬럼=?,,, where id=?
// delete - deleteById : delete app_user where id=?

/*
1. 검색: findBy필드명
 */