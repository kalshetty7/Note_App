package com.note.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.note.entities.Note;

/**
 *
 * @author anupkalshetty
 */
@Repository
@Transactional
public class GenericDAOImpl<T> implements GenericDAO<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    private Query query(String q) {
        return currentSession().createQuery(q);
    }

    @Override
    public void delete(T t) {
        currentSession().delete(t);
    }

    @Override
    public void save(T t) {
        currentSession().saveOrUpdate(t);
    }

    @Override
    public List<T> getAllObjects() {
        Query q = query("from Note");
        return q.list();
    }

    @Override
    public T findById(long id) {
        Query q = query("from Note e where e.id = :id");
        q.setParameter("id", id);
        List<T> l = q.list();
        if (l != null && l.size() > 0) {
            return l.get(0);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(List<Long> ids) {
        /*
		Query q = query("delete from Note where id in (:ids)");
		q.setParameter("ids", ids);
		try {
			q.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
         */
        for (Long id : ids) {
            Note e = (Note) findById(id);
            delete((T) e);
        }
    }

	@Override
	public List<Note> find(String t) {
		List<Note> noteList=new ArrayList<>();
		Query q = query("from Note n where LOWER(n.text) like '%"+t.toLowerCase()+"%' or LOWER(n.title) like '%"+t.toLowerCase()+"%'");
		try {
			noteList=q.list();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noteList;
	}

}
