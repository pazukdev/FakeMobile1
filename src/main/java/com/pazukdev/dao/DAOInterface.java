package com.pazukdev.dao;

import com.pazukdev.entities.Bearing;

import java.util.List;

public interface DAOInterface<T> {

	public void create(Bearing obj);

	public T read(T obj);

	public void update(T obj);

	public void delete(T obj);

	public List<T> getList();
}
