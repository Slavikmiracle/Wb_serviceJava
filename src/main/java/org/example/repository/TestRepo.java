package org.example.repository;

import org.springframework.data.repository.CrudRepository;

public interface TestRepo extends CrudRepository<TestEntity, Integer> {
        TestEntity findById(int id);
}