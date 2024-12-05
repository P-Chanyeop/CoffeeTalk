package com.example.coffeetalk.repository;

import com.example.coffeetalk.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Member, Integer>{

    Member findByUsername(String username);
}
