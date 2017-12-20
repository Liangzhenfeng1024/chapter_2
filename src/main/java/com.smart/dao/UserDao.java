package com.smart.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;
import com.smart.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserDao {
    private JdbcTemplate jdbcTemplate;

    private final static String MATCH_COUNT_SQL = " SELECT COUNT(*) FROM T_USER "
            + " WHERE USER_NAME = ? AND PASSWORD = ? ";

    private final String UPDATE_LOGIN_INFO_SQL = " UPDATE T_USER " +
            " SET LAST_VISIT = ?, LAST_IP = ?, CREDITS = ? " +
            " WHERE USER_ID = ? ";

    @Autowired
    public  void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int getMatchCount(String userName, String password) {
        return jdbcTemplate.queryForObject(MATCH_COUNT_SQL, new Object[] {userName, password}, Integer.class);
    }

    public User findUserByUserName(final  String userName) {
        String sqlStr = " SELECT USER_ID, USER_NAME, CREDITS FROM T_USER "
                + " WHERE USER_NAME = ? ";
        final User user = new User();
        jdbcTemplate.query(sqlStr, new Object[] { userName},
                // 匿名类方式实现的回调函数
                new RowCallbackHandler() {
                    public void processRow(ResultSet rs) throws SQLException {
                        user.setUserId(rs.getInt("USER_ID"));
                        user.setUserName(userName);
                        user.setCredits(rs.getInt("CREDITS"));
                    }
        });
        return user;
    }

    public void updateLoginInfo(User user) {
        jdbcTemplate.update(UPDATE_LOGIN_INFO_SQL, new Object[] {
                user.getLastVisit(), user.getLastIp(), user.getCredits(),
                user.getUserId()
        });
    }
}
