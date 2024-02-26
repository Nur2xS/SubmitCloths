package com.textilia.submitcloths.entities;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "cloth")
public class Cloth {

    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "size", nullable = false, length = 10)
    private String size;

    @Column(name = "color", nullable = false, length = 10)
    private String color;

    @Column(name = "created_timestamp")
    private LocalDateTime createdTimestamp;

    @Column(name = "updated_timestamp")
    private LocalDateTime updatedTimestamp;

    @PrePersist
    protected void onCreate() {
        createdTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedTimestamp = LocalDateTime.now();
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(LocalDate createdTimestamp) {
        this.createdTimestamp = createdTimestamp.atStartOfDay();
    }

    public LocalDateTime getUpdatedTimestamp() {
        return updatedTimestamp;
    }

    public void setUpdatedTimestamp(LocalDate updatedTimestamp) {
        this.updatedTimestamp = updatedTimestamp.atStartOfDay();
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                ", updatedTimestamp=" + updatedTimestamp +
                '}';
    }

}
