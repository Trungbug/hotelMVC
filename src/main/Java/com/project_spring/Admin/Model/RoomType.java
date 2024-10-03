package com.project_spring.Admin.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.repository.Query;

import java.io.Serial;
import java.io.Serializable;

public class RoomType {
    private int roomTypeId;

    @NotEmpty
    private String typeName;

    @NotEmpty
    private String description;

    public RoomType() {
    }

    public RoomType(int roomTypeId, String typeName, String description) {
        this.roomTypeId = roomTypeId;
        this.typeName = typeName;
        this.description = description;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}