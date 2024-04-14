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
        name="contacts",
        indexes = {
                @Index(columnList = "Name"),
                @Index(columnList = "Email"),
                @Index(columnList = "Subject"),
                @Index(columnList = "Status"),
                @Index(columnList = "CreatedAt"),
                @Index(columnList = "UpdatedAt")
        }
)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Name;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Email;

    @Column(nullable = false, columnDefinition = "varchar(191)")
    private String Subject;

    @Column(nullable = false, columnDefinition = "text")
    private String Message;

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

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String subject) {
        Subject = subject;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
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
