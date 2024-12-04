package com.example.coffeetalk.controller;

import com.example.coffeetalk.dto.ProfileRequest;
import com.example.coffeetalk.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;

    @Autowired
    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    /* TODO : 프로필 조회 API
    *   */

    /* TODO : 프로필 생성 API
    * */
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequest request) {
        profileService.createProfile(request);

        return ResponseEntity.ok("Profile created successfully!");
    }
}
