package com.crud.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.crud.app.models.UserModel;

public interface UserRepo extends JpaRepository<UserModel, Long>{
	
	UserModel findById(long id);
	
	@Query("select a from UserModel a where a.username = ?1")
	List<UserModel> findByUsernameList(String username);
	
//	@Query(value = "select a.name, a.address, b.product_code, b.status from "
//			+ "user a join payment b on a.id = b.user_id where a.id = ?1", nativeQuery = true)
//	List<Object[]> getDetail(Long paymentId);
	
}

