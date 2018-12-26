package com.meli.mutantes.repository;

import com.meli.mutantes.domain.DnaTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DnaTypeRepository extends JpaRepository<DnaTypeEntity, Long> {

    @Query("SELECT new com.meli.mutantes.repository.DnaTypeCount(d.dnaType, COUNT(*)) " +
            "FROM DnaTypeEntity d GROUP BY d.dnaType")
    List<DnaTypeCount> countHumansAndMutants();

}
