package com.pramodvaddiraju.logbook_backend.controller;

import com.pramodvaddiraju.logbook_backend.dto.NoteRequestDto;
import com.pramodvaddiraju.logbook_backend.dto.NoteResponseDto;
import com.pramodvaddiraju.logbook_backend.service.NoteService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private static final Logger log = LoggerFactory.getLogger(NoteController.class);


    private NoteService noteService;

    public NoteController(NoteService noteService){
        this.noteService = noteService;
    }

    @PostMapping
    ResponseEntity<NoteResponseDto> saveNote(@Valid @RequestBody NoteRequestDto noteRequestDto){
        log.info("Create note request received with title = {}" , noteRequestDto.getTitle());
        return ResponseEntity.status(201)
                .body(noteService.createNote(noteRequestDto));
    }

    @GetMapping("/title")
    ResponseEntity<NoteResponseDto> getByTitle(@RequestParam String title){
        return ResponseEntity.ok(noteService.findByTitleIgnoreCase(title).get());
    }


    @GetMapping("/{id}")
    ResponseEntity<NoteResponseDto> getById(@PathVariable Long id){
        return ResponseEntity.ok().body(noteService.getNoteById(id));
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteById(@PathVariable Long id){
        noteService.deleteNoteById(id);
        return ResponseEntity.noContent().build();
    }

}
