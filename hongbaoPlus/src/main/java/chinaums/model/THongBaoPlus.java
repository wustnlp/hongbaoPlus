package chinaums.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "t_hongbao_plus", catalog = "hongbao", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class THongBaoPlus implements Serializable {
	// Fields

	private String id;
	private String userid;
	private Integer amount;

	THongBaoPlus(){
		this.setId(UUID.randomUUID().toString());
	}
	THongBaoPlus(String userid, Integer amount){
		this.setId(UUID.randomUUID().toString());
		this.setUserid(userid);
		this.setAmount(amount);
	}
	
	THongBaoPlus(String id, String userid, Integer amount){
		this.setId(id);
		this.setUserid(userid);
		this.setAmount(amount);
	}

	@Id
	@Column(name = "id")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Column(name = "userid")
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	@Column(name = "amount")
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
}
