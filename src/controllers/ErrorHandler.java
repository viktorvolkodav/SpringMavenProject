package controllers;

import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Class responsible for handle errors.
 * 
 * @author Виктор
 *
 */
@ControllerAdvice
public class ErrorHandler {

	/**
	 * Method handle data exception.
	 * 
	 * @param ex the exception value
	 * @return run error.jsp
	 */
	@ExceptionHandler(DataAccessException.class)
	public final String handleDatabaseException(final DataAccessException ex) {
		ex.printStackTrace();
		return "error";
	}

}
