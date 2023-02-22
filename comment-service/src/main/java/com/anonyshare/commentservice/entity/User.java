package com.anonyshare.commentservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity(name = "users")
public class User {

    @Id
    private UUID id;
    private String username;

    @Enumerated(EnumType.STRING)
    private UserStatus status;
    private String imageUrl;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(unique = true)
    private Set<UUID> likedComments;

    @ElementCollection(fetch = FetchType.EAGER)
    @Column(unique = true)
    private Set<UUID> dislikedComments;
}
