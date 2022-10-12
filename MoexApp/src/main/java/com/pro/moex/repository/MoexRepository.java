package com.pro.moex.repository;

import com.pro.moex.entity.Ticker;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoexRepository extends CrudRepository<Ticker, Long>{
}
