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

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(
        name="sliders",
        indexes = {
                @Index(columnList = "Image"),
                @Index(columnList = "Title"),
                @Index(columnList = "Sort"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Image;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Title;

    @Column(nullable = true, columnDefinition = "text")
    private String Description;

    @Column(nullable = true, columnDefinition = "text")
    private String Link;

    @ColumnDefault("0")
    @Column(columnDefinition = "int4")
    private int Sort;

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

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getLink() {
        return Link;
    }

    public void setLink(String link) {
        Link = link;
    }

    public int getSort() {
        return Sort;
    }

    public void setSort(int sort) {
        Sort = sort;
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
