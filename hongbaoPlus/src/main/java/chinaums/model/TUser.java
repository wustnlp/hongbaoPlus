package chinaums.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

/**
 * TUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_user", catalog = "taizhang", uniqueConstraints = @UniqueConstraint(columnNames = "id"))
public class TUser implements java.io.Serializable {

	// Fields

	private String id;
	private String username;
	private String password;
	private String realname;
	private String role;
	private Integer flag;

	// Constructors

	/** default constructor */
	public TUser() {
	}

	/** minimal constructor */
	public TUser(String id, String username, String password, String realname) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
	}

	/** full constructor */
	public TUser(String id, String username, String password, String realname,
			String role, Integer flag) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.realname = realname;
		this.role = role;
		this.flag = flag;
	}

	// Property accessors
	@Id
	@Column(name = "id", unique = true, nullable = false)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "username", nullable = false, length = 15)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 15)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "realname", nullable = false, length = 5)
	public String getRealname() {
		return this.realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	@Column(name = "role", length = 30)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "flag")
	public Integer getFlag() {
		return this.flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

}