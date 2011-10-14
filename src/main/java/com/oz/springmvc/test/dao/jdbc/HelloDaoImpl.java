package com.oz.springmvc.test.dao.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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
	
	private static class HelloMapper implements RowMapper<Hello> {
		public Hello mapRow(ResultSet rs, int rowNum) throws SQLException {
			Hello hello = new Hello();
			hello.setId(rs.getLong("ID_BOOKING"));
			hello.setBookingNo(rs.getString("VR_BKG_BOOKING_NO"));
			return hello;
		}
	}
	
	public List<Hello> loadAll(){
		return getSimpleJdbcTemplate().query("select ID_BOOKING,VR_BKG_BOOKING_NO from tb_booking", new HelloMapper());
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
	public Page<Hello> loadPageByCondtion(Page<Hello> page,Map conditions){;
		conditions.put("start", page.getStart());
		conditions.put("limit", page.getPageSize());
		
		String sql = "select ID_BOOKING,VR_BKG_BOOKING_NO from tb_booking limit :start, :limit";
		List<Hello> list = getSimpleJdbcTemplate().query(sql, new HelloMapper(), conditions);
		page.setResult(list);
		
		String sqlCount = "select count(*) from tb_booking";// where ID_BOOKING=:bookingNo
		long count = getSimpleJdbcTemplate().queryForLong(sqlCount,conditions);
		page.setTotalCount(count);
		return page;
	}
}

