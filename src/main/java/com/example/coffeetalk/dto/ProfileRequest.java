package com.example.coffeetalk.dto;

import com.example.coffeetalk.entity.Profile;
import com.example.coffeetalk.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProfileRequest {
    private String username;
    private String email;
    private List<String> interests;
    private List<String> techStacks;

    public Profile toEntity(User user){
        return Profile.builder()
                .profileName(username)
                .profileEmail(email)
                .interest(String.join(",", interests))
                .techStacks(String.join(",", techStacks))
                .build();
    }
}
