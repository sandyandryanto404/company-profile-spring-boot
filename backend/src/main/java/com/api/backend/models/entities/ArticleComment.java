package com.api.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(
        name="articles_comments",
        indexes = {
                @Index(columnList = "ParentId"),
                @Index(columnList = "ArticleId"),
                @Index(columnList = "UserId"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class ArticleComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="parent_id", nullable=true)
    @JsonIgnore
    private ArticleComment Parent;

    @ManyToOne
    @JoinColumn(name="article_id", nullable=true)
    @JsonIgnore
    private Article Article;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    @JsonIgnore
    private User User;

    @Column(nullable = false, columnDefinition = "text")
    private String Comment;

    @ColumnDefault("0")
    @Column(columnDefinition = "int2")
    private int Status;

    @Column(nullable = true)
    private Date CreatedAt;

    @Column(nullable = true)
    private Date UpdatedAt;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public ArticleComment getParent() {
        return Parent;
    }

    public void setParent(ArticleComment parent) {
        Parent = parent;
    }

    public com.api.backend.models.entities.Article getArticle() {
        return Article;
    }

    public void setArticle(com.api.backend.models.entities.Article article) {
        Article = article;
    }

    public com.api.backend.models.entities.User getUser() {
        return User;
    }

    public void setUser(com.api.backend.models.entities.User user) {
        User = user;
    }

    public String getComment() {
        return Comment;
    }

    public void setComment(String comment) {
        Comment = comment;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        UpdatedAt = updatedAt;
    }
}
