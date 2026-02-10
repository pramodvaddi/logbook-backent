package com.pramodvaddiraju.logbook_backend.service;

import com.pramodvaddiraju.logbook_backend.dto.NoteRequestDto;
import com.pramodvaddiraju.logbook_backend.dto.NoteResponseDto;
import com.pramodvaddiraju.logbook_backend.entity.Note;
import com.pramodvaddiraju.logbook_backend.exception.ResourceNotFoundException;
import com.pramodvaddiraju.logbook_backend.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NoteServiceImpl implements NoteService{

    private NoteRepository noteRepository;
    private ModelMapper modelMapper;

    public NoteServiceImpl(NoteRepository noteRepository, ModelMapper modelMapper){
        this.modelMapper = modelMapper;
        this.noteRepository = noteRepository;
    }


    @Override
    public NoteResponseDto createNote(NoteRequestDto noteRequestDto) {
        Note note = modelMapper.map(noteRequestDto, Note.class);
        Note savedNote = noteRepository.save(note);
        return modelMapper.map(savedNote, NoteResponseDto.class);
    }

    @Override
    public Page<NoteResponseDto> getAllNotes(Pageable pageable) {
      return noteRepository.findAll(pageable).map(
              note-> modelMapper.map(note, NoteResponseDto.class));
    }

    @Override
    public Optional<NoteResponseDto> findByTitleIgnoreCase(String title) {
        Note note = noteRepository.findByTitleIgnoreCase(title).orElseThrow(
                ()-> new ResourceNotFoundException("Not found" + title)
        );
        return Optional.of(modelMapper.map(note, NoteResponseDto.class));

    }


    @Override
    public NoteResponseDto getNoteById(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() ->new ResourceNotFoundException("Not found with id: " + id));

        return modelMapper.map(note, NoteResponseDto.class);

    }

    @Override
    public void deleteNoteById(Long id) {
        noteRepository.deleteById(id);

    }
}
