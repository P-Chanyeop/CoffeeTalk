package com.example.coffeetalk.repository;

import com.example.coffeetalk.entity.Profile;
import com.example.coffeetalk.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findProfileByUser(User user);
}
