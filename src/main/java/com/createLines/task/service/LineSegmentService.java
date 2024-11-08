package com.createLines.task.service;

import com.createLines.task.model.LineSegment;
import com.createLines.task.repository.LineSegmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LineSegmentService {

    @Autowired
    private LineSegmentRepository lineSegmentRepository;

    public List<LineSegment> getAllLineSegments() {
        return lineSegmentRepository.findAll();
    }

    public void mergeLineSegments() {
        //toDo Hier muss die Logik implementiert werden, die überprüft, ob Linien zusammengeführt werden können
    }

    public LineSegment save(LineSegment lineSegment) {
        return lineSegmentRepository.save(lineSegment);
    }

    public void deleteAllLineSegments() {
        lineSegmentRepository.deleteAll();
    }
}
