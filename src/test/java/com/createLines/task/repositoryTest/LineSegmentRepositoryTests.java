package com.createLines.task.repositoryTest;

import com.createLines.task.model.LineSegment;
import com.createLines.task.repository.LineSegmentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LineSegmentRepositoryTests {

    @Autowired
    private LineSegmentRepository lineSegmentRepository;


    @Test
    void testSaveAndFindLineSegment() {
        lineSegmentRepository.deleteAll(); // Löscht alle bestehenden Einträge

        // Erstelle ein neues LineSegment und speichere es
        LineSegment segment = new LineSegment(0d, 0d, 100d, 100d);
        lineSegmentRepository.save(segment);

        // Holt alle LineSegments aus der DB und prüft die Größe
        List<LineSegment> segments = lineSegmentRepository.findAll();
        assertEquals(1, segments.size());
        assertEquals(segment.getX1(), segments.get(0).getX1());
    }
}
