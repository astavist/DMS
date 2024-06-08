package com.astavist.DMS.controller;

import com.astavist.DMS.service.DiaryEntryManager;
import com.astavist.DMS.service.dto.requests.CreateDiaryRequest;
import com.astavist.DMS.service.dto.requests.UpdateDiaryRequest;
import com.astavist.DMS.service.dto.responses.CreateDiaryResponse;
import com.astavist.DMS.service.dto.responses.GetAllDiariesResponse;
import com.astavist.DMS.service.dto.responses.GetDiaryResponse;
import com.astavist.DMS.service.dto.responses.UpdateDiaryResponse;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/diary-entries")
@CrossOrigin(origins = "http://localhost:3000")
@AllArgsConstructor
public class DiaryEntryController {
    private final DiaryEntryManager service;

    @PostMapping
    public CreateDiaryResponse createEntry(@RequestBody CreateDiaryRequest request) {
        return service.create(request);
    }
    @GetMapping
    public List<GetAllDiariesResponse> getAllEntries() {
        return service.getAll();
    }
    @GetMapping("/{id}")
    public GetDiaryResponse getEntryById(@PathVariable UUID id) {
        return service.getById(id);
    }
    @PutMapping("/{id}")
    public UpdateDiaryResponse updateEntry(UUID id, UpdateDiaryRequest request){
        return service.update(id,request);
    }
    @DeleteMapping("/{id}")
    public void deleteEntry(@PathVariable UUID id) {
        service.delete(id);
    }
}
