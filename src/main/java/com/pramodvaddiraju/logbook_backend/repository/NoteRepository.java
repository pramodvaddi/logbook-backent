package com.pramodvaddiraju.logbook_backend.repository;

import com.pramodvaddiraju.logbook_backend.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {



}
