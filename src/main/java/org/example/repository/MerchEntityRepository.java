package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface MerchEntityRepository extends JpaRepository<MerchEntity, Long> {
    MerchEntity findById(long id);
}
