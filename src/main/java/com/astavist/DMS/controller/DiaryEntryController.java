package com.astavist.DMS.controller;

import com.astavist.DMS.model.DiaryEntry;
import com.astavist.DMS.service.DiaryEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/diary-entries")
@CrossOrigin(origins = "http://localhost:3000")
public class DiaryEntryController {
    private final DiaryEntryService diaryEntryService;

    @Autowired
    public DiaryEntryController(DiaryEntryService diaryEntryService) {
        this.diaryEntryService = diaryEntryService;
    }

    @PostMapping
    public ResponseEntity<DiaryEntry> createEntry(@RequestBody DiaryEntry diaryEntry) {
        diaryEntry.setCreatedDate(LocalDateTime.now());
        DiaryEntry savedEntry = diaryEntryService.saveEntry(diaryEntry);
        return ResponseEntity.ok(savedEntry);
    }

    @GetMapping
    public ResponseEntity<List<DiaryEntry>> getAllEntries() {
        List<DiaryEntry> diaryEntries = diaryEntryService.getAllEntries();
        return ResponseEntity.ok(diaryEntries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DiaryEntry> getEntryById(@PathVariable UUID id) {
        Optional<DiaryEntry> diaryEntry = diaryEntryService.getEntryById(id);
        return diaryEntry.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable UUID id) {
        diaryEntryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}
