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
    @JoinColumn(name = "userId")
    private Member member;

    private String profileName;

    private String profileEmail;

    @Column(length = 1000)
    private String interest;

    @Column(length = 1000)
    private String techStacks;
}
