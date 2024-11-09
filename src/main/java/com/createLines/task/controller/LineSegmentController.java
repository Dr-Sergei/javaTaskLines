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
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Der Controller für LineSegment. Dieser steuert die HTTP-Anfragen und Interaktionen mit der View (HTML).
@Controller
@RequestMapping({"/line-segments", "/"})  // Mapping für die Endpunkte "/line-segments" und die root URL "/"
public class LineSegmentController {

    // Spring wird automatisch den LineSegmentService injizieren, um die Geschäftslogik zu verwenden.
    @Autowired
    private LineSegmentService lineSegmentService;

    // GET-Mapping für die Anzeige aller Line Segments
    @GetMapping
    public String showLineSegments(Model model) {
        // Ruft alle LineSegments aus der Datenbank ab
        List<LineSegment> segments = lineSegmentService.getAllLineSegments();

        // Erstellung einer kopierten Liste, um sicherzustellen, dass die ursprüngliche Liste nicht verändert wird
        List<LineSegment> sortableList = new ArrayList<>(segments);  // Sicherstellung, dass die Collection mutable ist

        // Sortierung der Liste nach der Länge des LineSegments
        sortableList.sort(Comparator.comparing(LineSegment::getLength));  // Vergleiche nach der Länge der Liniensegmente

        // Hinzufügen der sortierten Liste als Attribut im Model, damit sie in der View (HTML) angezeigt werden kann
        model.addAttribute("segments", sortableList);

        // Rückgabe des Namens der HTML-Template-Datei, die die View repräsentiert
        return "lineSegments";
    }

    // POST-Mapping für den Datei-Upload von Liniensegmenten
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        try {
            // Löscht alle bestehenden LineSegments aus der Datenbank, bevor neue hinzugefügt werden
            lineSegmentService.deleteAllLineSegments();

            // BufferedReader liest die hochgeladene Datei Zeile für Zeile
            BufferedReader reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                // Aufteilen der Zeile in vier Teile (x1, y1, x2, y2) durch Leerzeichen
                String[] parts = line.split(" ");

                // Überprüfen, ob genau 4 Werte für ein LineSegment vorhanden sind
                if (parts.length != 4) {
                    throw new RuntimeException("Wrong file format. Needed 4 coordinates to create line");
                }

                // Parsen der Werte zu Double, da Koordinaten als Gleitkommazahlen erwartet werden
                Double x1 = Double.parseDouble(parts[0]);
                Double y1 = Double.parseDouble(parts[1]);
                Double x2 = Double.parseDouble(parts[2]);
                Double y2 = Double.parseDouble(parts[3]);

                // Erstellen eines neuen LineSegment-Objekts mit den aus der Datei extrahierten Koordinaten
                LineSegment segment = new LineSegment(x1, y1, x2, y2);

                // Speichern des LineSegments in der Datenbank
                lineSegmentService.save(segment);
            }

            // Eine Erfolgsnachricht wird für die Weiterleitung gesetzt
            redirectAttributes.addFlashAttribute("message", "File successfully uploaded");

        } catch (IOException e) {
            // Falls ein Fehler beim Dateilesen oder -verarbeiten auftritt, wird eine Fehlermeldung gesetzt
            redirectAttributes.addFlashAttribute("message", String.format("Error while uploading file: {} ", e.getMessage()));
        }

        // Weiterleitung an die URL "/line-segments", damit die Liste der LineSegments erneut angezeigt wird
        return "redirect:/line-segments";
    }
}
