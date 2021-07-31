package org.example.repository;

import org.example.model.TodoEntity;
import org.example.model.TodoUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TodoUserRepository extends JpaRepository<TodoUserEntity, Long> {
    Optional<TodoUserEntity> findByUseridAndId(String userid, Long id);
    List<TodoUserEntity> findByUserid(String userid);

    void deleteByUseridAndId(String userid, Long id);
    void deleteByUserid(String userid);
}
