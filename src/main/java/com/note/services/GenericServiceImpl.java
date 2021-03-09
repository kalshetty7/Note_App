package com.note.services;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.note.dao.GenericDAO;
import com.note.entities.Note;


/**
 *
 * @author anupkalshetty
 */
@Service
@Transactional
public class GenericServiceImpl<T> implements GenericService<T> {
	
	@Autowired
	GenericDAO<T> genericDAO;

    @Override
    public void delete(T t) {
        genericDAO.delete(t);
    }

    @Override
    public void save(T t) {
        genericDAO.save(t);
    }

    @Override
    public List<T> getAllObjects() {
    	return genericDAO.getAllObjects();
    }

    @Override
    public T findById(long id) {
    	return genericDAO.findById(id);
    }

	@Override
	public void deleteById(List<Long> ids) {
		genericDAO.deleteById(ids);
	}

	@Override
	public List<Note> find(String t) {
		return genericDAO.find(t);
	}

    
}
