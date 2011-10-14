package com.oz.springmvc.test.dao;

import java.util.List;

public interface BaseDao<T> {

	public T save(T t);

	public void update(T t);

	public void delete(T t);

	public void delete(Long id);

	public T load(Long id);

	public List<T> loadAll();

}
