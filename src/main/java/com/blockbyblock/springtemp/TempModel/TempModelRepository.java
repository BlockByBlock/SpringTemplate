package com.blockbyblock.springtemp.TempModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TempModelRepository 
  extends JpaRepository<TempModel, Long> {

  @Query("select t from TempModel t where t.name = ?1")
  Optional<TempModel> findByName(String name);
}
