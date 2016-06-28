package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;

@Component("bankingDao")
public class BankDao {

	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = LogManager.getLogger();

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	// Search bank for code in DB
	public Bank getBankForCode(String code) {

		logger.info("run");

		SqlParameterSource parameterSource = new MapSqlParameterSource("code",
				code);
		Bank bank;
		try {

			bank = jdbc.queryForObject(
					"SELECT * FROM banktable WHERE code = :code",
					parameterSource, new RowMapper<Bank>() {

						@Override
						public Bank mapRow(ResultSet rs, int rowNum)
								throws SQLException {
							return readOneBank(rs);
						}
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return bank;
	}

	// Search bank/banks for name in DB
	public List<Bank> getBankForName(String name) {

		logger.info("run");

		SqlParameterSource parameterSource = new MapSqlParameterSource("name",
				"%" + name.toUpperCase() + "%");
		return jdbc.query("SELECT * FROM banktable WHERE name LIKE :name",
				parameterSource, new RowMapper<Bank>() {

					@Override
					public Bank mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						return readOneBank(rs);
					}
				});
	}

	// Return all banks from DB
	public List<Bank> getAllBanks() {

		logger.info("run");

		return jdbc.query("SELECT * FROM banktable", new RowMapper<Bank>() {

			@Override
			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				return readOneBank(rs);
			}
		});
	}

	// Create one bank and add properties to it from ResultSet
	public Bank readOneBank(ResultSet rs) {

		logger.info("run");

		Bank bank = Bank.createBank();
		try {
			bank.setName(rs.getString("name").toUpperCase());
			bank.setCode(rs.getString("code"));
			bank.setMfo(rs.getString("mfo"));
			bank.setDate(rs.getDate("date"));
			bank.setAdress(rs.getString("adress"));
			bank.setLicense(rs.getString("license"));
			bank.setLicensedate(rs.getDate("licensedate"));
			bank.setShortName(rs.getString("shortName").toUpperCase());
			bank.setStatus(BankStatus.NORMAL);

		} catch (SQLException e) {
			logger.catching(e);
		}
		return bank;
	}

	// Add in DB new Banks
	public int[] updateDB(List<Bank> banks) {

		logger.info("run");

		SqlParameterSource[] resBatch = SqlParameterSourceUtils
				.createBatch(banks.toArray());

		int[] res = jdbc.batchUpdate(
				"INSERT INTO banktable (name, code, mfo, date, adress, license, licensedate, status, shortName) VALUES (:name, :code, :mfo, :date, :adress, :license, :licensedate, :status, :shortName)",
				resBatch);
		return res;
	}

	// Clean DB
	public boolean cleanDB() {

		logger.info("run");

		SqlParameterSource parameterSource = null;
		return jdbc.update("TRUNCATE banktable", parameterSource) == 1;
	}

}
