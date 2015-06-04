package cz.czechitas.todo.database.dao;

import java.util.List;


public interface DAO<T>
{
	Long create(T t);
	T read(Long id);
	T readFirst();
	List<T> readAll();
	List<T> readAll(int limit, int offset);
	Long update(T t);
	void delete(T t);
	void deleteAll();
}
