package com.crud.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.crud.app.models.UploadModel;
import com.crud.app.models.UserModel;

public interface UploadRepo extends JpaRepository<UploadModel, Long>{
	
	UploadModel findById(long id);
	
//	@Query(value = "select a.name, a.address, b.product_code, b.status from "
//			+ "user a join payment b on a.id = b.user_id where a.id = ?1", nativeQuery = true)
//	List<Object[]> getDetail(Long paymentId);
}

