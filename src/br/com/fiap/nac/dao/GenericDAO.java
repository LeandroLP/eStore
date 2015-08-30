package br.com.fiap.nac.dao;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> getAll() throws ClassNotFoundException, SQLException;

	public T save(T object) throws ClassNotFoundException, SQLException;

	public T get(Integer id) throws ClassNotFoundException, SQLException;

	public boolean update(T object) throws ClassNotFoundException, SQLException;

	public boolean delete(T object) throws ClassNotFoundException, SQLException;

	public boolean deleteAll() throws ClassNotFoundException, SQLException;
	
}
