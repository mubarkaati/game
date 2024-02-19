package com.pairing.puzzlegame.entities;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
public abstract class Audit {

    @Id
    private final String id = UUID.randomUUID().toString();

    private final LocalDateTime createdTime = LocalDateTime.now();

    private String updatedBy;

    private LocalDateTime updatedTime;

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public String getId() {
        return id;
    }
}