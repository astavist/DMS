package com.astavist.DMS.service;

import com.astavist.DMS.model.DiaryEntry;
import com.astavist.DMS.repository.DiaryEntryRepository;
import com.astavist.DMS.service.abstracts.DiaryEntryService;
import com.astavist.DMS.service.dto.requests.CreateDiaryRequest;
import com.astavist.DMS.service.dto.requests.UpdateDiaryRequest;
import com.astavist.DMS.service.dto.responses.CreateDiaryResponse;
import com.astavist.DMS.service.dto.responses.GetAllDiariesResponse;
import com.astavist.DMS.service.dto.responses.GetDiaryResponse;
import com.astavist.DMS.service.dto.responses.UpdateDiaryResponse;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DiaryEntryManager implements DiaryEntryService {
    private DiaryEntryRepository repository;
    private ModelMapper mapper;

    //TODO: Exception Handlers

    public CreateDiaryResponse create(CreateDiaryRequest request) {
        DiaryEntry entry = mapper.map(request,DiaryEntry.class);
        entry.setCreatedDate(LocalDateTime.now());
        entry.setId(UUID.randomUUID());
        repository.save(entry);

        return mapper.map(entry,CreateDiaryResponse.class);
    }

    @Override
    public UpdateDiaryResponse update(UUID id, UpdateDiaryRequest request) {
        DiaryEntry entry = mapper.map(request,DiaryEntry.class);
        repository.deleteById(id);
        entry.setId(id);
        repository.save(entry);
        return mapper.map(entry,UpdateDiaryResponse.class);
    }

    @Override
    public GetDiaryResponse getById(UUID id) {
        DiaryEntry entry = mapper.map(repository.getReferenceById(id),DiaryEntry.class);
        return mapper.map(entry,GetDiaryResponse.class);
    }

    @Override
    public List<GetAllDiariesResponse> getAll() {
        var entries = repository.findAll();
        return entries.stream()
                .map(DiaryEntry -> mapper
                        .map(DiaryEntry, GetAllDiariesResponse.class))
                .toList();
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }

}
