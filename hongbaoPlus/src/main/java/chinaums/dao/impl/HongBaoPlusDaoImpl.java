package chinaums.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chinaums.dao.BaseDaoI;
import chinaums.model.THongBaoPlus;

@Repository("HongBaoPlusDao")
public class HongBaoPlusDaoImpl implements BaseDaoI<THongBaoPlus> {
	private Logger log = Logger.getLogger(HongBaoPlusDaoImpl.class);
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Serializable save(THongBaoPlus o) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(o);
		sessionFactory.getCurrentSession().flush();
		return null;
	}
	
	@Override
	public THongBaoPlus getByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}            
		List<THongBaoPlus> l = q.list();
		if (l != null && l.size() > 0) {
			return l.get(0);
		}
		return null;
	}
	
	public List<THongBaoPlus> getAllByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}            
		List<THongBaoPlus> l = q.list();
		if (l != null && l.size() > 0) {
			return l;
		}
		return null;
	}

	@SuppressWarnings("finally")
	@Override
	public int save(List<THongBaoPlus> list) {
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
	public THongBaoPlus getByHql(String hql) {
		// TODO Auto-generated method stub
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		List<THongBaoPlus> l = q.list();
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
	public List<THongBaoPlus> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		Query q = sessionFactory.getCurrentSession().createQuery(hql);
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public void update(THongBaoPlus t) {
		// TODO Auto-generated method stub
		//sessionFactory.getCurrentSession().update(t);
		//sessionFactory.getCurrentSession().flush();
		String userid = t.getUserid();
		Integer amount = t.getAmount();	
		String sql = "update t_hongbao_plus set amount=%s where userid='%s'";
		sql = String.format(sql, amount, userid);
		Query q=sessionFactory.getCurrentSession().createSQLQuery(sql);
		q.executeUpdate();
	}

	@Override
	public List<THongBaoPlus> find(String hql, Map<String, Object> params, int page,int rows) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		return q.setFirstResult((page - 1) * rows).setMaxResults(rows).list();
	}

	@Override
	public List<THongBaoPlus> find(String hql, Map<String, Object> params) {
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
	public List<THongBaoPlus> find(String hql) {
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		return q.list();
	}
}
