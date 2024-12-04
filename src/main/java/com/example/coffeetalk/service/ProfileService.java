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
    private final UserService userService;

    @Autowired
    public ProfileService(ProfileRepository profileRepository, UserService userService) {
        this.profileRepository = profileRepository;
        this.userService = userService;
    }

    public Profile createProfile(ProfileRequest request) {
        User user = this.userService.findUser(request.getUsername());

        if (user != null) {
            Profile profile = request.toEntity(user);
            Profile newProfile = this.profileRepository.save(profile);

            if (newProfile != null) {
                return newProfile;
            }
        }

        log.error("Failed to create profile");
        return null;
    }
}
