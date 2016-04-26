package dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {

	@NotBlank(message = "username cannot be blank")
	@Size(min = 4, max = 20, message = "username min=4, max=20")
	// @Pattern(regexp="^\\w{8,}$", message= "must consist numbers, letters and
	// carracters")
	private String username;

	@NotBlank(message = "email cannot be blank")
	@Email
	private String email;

	@NotBlank(message = "password cannot be blank")
	@Size(min = 4, max = 20, message = "password min=4, max=20")
	@Pattern(regexp = "^\\S+$")
	private String password;

	private boolean enabled = false;
	private String authority;

	public User() {
	}

	public User(String username, String password, String email, boolean enabled, String authority) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;

	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
