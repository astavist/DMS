package com.astavist.DMS.service.abstracts;

import com.astavist.DMS.service.dto.requests.CreateDiaryRequest;
import com.astavist.DMS.service.dto.requests.UpdateDiaryRequest;
import com.astavist.DMS.service.dto.responses.CreateDiaryResponse;
import com.astavist.DMS.service.dto.responses.GetAllDiariesResponse;
import com.astavist.DMS.service.dto.responses.GetDiaryResponse;
import com.astavist.DMS.service.dto.responses.UpdateDiaryResponse;

import java.util.List;
import java.util.UUID;

public interface DiaryEntryService {
    CreateDiaryResponse create(CreateDiaryRequest request);
    UpdateDiaryResponse update(UUID id,UpdateDiaryRequest request);
    GetDiaryResponse getById(UUID id);
    List<GetAllDiariesResponse> getAll();
    void delete(UUID id);

}
