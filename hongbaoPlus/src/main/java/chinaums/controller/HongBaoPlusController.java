package chinaums.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import chinaums.dao.impl.HongBaoPlusDaoImpl;
import chinaums.model.THongBaoPlus;
import chinaums.service.BaseServiceI;
import chinaums.service.impl.HongBaoPlusServiceImpl;
import chinaums.util.Json;

@Controller
public class HongBaoPlusController extends BaseController<THongBaoPlus>{
	private Logger logger = Logger.getLogger(HongBaoPlusController.class);
	
	//@Autowired
	private HongBaoPlusServiceImpl hongbaoService;// 业务逻辑
	
	@RequestMapping(value = "/getAmount")
	public void getAmount(THongBaoPlus hb) {
		Integer amount = hongbaoService.getHongbaoAmount(hb);
		Json json=new Json();
		if(amount == null || amount < 0) {
			json.setSuccess(false);
			json.setMsg("没有该用户");
			json.setResult(amount);
		}else {
			json.setSuccess(true);
			json.setMsg("查询用户红宝数成功");
			json.setResult(amount);
		}
		writeJson(json);
	}
	
	@RequestMapping(value = "/addAmount")
	public void addAmount(THongBaoPlus hb) {
		Integer status = hongbaoService.addHongbao(hb);
		Json json=new Json();
		if(status == null || status < 0) {
			json.setSuccess(false);
			json.setMsg("添加用户红包数据失败");
			json.setResult(null);
		}else {
			json.setSuccess(true);
			json.setMsg("添加红包数据成功");
			json.setResult(hongbaoService.getHongbao(hb));
		}
		writeJson(json);
	}
	
	@RequestMapping(value = "/useHongBao")
	public void useHongBao(THongBaoPlus hb) {
		Integer amount = hb.getAmount();
		Json json=new Json();
		Integer type = hongbaoService.useHongbao(hb);		
		if(type == null) {
			json.setSuccess(false);
			json.setMsg("没有该用户");
		}else if(type == -1){
			json.setSuccess(false);
			json.setMsg("该用户没有红包");
		}
		else if(type == -2){			
			json.setSuccess(false);
			json.setMsg("使用红包数应为 正整数");
		}else if(type == -3) {
			json.setSuccess(false);
			json.setMsg("该用户红包数不足");
		}else{
			json.setSuccess(true);
			json.setMsg("成功使用红包");
			json.setResult(this.useHongBao(amount));
		}
		writeJson(json);
	}
	
	private Map<String, Object> useHongBao(Integer amount) {
		Map<String, Object> m = new HashMap<String, Object>();
		Random r = new Random();
		DecimalFormat df = new DecimalFormat("#0.00");  
		if(amount == null) {
			return null;
		}
		if(amount >= 20){
			double d = r.nextDouble();
			m.put("value", df.format(d*20));
			m.put("type", "现金(元)");
			amount -= 20;
			return m;
		}
		if(amount >= 8) {
			double d = r.nextDouble();
			m.put("value", df.format(d*500));
			m.put("type", "流量(M)");
			amount -= 8;
			return m;
		}
		if(amount > 0) {
			double d = r.nextDouble();
			m.put("value", df.format(d*1000));
			m.put("type", "积分");			
		}
		return m;
	}
	
	public HongBaoPlusServiceImpl getHongbaoService() {
		return this.getHongbaoService();
	}
 
	@Autowired
	public void setHongbaoService(HongBaoPlusServiceImpl hongbaoService){
		this.hongbaoService = hongbaoService;
	}
}
