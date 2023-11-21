package com.example.event.entity;

import com.example.event.common.enums.EventType;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
//    @Version
//    private Long version;
    private String title;
    private String description;
    private EventType eventType;
    private String location;
    private LocalDate staringDate;
    private LocalDateTime endingDate;
    @CreationTimestamp
    @Column(name = "created_date")
    private Date createdDate;

    @UpdateTimestamp
    @Column(name = "last_modified_date")
    private Date lastModifiedDate;

    @PrePersist
    public void prePersist() {
        this.createdDate = new Date();
        this.lastModifiedDate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastModifiedDate = new Date();
    }
}
