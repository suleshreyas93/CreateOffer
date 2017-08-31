package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class OffersDao {
	
	private NamedParameterJdbcTemplate jdbc;
	
	public void setDataSource(DataSource jdbc)
	{
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
	
	public List<Offers> getAllOffers()
	{
		return jdbc.query("select * from offers", new RowMapper<Offers>() {

			@Override
			public Offers mapRow(ResultSet rs, int rowNum) throws SQLException {
				
				Offers offer = new Offers();
				offer.setId(rs.getInt("id"));
				offer.setName(rs.getString("name"));
				offer.setEmail(rs.getString("email"));
				offer.setText(rs.getString("text"));
				
				return offer;
				
			}
			
		});
	}
	
	public boolean createOffer(Offers offer)
	{
		BeanPropertySqlParameterSource param = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers(name,email,text) value(:name,:email,:text)", param) == 1;
		
	}

}
