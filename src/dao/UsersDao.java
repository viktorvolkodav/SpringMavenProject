package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("usersDao")
public class UsersDao {

	private static Logger logger = LogManager.getLogger();
	private NamedParameterJdbcTemplate jdbc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	@Transactional
	public boolean createUser(User user) {

		logger.info("run");
		MapSqlParameterSource params = new MapSqlParameterSource();

		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());

		return  jdbc.update(
				"insert into users (username, password, email, enabled, authority) values (:username, :password, :email, :enabled, :authority)" ,
				params)  == 1;
	}

	public boolean exists(String username) {

		logger.info("run");
		return jdbc.queryForObject(
				"select count(*) from users where username=:username",
				new MapSqlParameterSource("username", username),
				Integer.class) > 0;
	}

	public User getUser(String username) {

		logger.info("run");
		MapSqlParameterSource params = new MapSqlParameterSource("username",
				username);

		return jdbc.queryForObject(
				"select * from users where username= :username",
				params, new RowMapper<User>() {

					@Override
					public User mapRow(ResultSet rs, int rowNum) {
						User user = new User();

						try {
							user.setUsername(rs.getString("username"));

							user.setEmail(rs.getString("email"));
							user.setAuthority(rs.getString("authority"));
							if (rs.getString("enabled").equals("1")) {
								user.setEnabled(true);
							} else
								user.setEnabled(false);
						} catch (SQLException e) {
							logger.catching(e);
						}
						return user;
					}
				});
	}

	public List<User> getAllUsers() {
		return jdbc.query(
				"select * from users",
				BeanPropertyRowMapper.newInstance(User.class));
	}

}
