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

@Entity
@Table(
        name="portfolios_images",
        indexes = {
                @Index(columnList = "PortfolioId"),
                @Index(columnList = "Image"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class PortfolioImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @ManyToOne
    @JoinColumn(name="portfolio_id", nullable=true)
    @JsonIgnore
    private Portfolio Portfolio;

    @Column(nullable = false, columnDefinition = "varchar(255)")
    private String Image;

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

    public com.api.backend.models.entities.Portfolio getPortfolio() {
        return Portfolio;
    }

    public void setPortfolio(com.api.backend.models.entities.Portfolio portfolio) {
        Portfolio = portfolio;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
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
