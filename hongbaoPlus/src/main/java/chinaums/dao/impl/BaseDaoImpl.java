package chinaums.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chinaums.dao.BaseDaoI;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDaoI<T> {
	private Logger log = Logger.getLogger(BaseDaoImpl.class);
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(T o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(o);
		sessionFactory.getCurrentSession().flush();
		return null;
	}
	
	@Override
	public T getByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}            
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public int save(List<T> list) {
		// TODO Auto-generated method stub
		int len = list.size();
		int i = 0;

		for (; i < len; i++) {
			sessionFactory.getCurrentSession().save(list.get(i));
			if (i % 20 == 19) {
				sessionFactory.getCurrentSession().flush();
				sessionFactory.getCurrentSession().clear();
			}
		}

		return i;

	}

	@Override
	public int countByHql(String hql) {
		// TODO Auto-generated method stub
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		return (Integer) query.uniqueResult();
	}

	@Override
	public int countByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public T getByHql(String hql) {
		// TODO Auto-generated method stub
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<T> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}

	@Override
	public int update(String hql,Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		int deteleEntity=q.executeUpdate();
		return deteleEntity;
	}

	@Override
	public int updates(String hql, String[] id,Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		q.setParameterList("id", id);
            
		return q.executeUpdate();
	}

	@Override
	public List<T> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params, int page,int rows) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<T> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.list();
	}

	@Override
	public Long count(String hql) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		return (Long) q.uniqueResult();
	}

	@Override
	public List<T> find(String hql) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}



}
