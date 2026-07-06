package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.entity.Library;
@Repository
public interface LibraryRepository extends JpaRepository<Library,Integer> {

}
