package com.smart.dao;

import com.smart.domain.LoginLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class LoginLogDao {
    private JdbcTemplate jdbcTemplate;

    //保存登录日志SQL
    private final static String  INSER_LOGIN_LOG_SQL = " INSERT INTO T_LOGIN_LOG " +
            " (USER_ID, IP, LOGIN_DATETIME) VALUES(?, ?, ?)";

    public void inserLoginLog(LoginLog loginLog) {
        Object args[] = { loginLog.getUserId(), loginLog.getIp(), loginLog.getLoginDate()};
        jdbcTemplate.update(INSER_LOGIN_LOG_SQL, args);
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
