package com.createLines.task.repository;

import com.createLines.task.model.LineSegment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineSegmentRepository extends JpaRepository<LineSegment, Long> {
}
