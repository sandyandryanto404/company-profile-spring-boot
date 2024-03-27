package com.api.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.Set;

@Entity
@Table(
        name="users",
        indexes = {
                @Index(columnList = "Image"),
                @Index(columnList = "FirstName"),
                @Index(columnList = "LastName"),
                @Index(columnList = "Gender"),
                @Index(columnList = "Country"),
                @Index(columnList = "Province"),
                @Index(columnList = "Regency"),
                @Index(columnList = "PostalCode"),
                @Index(columnList = "BirthPlace"),
                @Index(columnList = "BirthDate"),
                @Index(columnList = "Username"),
                @Index(columnList = "Email"),
                @Index(columnList = "Phone"),
                @Index(columnList = "Password"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String Image;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String FirstName;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String LastName;

    @Column(nullable = true, columnDefinition = "varchar(2)")
    private String Gender;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String Country;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String Province;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String Regency;

    @Column(nullable = true, columnDefinition = "varchar(64)")
    private String PostalCode;

    @Column(nullable = true, columnDefinition = "varchar(191)")
    private String BirthPlace;

    @Column(nullable = true)
    private Date BirthDate;

    @Column(nullable = true, columnDefinition = "text")
    private String Address;

    @Column(nullable = true, columnDefinition = "text")
    private String AboutMe;

    @Column(nullable = false, columnDefinition = "varchar(191)", unique=true)
    private String Username;

    @Column(nullable = false, columnDefinition = "varchar(191)", unique=true)
    private String Email;

    @Column(nullable = false, columnDefinition = "varchar(64)", unique=true)
    private String Phone;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Password;

    @ColumnDefault("0")
    @Column(columnDefinition = "int2")
    private int Status;

    @Column(nullable = true)
    private Date CreatedAt;

    @Column(nullable = true)
    private Date UpdatedAt;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> Roles;

    public Set<Role> getRoles() {
        return Roles;
    }

    public void setRoles(Set<Role> roles) {
        Roles = roles;
    }

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

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getRegency() {
        return Regency;
    }

    public void setRegency(String regency) {
        Regency = regency;
    }

    public String getPostalCode() {
        return PostalCode;
    }

    public void setPostalCode(String postalCode) {
        PostalCode = postalCode;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAboutMe() {
        return AboutMe;
    }

    public void setAboutMe(String aboutMe) {
        AboutMe = aboutMe;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
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

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
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
