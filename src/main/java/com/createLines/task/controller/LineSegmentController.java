package com.createLines.task.controller;


import com.createLines.task.model.LineSegment;
import com.createLines.task.service.LineSegmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

@Controller
@RequestMapping({"/line-segments", "/"})
public class LineSegmentController {

    @Autowired
    private LineSegmentService lineSegmentService;

    @GetMapping
    public String showLineSegments(Model model) {
        List<LineSegment> segments = lineSegmentService.getAllLineSegments();
        Collections.sort(segments, Comparator.comparing(LineSegment::getLength));
        model.addAttribute("segments", segments);
        return "lineSegments";

    }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {

        try {
            lineSegmentService.deleteAllLineSegments();

            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(" ");

                if (parts.length != 4) {
                    throw new RuntimeException("Wrong file format. Needed 4 coordinates to create line");
                }
                Double x1 = Double.parseDouble(parts[0]);
                Double y1 = Double.parseDouble(parts[1]);
                Double x2 = Double.parseDouble(parts[2]);
                Double y2 = Double.parseDouble(parts[3]);

                LineSegment segment = new LineSegment(x1, y1, x2, y2);

                lineSegmentService.save(segment);
            }
            redirectAttributes.addFlashAttribute("message", "File successfully uploaded");

        } catch (IOException e) {
            redirectAttributes.addFlashAttribute("message", String.format("Error while uploading file: {} ", e));
        }

        return "redirect:/line-segments";
    }
}
