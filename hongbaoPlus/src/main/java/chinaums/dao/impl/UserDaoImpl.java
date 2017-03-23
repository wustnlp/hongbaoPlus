package chinaums.dao.impl;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import chinaums.util.SqlHelper;
import chinaums.dao.BaseDaoI;
import chinaums.model.TUser;

@Repository("userDao")
public class UserDaoImpl implements BaseDaoI<TUser>{
	private SessionFactory sessionFactory;
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Logger log=Logger.getLogger(UserDaoImpl.class);
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
    public TUser exist(String username){
    	TUser user=null;
    	try {
			conn = SqlHelper.getConnection();
			ps = conn.prepareStatement("select * from t_user where username = ?");
			
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {
				user=new TUser();
				user.setId(rs.getString("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setFlag(rs.getInt("flag"));
				log.info(user.getPassword());
			}
		} catch (Exception e) {
			log.info("异常");
			e.printStackTrace();
		} finally {
			SqlHelper.close(rs, ps, conn);
		}
    	return user;
    }

	@Override
	public Serializable save(TUser o) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(o);
		sessionFactory.getCurrentSession().flush();
		return null;
	}
	
	
	
	@Override
	public TUser getByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		Query q=sessionFactory.getCurrentSession().createQuery(hql);
		if(params!=null&&!params.isEmpty()){
			for(String key:params.keySet()){
				q.setParameter(key, params.get(key));
			}
		}
		List<TUser> l =q.list();
		if(l!=null&&l.size()>0){
			
			return l.get(0);
		}
		return null;
	}
	@Override
	public int save(List<TUser> list) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int countByHql(String hql) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int countByHql(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public TUser getByHql(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int update(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public int updates(String hql, String[] id, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public List<TUser> find(String hql, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void update(TUser t) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<TUser> find(String hql, Map<String, Object> params, int page,
			int rows) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TUser> find(String hql, Map<String, Object> params) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Long count(String hql) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<TUser> find(String hql) {
		// TODO Auto-generated method stub
		return null;
	}


}
