package com.astavist.DMS.service.dto.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDiaryRequest {

    private UUID id;
    private String title;
    @Column(length = 10000)
    private String content;
    private LocalDateTime createdDate;
}
