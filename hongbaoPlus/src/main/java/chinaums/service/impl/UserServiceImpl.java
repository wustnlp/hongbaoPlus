package chinaums.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinaums.dao.impl.UserDaoImpl;
import chinaums.model.TUser;

@Service("userService")
public class UserServiceImpl {
private static Logger log=Logger.getLogger(UserServiceImpl.class);
	private UserDaoImpl userDao;

	public UserDaoImpl getUserDao() {
		return userDao;
	}

	@Autowired
	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}


	public Serializable save(TUser t) {
		// TODO Auto-generated method stub
		return userDao.save(t);
	}
	
	
	public TUser getByHql(String username,String password) {
		// TODO Auto-generated method stub
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("username",username);
		params.put("password", password);
		TUser tuser=userDao.getByHql("from TUser where username=:username and password=:password", params);
		if(tuser!=null){
			return tuser;
		}
		return null;
	}

}
