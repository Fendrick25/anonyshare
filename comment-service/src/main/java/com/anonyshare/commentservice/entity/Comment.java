package com.anonyshare.commentservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table
@Entity(name = "comments")
public class Comment {

    @Id
    private UUID id;
    private UUID postId;
    private String content;
    private int likesCount;
    private int dislikeCount;
    private long createdAt;
    private long updatedAt;


    @ManyToOne(fetch = FetchType.EAGER)
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> children;
}
