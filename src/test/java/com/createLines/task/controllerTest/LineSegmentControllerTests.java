package com.createLines.task.controllerTest;

import com.createLines.task.controller.LineSegmentController;
import com.createLines.task.model.LineSegment;
import com.createLines.task.service.LineSegmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LineSegmentController.class)
public class LineSegmentControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LineSegmentService lineSegmentService;

    @Test
    void testGetLineSegments() throws Exception {
        lineSegmentService.deleteAllLineSegments();
        LineSegment segment = new LineSegment(0d,0d,100d,100d);
        when(lineSegmentService.getAllLineSegments()).thenReturn(List.of(segment));

        mockMvc.perform(get("/line-segments"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("segments"))
                .andExpect(view().name("lineSegments"));
    }
    }

