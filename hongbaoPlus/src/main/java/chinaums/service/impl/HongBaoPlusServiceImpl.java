package chinaums.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import chinaums.dao.impl.BaseDaoImpl;
import chinaums.dao.impl.HongBaoPlusDaoImpl;
import chinaums.dao.impl.UserDaoImpl;
import chinaums.model.THongBaoPlus;
import chinaums.model.TUser;
import chinaums.service.BaseServiceI;

@Service("hongbaoPlusService")
public class HongBaoPlusServiceImpl implements BaseServiceI<THongBaoPlus> {
	private Logger log=Logger.getLogger(HongBaoPlusServiceImpl.class);
	private HongBaoPlusDaoImpl hongBaoDao;
	
	public HongBaoPlusDaoImpl getHongBaoDao() {
		return hongBaoDao;
	}
	@Autowired
	public void setHongBaoDao(HongBaoPlusDaoImpl hongBaoDao) {
		this.hongBaoDao = hongBaoDao;
	}


	@Override
	public Serializable save(THongBaoPlus hongbao) {
		// TODO Auto-generated method stub
		return hongBaoDao.save(hongbao);
	}


	@Override
	public int delete(String id){
		return -1;
	}
	

	@Override
	public void update(THongBaoPlus t){
		
	}

	/*获取用户红包	 
	*/
	public THongBaoPlus getHongbao(THongBaoPlus hb) {
		String userid = hb.getUserid();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userid", userid);
		String hql = "from THongBaoPlus t where t.userid=:userid";
		THongBaoPlus hbp = hongBaoDao.getByHql(hql, params);
		return hbp;
	}
	/*获取用户红包数	 
	*/
	public Integer getHongbaoAmount(THongBaoPlus hb) {
		String userid = hb.getUserid();
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userid", userid);
		String hql = "from THongBaoPlus t where t.userid=:userid";
		THongBaoPlus hbp = hongBaoDao.getByHql(hql, params);
		if(hbp!=null){
			return hbp.getAmount();
		}
		return null;
	}
	/*消耗红包 
	*/
	public Integer useHongbao(THongBaoPlus hb) {
		Integer originAmount = this.getHongbaoAmount(hb);
		String userid = hb.getUserid();
		Integer toUse = hb.getAmount();
		if(originAmount == null){
			return null;
		}
		if(originAmount <= 0) {
			return -1;
		}else if(toUse <= 0) {
			return -2;
		}else if(toUse > originAmount) {
			return -3;
		}
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userid", userid);
		Integer amount = originAmount - toUse;
		hb.setAmount(amount);
		hongBaoDao.update(hb);
		return 0;
	}

	/*添加红包 
	*/
	public int addHongbao(THongBaoPlus hb) {
		Integer originAmount = this.getHongbaoAmount(hb);
		String userid = hb.getUserid();
		Integer toAdd = hb.getAmount();
		if(originAmount == null){
			if(toAdd == null || toAdd <= 0) {
				return -1;				
			}else {
				hb.setAmount(toAdd);
				hongBaoDao.save(hb);
				return 1;
			}
		}
		if(originAmount < 0) {
			return -1;
		}else if(toAdd <= 0) {
			return -2;
		}
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userid", userid);
		Integer amount = originAmount + toAdd;
		hb.setAmount(amount);
		hongBaoDao.update(hb);
		return 0;
	}

}
