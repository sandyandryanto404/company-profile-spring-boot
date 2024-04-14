/**
 * This file is part of the Sandy Andryanto Company Profile Website.
 *
 * @author     Sandy Andryanto <sandy.andryanto404@gmail.com>
 * @copyright  2024
 *
 * For the full copyright and license information,
 * please view the LICENSE.md file that was distributed
 * with this source code.
 */

package com.api.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
@Table(
        name="articles",
        indexes = {
                @Index(columnList = "UserId"),
                @Index(columnList = "Image"),
                @Index(columnList = "Title"),
                @Index(columnList = "Slug"),
                @Index(columnList = "Description"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
    @JoinTable(name = "articles_references", joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "reference_id"))
    private Set<Reference> References;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=true)
    @JsonIgnore
    private User User;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Image;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Title;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Slug;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Description;

    @Column(nullable = true, columnDefinition = "text")
    private String Content;

    @ColumnDefault("0")
    @Column(columnDefinition = "int2")
    private int Status;

    @Column(nullable = true)
    private Date CreatedAt;

    @Column(nullable = true)
    private Date UpdatedAt;

    public Set<Reference> getReferences() {
        return References;
    }

    public void setReferences(Set<Reference> references) {
        References = references;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public com.api.backend.models.entities.User getUser() {
        return User;
    }

    public void setUser(com.api.backend.models.entities.User user) {
        User = user;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSlug() {
        return Slug;
    }

    public void setSlug(String slug) {
        Slug = slug;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
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
