package com.createLines.task.serviceTest;

import com.createLines.task.model.LineSegment;
import com.createLines.task.service.LineSegmentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional /** Führt sicher, dass Datenbankänderungen nach jedem Test zurückgesetzt werden*/
public class LineSegmentServiceTests {

    @Autowired
    private LineSegmentService lineSegmentService;

    @Test
    public void testSaveAndRetrieveLineSegments(){
        LineSegment lineSegment = new LineSegment(0d,0d,100d,100d);
        lineSegmentService.save(lineSegment);

        List<LineSegment> segments = lineSegmentService.getAllLineSegments();
        assertEquals(1, segments.size());
    }
}
