package com.astavist.DMS.service;

import com.astavist.DMS.model.DiaryEntry;
import com.astavist.DMS.repository.DiaryEntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DiaryEntryService {
    private final DiaryEntryRepository diaryEntryRepository;

    @Autowired
    public DiaryEntryService(DiaryEntryRepository diaryEntryRepository) {
        this.diaryEntryRepository = diaryEntryRepository;
    }

    public DiaryEntry saveEntry(DiaryEntry entry) {
        return diaryEntryRepository.save(entry);
    }

    public List<DiaryEntry> getAllEntries() {
        return diaryEntryRepository.findAll();
    }

    public Optional<DiaryEntry> getEntryById(UUID id) {
        return diaryEntryRepository.findById(id);
    }

    public void deleteEntry(UUID id) {
        diaryEntryRepository.deleteById(id);
    }
}
