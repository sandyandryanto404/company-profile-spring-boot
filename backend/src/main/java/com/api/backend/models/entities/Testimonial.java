package com.api.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(
        name="testimonials",
        indexes = {
                @Index(columnList = "CustomerId"),
                @Index(columnList = "Image"),
                @Index(columnList = "Name"),
                @Index(columnList = "Position"),
                @Index(columnList = "Sort"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Testimonial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=true)
    @JsonIgnore
    private Customer Customer;

    @Column(nullable = true, columnDefinition = "varchar(255)")
    private String Image;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Name;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Position;

    @Column(nullable = false, columnDefinition = "text")
    private String Quote;

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

    public com.api.backend.models.entities.Customer getCustomer() {
        return Customer;
    }

    public void setCustomer(com.api.backend.models.entities.Customer customer) {
        Customer = customer;
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

    public String getPosition() {
        return Position;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getQuote() {
        return Quote;
    }

    public void setQuote(String quote) {
        Quote = quote;
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
