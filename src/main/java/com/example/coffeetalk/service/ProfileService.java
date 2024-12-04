package com.example.coffeetalk.service;

import com.example.coffeetalk.dto.ProfileRequest;
import com.example.coffeetalk.entity.Profile;
import com.example.coffeetalk.entity.User;
import com.example.coffeetalk.repository.ProfileRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Autowired
    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public Profile createProfile(ProfileRequest request, User user) {
        Profile profile = request.toEntity(user);

        return this.profileRepository.save(profile);
    }

    public Profile getProfile(User user) {
        return this.profileRepository.findProfileByUser(user);
    }
}
