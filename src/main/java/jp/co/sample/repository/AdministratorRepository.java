package jp.co.sample.repository;








import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.domain.Administrator;



@Repository
public class AdministratorRepository {
	
	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private static final RowMapper<Administrator>ADMINISTRATOR_ROW_MAPPER =(rs,i) ->{
		Administrator administrator =new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mailAddress"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};
	
	public void  insert(Administrator administrator) {
		SqlParameterSource param=new BeanPropertySqlParameterSource(administrator);
		String insertsql="insert into administrator(id,name,mailAddress,password)"
				+ " values(:id,:name:,:mailAddress,:password)";
		template.update(insertsql, param);
	}
	public Administrator findByMailAddressAndPassword(String mailAddress,String password){
		try {
			SqlParameterSource param=new MapSqlParameterSource().addValue("mailAddress", mailAddress).addValue("password", password);
		String sql="select id,name,mailAddress,password from addministrators where mailAdress and password";
		 return template.queryForObject(sql, param, ADMINISTRATOR_ROW_MAPPER);
		}catch(Exception e) {
			return null;
		}
	}
}


