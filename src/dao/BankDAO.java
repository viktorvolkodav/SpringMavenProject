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

@Component("bankingDAO")
public class BankDAO {

	private NamedParameterJdbcTemplate jdbc;
	private static Logger logger = LogManager.getLogger(BankDAO.class);

	@Autowired
	public void setJdbc(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	// Search bank for code in DB
	public Bank getBankForCode(String code) {

		SqlParameterSource parameterSource = new MapSqlParameterSource("code", code);
		Bank bank;
		try {

			bank = jdbc.queryForObject("SELECT * FROM banktable WHERE code = :code", parameterSource,
					new RowMapper<Bank>() {

						@Override
						public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
							return createOneBank(rs);
						}
					});
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
		return bank;
	}

	// Search bank/banks for name in DB
	public List<Bank> getBankForName(String name) {
		SqlParameterSource parameterSource = new MapSqlParameterSource("name", "%" + name.toUpperCase() + "%");
		return jdbc.query("SELECT * FROM banktable WHERE name LIKE :name", parameterSource, new RowMapper<Bank>() {

			@Override
			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				return createOneBank(rs);
			}
		});
	}

	// Return all banks from DB
	public List<Bank> getAllBanks() {

		return jdbc.query("SELECT * FROM banktable", new RowMapper<Bank>() {

			@Override
			public Bank mapRow(ResultSet rs, int rowNum) throws SQLException {
				return createOneBank(rs);
			}
		});
	}

	// Create one bank and add properties to it from ResultSet
	public Bank createOneBank(ResultSet rs) {
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

		SqlParameterSource[] resBatch = SqlParameterSourceUtils.createBatch(banks.toArray());

		int[] res = jdbc.batchUpdate(
				"INSERT INTO banktable (name, code, mfo, date, adress, license, licensedate, status, shortName) VALUES (:name, :code, :mfo, :date, :adress, :license, :licensedate, :status, :shortName)",
				resBatch);
		return res;
	}

	// Clean DB
	public boolean cleanDB() {
		SqlParameterSource parameterSource = null;
		return jdbc.update("TRUNCATE banktable", parameterSource) == 1;
	}

}
