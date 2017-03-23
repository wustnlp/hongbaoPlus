package chinaums.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;


public interface BaseServiceI<T> {

	
	public Serializable save(T o);

	public int delete(String id);

	public void update(T t);
	
	

}
