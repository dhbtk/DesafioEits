package com.stockontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stockontrol.entity.Batch;

@Repository("batchRepository")
public interface BatchRepository extends JpaRepository<Batch, Long>
{

}
