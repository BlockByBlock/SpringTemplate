package com.blockbyblock.springtemp.TempModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TempModelRepository 
  extends JpaRepository<TempModel, Long> {
  
}
