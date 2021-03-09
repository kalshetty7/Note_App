package com.note.services;



import java.util.List;

import com.note.entities.Note;

/**
 *
 * @author anupkalshetty
 */
public interface GenericService<T> {
    void delete(T t);
    
    void deleteById(List<Long> deletedIds);
    
    void save(T t);
    
    List<T> getAllObjects();
    
    T findById(long id);
    
    List<Note> find(String t);
}
