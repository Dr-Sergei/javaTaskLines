package com.createLines.task.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.regex.Matcher;

@Entity
@Table(name = "line_segments")
public class LineSegment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Double x1;

    @NotNull
    private Double y1;

    @NotNull
    private Double x2;

    @NotNull
    private Double y2;

    @NotNull
    private Double length;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public LineSegment() {
    }

    public LineSegment(Double x1, Double y1, Double x2, Double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.length = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    public Integer id() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotNull Double x1() {
        return x1;
    }

    public void setX1(@NotNull Double x1) {
        this.x1 = x1;
    }

    public @NotNull Double y1() {
        return y1;
    }

    public void setY1(@NotNull Double y1) {
        this.y1 = y1;
    }

    public @NotNull Double x2() {
        return x2;
    }

    public void setX2(@NotNull Double x2) {
        this.x2 = x2;
    }

    public @NotNull Double y2() {
        return y2;
    }

    public void setY2(@NotNull Double y2) {
        this.y2 = y2;
    }

    public @NotNull Double length() {
        return length;
    }

    public void setLength(@NotNull Double length) {
        this.length = length;
    }

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
