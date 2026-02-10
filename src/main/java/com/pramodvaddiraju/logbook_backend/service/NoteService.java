package com.pramodvaddiraju.logbook_backend.service;

import com.pramodvaddiraju.logbook_backend.dto.NoteRequestDto;
import com.pramodvaddiraju.logbook_backend.dto.NoteResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface NoteService {

    NoteResponseDto createNote(NoteRequestDto noteRequestDto);
    Page<NoteResponseDto> getAllNotes(Pageable pageable);
    Optional<NoteResponseDto> findByTitleIgnoreCase(String title);
    NoteResponseDto getNoteById(Long id);
    void deleteNoteById(Long id);

}
