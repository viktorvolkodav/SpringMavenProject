package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dao.User;
import dao.UsersDao;

@Service("usersService")
public class UsersService {

	private UsersDao usersDao;
	private static Logger logger = LogManager.getLogger();

	@Autowired
	public void setUsersDao(UsersDao usersDao) {
		this.usersDao = usersDao;
	}

	public void createUser(User user) {
		logger.info("run");
		usersDao.createUser(user);
	}

	public boolean userExists(String username) {
		logger.info("run");
		return usersDao.exists(username);
	}

	public User getUser(String userName) {
		logger.info("run");
		return usersDao.getUser(userName);
	}

	

}
