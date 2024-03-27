package com.api.backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;

@Entity
@Table(
        name="portfolios",
        indexes = {
                @Index(columnList = "CustomerId"),
                @Index(columnList = "ReferenceId"),
                @Index(columnList = "Title"),
                @Index(columnList = "ProjectDate"),
                @Index(columnList = "Sort"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=true)
    @JsonIgnore
    private Customer Customer;

    @ManyToOne
    @JoinColumn(name="reference_id", nullable=true)
    @JsonIgnore
    private Reference Reference;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Title;

    @Column(nullable = true, columnDefinition = "text")
    private String Description;

    @Column(nullable = true)
    private Date ProjectDate;

    @Column(nullable = true, columnDefinition = "text")
    private String ProjectUrl;

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

    public com.api.backend.models.entities.Reference getReference() {
        return Reference;
    }

    public void setReference(com.api.backend.models.entities.Reference reference) {
        Reference = reference;
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

    public Date getProjectDate() {
        return ProjectDate;
    }

    public void setProjectDate(Date projectDate) {
        ProjectDate = projectDate;
    }

    public String getProjectUrl() {
        return ProjectUrl;
    }

    public void setProjectUrl(String projectUrl) {
        ProjectUrl = projectUrl;
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
