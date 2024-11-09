package com.createLines.task.serviceTest;

import com.createLines.task.model.LineSegment;
import com.createLines.task.service.LineSegmentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional /** Führt sicher, dass Datenbankänderungen nach jedem Test zurückgesetzt werden*/
public class LineSegmentServiceTests {

    @Autowired
    private LineSegmentService lineSegmentService;

    @Test
    void testDeleteAllLineSegments() {
        LineSegment segment1 = new LineSegment(0d, 0d, 100d, 100d);
        lineSegmentService.save(segment1);
        LineSegment segment2 = new LineSegment(100d, 100d, 200d, 200d);
        lineSegmentService.save(segment2);


        lineSegmentService.deleteAllLineSegments();
        assertTrue(lineSegmentService.getAllLineSegments().isEmpty());
    }

    @Test
    public void testSaveAndRetrieveLineSegments(){
        LineSegment lineSegment = new LineSegment(0d,0d,100d,100d);
        lineSegmentService.deleteAllLineSegments();
        lineSegmentService.save(lineSegment);

        List<LineSegment> segments = lineSegmentService.getAllLineSegments();
        assertEquals(1, segments.size());
    }
}
