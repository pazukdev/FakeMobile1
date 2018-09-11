package com.pazukdev.dao;

import com.pazukdev.db.DataProvider;
import com.pazukdev.entities.Bearing;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class DAOBearing implements DAOInterface<Bearing> {

	@Override
	public void create(Bearing bearing) {
		EntityManager entityManager = DataProvider.getEntityManager();
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(bearing);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			throw new RuntimeException("DAOBearing create Exception", e);
		} finally {
			entityManager.close();
		}
	}

	@Override
	public Bearing read(Bearing bearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			Bearing b = em.find(Bearing.class, bearing.getId());
			em.getTransaction().commit();
			return b;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing read Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void update(Bearing bearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			Bearing b = em.find(Bearing.class, bearing.getId());
			b.setNumberOfOriginal(bearing.getNumberOfOriginal());
			b.setType(bearing.getType());
			b.setMajorLocation(bearing.getMajorLocation());
			b.setQuantity(bearing.getQuantity());
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing update Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public void delete(Bearing bearing) {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			Bearing b = em.find(Bearing.class, bearing.getId());
			em.remove(b);
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing delete Exception", e);
		} finally {
			em.close();
		}
	}

	@Override
	public List<Bearing> getList() {
		EntityManager em = DataProvider.getEntityManager();
		try {
			em.getTransaction().begin();
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Bearing> criteria = cb.createQuery(Bearing.class);
			Root<Bearing> root = criteria.from(Bearing.class);
			criteria.select(root);
			List<Bearing> list = em.createQuery(criteria).getResultList();
			em.getTransaction().commit();
			return list;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException("DAOBearing getList Exception", e);
		} finally {
			em.close();
		}
	}

}
