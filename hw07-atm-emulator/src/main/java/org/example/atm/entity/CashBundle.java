package org.example.atm.entity;

import lombok.AllArgsConstructor;

import java.util.Map;

@AllArgsConstructor
public class CashBundle {

    private final Map<NoteType, Integer> notes;

    public int getNoteTypeCount(NoteType noteType) {
        return notes.getOrDefault(noteType, 0);
    }
}
