package org.example.repository;

import org.example.model.TodoEntity;
import org.example.model.TodoUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUserEntity, Long> {
}
