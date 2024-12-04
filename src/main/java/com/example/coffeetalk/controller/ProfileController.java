package com.example.coffeetalk.controller;

import com.example.coffeetalk.dto.ProfileRequest;
import com.example.coffeetalk.entity.Profile;
import com.example.coffeetalk.entity.User;
import com.example.coffeetalk.service.ProfileService;
import com.example.coffeetalk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {

    private final ProfileService profileService;
    private final UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    /* TODO : 프로필 조회 API
    *   */
    public ResponseEntity<Profile> getProfile(@RequestBody ProfileRequest request) {

        User user = this.userService.findUser(request.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Profile profile = this.profileService.getProfile(user);

        if (profile == null) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok(profile);
    }

    /* TODO : 프로필 생성 API
    * */
    public ResponseEntity<?> createProfile(@RequestBody ProfileRequest request) {
        User user = this.userService.findUser(request.getUsername());

        if (user == null) {
            return ResponseEntity.badRequest().body("User not found");
        }

        Profile newProfile = this.profileService.createProfile(request, user);

        if (newProfile == null) {
            return ResponseEntity.badRequest().body("Failed to create profile");
        }

        return ResponseEntity.ok("Profile created successfully!");
    }
}
