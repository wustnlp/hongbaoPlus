package chinaums.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface BaseDaoI<T> {
	public Serializable save(T o);
	public T getByHql(String hql);
	public T getByHql(String hql, Map<String, Object> params);
	public int save(List<T> list);
	public int countByHql(String hql);
	public int countByHql(String hql, Map<String, Object> params);
	public int update(String hql,Map<String, Object> params);
	public int updates(String hql,String[] id,Map<String, Object> params);
	public List<T> find(String hql, int page, int rows);
	public List<T> find(String hql,Map<String, Object> params, int page, int rows);
	public List<T> find(String hql,Map<String,Object> params);
	public void update(T t);
	public Long count(String hql);
	public List<T> find(String hql);
}
