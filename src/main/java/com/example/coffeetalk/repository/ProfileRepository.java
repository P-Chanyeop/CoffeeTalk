package com.example.coffeetalk.repository;

import com.example.coffeetalk.entity.Member;
import com.example.coffeetalk.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    Profile findProfileByMember(Member member);
}
