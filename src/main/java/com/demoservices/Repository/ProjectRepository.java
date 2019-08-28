package com.demoservices.Repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.demoservices.Model.Projects;

public interface ProjectRepository extends JpaRepository<Projects, String>,PagingAndSortingRepository<Projects, String>  {

	 Page<Projects> findAll(Pageable pageable);
	 
	 @Query(value="(SELECT *  FROM `projects` where project_Name like %:search% or consultant_Name like %:search% or description like %:search%)", nativeQuery = true)
	  List<Projects> searchresultIgnoreCase(@Param("search") String search);

}
