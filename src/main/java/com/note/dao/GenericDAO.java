package com.note.dao;

import java.util.List;

import com.note.entities.Note;

/**
 *
 * @author anupkalshetty
 */
public interface GenericDAO<T> {

    void delete(T t);

    void save(T t);

    List<T> getAllObjects();

    public void deleteById(List<Long> ids);

    T findById(long id);
    
    List<Note> find(String t);
}
