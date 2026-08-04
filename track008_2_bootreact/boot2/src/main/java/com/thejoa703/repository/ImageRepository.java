package com.thejoa703.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thejoa703.entity.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long>{
	
}
/*
(1) 사용할 수 있는 기본 SQL
  create - save: insert into images (컬럼,,,) values (?,,,)
  read   - findAll  : select * from images
           findById : select * from images where id=?
  update - save : update images set 컬럼=?,,, where id=?
  delete - deleteById : delete images where id=?
*/
