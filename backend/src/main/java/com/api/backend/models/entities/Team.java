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
        name="teams",
        indexes = {
                @Index(columnList = "Image"),
                @Index(columnList = "Name"),
                @Index(columnList = "Email"),
                @Index(columnList = "Phone"),
                @Index(columnList = "PositionName"),
                @Index(columnList = "Twitter"),
                @Index(columnList = "Facebook"),
                @Index(columnList = "Instagram"),
                @Index(columnList = "LinkedIn"),
                @Index(columnList = "Sort"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Image;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Name;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Email;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Phone;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String PositionName;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Twitter;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Facebook;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Instagram;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String LinkedIn;

    @Column(nullable = true, columnDefinition = "text")
    private String Address;

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

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getPositionName() {
        return PositionName;
    }

    public void setPositionName(String positionName) {
        PositionName = positionName;
    }

    public String getTwitter() {
        return Twitter;
    }

    public void setTwitter(String twitter) {
        Twitter = twitter;
    }

    public String getFacebook() {
        return Facebook;
    }

    public void setFacebook(String facebook) {
        Facebook = facebook;
    }

    public String getInstagram() {
        return Instagram;
    }

    public void setInstagram(String instagram) {
        Instagram = instagram;
    }

    public String getLinkedIn() {
        return LinkedIn;
    }

    public void setLinkedIn(String linkedIn) {
        LinkedIn = linkedIn;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
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
