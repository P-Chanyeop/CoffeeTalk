package com.example.coffeetalk.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer profileId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String profileName;

    @Column(length = 1000)
    private String interest;

    @Column(length = 1000)
    private String techStacks;
}
