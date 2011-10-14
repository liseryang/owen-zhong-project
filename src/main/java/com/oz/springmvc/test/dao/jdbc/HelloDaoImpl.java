package com.oz.springmvc.test.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.oz.springmvc.framework.orm.Page;
import com.oz.springmvc.test.dao.HelloDao;
import com.oz.springmvc.test.dao.JDBCDao;
import com.oz.springmvc.test.domain.Hello;
@Repository
public class HelloDaoImpl extends JDBCDao implements HelloDao {
	private final String INSERT_SQL = "insert into tb_booking(VR_BKG_BOOKING_NO) values(:bookingNo)";
	private final String UPDATE_SQL = "update tb_booking(VR_BKG_BOOKING_NO) values(:bookingNo)";
	private final String DELETE_SQL = "delete from tb_booking where ID_BOOKING=?";
	private final String LOAD_BY_ID_SQL = "select ID_BOOKING,VR_BKG_BOOKING_NO from tb_booking where ID_BOOKING=?";
	private final String LOAD_ALL_SQL = "select ID_BOOKING,VR_BKG_BOOKING_NO from tb_booking";
	
	
	private static class HelloMapper implements RowMapper<Hello> {
		public Hello mapRow(ResultSet rs, int rowNum) throws SQLException {
			Hello hello = new Hello();
			hello.setId(rs.getLong("ID_BOOKING"));
			hello.setBookingNo(rs.getString("VR_BKG_BOOKING_NO"));
			return hello;
		}
	}
	
	public List<Hello> loadAll(){
		return getSimpleJdbcTemplate().query(LOAD_ALL_SQL, new HelloMapper());
	}

	public Hello save(Hello hello) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getNamedParameterJdbcOperations().update(INSERT_SQL, new BeanPropertySqlParameterSource(hello),keyHolder);
		return load((Long) keyHolder.getKey());
	}
	public void delete(Hello t) {
		this.delete(t.getId());
	}
	public void delete(Long id) {
		getJdbcTemplate().update(DELETE_SQL,id);
	}
	public Hello load(Long id) {
		return getSimpleJdbcTemplate().queryForObject(LOAD_BY_ID_SQL, new HelloMapper(),id);
	}
	public void update(Hello hello) {
		getSimpleJdbcTemplate().update(UPDATE_SQL, new BeanPropertySqlParameterSource(hello));
	}
	@SuppressWarnings("unchecked")
	public Page<Hello> loadPageByCondtion(Page<Hello> page,Map conditions){
		MapSqlParameterSource sqlParameter = new MapSqlParameterSource();
		sqlParameter.addValue("start", page.getStart());
		sqlParameter.addValue("limit", page.getPageSize());
	
		StringBuffer whereSb = new StringBuffer("where 1=1 ");
		if(conditions.get("bookingNo")!=null && StringUtils.isNotBlank((String) conditions.get("bookingNo"))){
			whereSb.append("and  VR_BKG_BOOKING_NO like :bookingNo ");
			sqlParameter.addValue("bookingNo", "%"+conditions.get("bookingNo")+"%");
		}
		StringBuffer sql = new StringBuffer("select ID_BOOKING,VR_BKG_BOOKING_NO from tb_booking ");
		sql.append(whereSb.toString()).append(" limit :start, :limit");
		List<Hello> list = getSimpleJdbcTemplate().query(sql.toString(), new HelloMapper(), sqlParameter);
		page.setResult(list);
		StringBuffer sqlCount = new StringBuffer("select count(*) from tb_booking ").append(whereSb.toString());// where VR_BKG_BOOKING_NO=:bookingNo
		long count = getSimpleJdbcTemplate().queryForLong(sqlCount.toString(),sqlParameter);
		page.setTotalCount(count);
		return page;
	}
}

