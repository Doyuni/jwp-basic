package next.dao;

import next.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao {


    public void insert(User user) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

   public void update(User user) throws SQLException {
       JdbcTemplate jdbcTemplate = new JdbcTemplate();
       String sql ="UPDATE USERS SET password = ?, name = ?, email = ? WHERE userId = ?";
       jdbcTemplate.update(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
   }

    public List<User> findAll() throws SQLException {
       JdbcTemplate jdbcTemplate = new JdbcTemplate();
       RowMapper<User> rowMapper = new RowMapper<User>() {
           @Override
           public User mapRow(ResultSet rs) throws SQLException {
               return new User(
                       rs.getString("userId"),
                       rs.getString("password"),
                       rs.getString("name"),
                       rs.getString("email")
               );
           }
       };
       String sql = "SELECT userId, password, name, email FROM USERS";
       return jdbcTemplate.query(sql, rowMapper);
    }

    public User findByUserId(String userId) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        RowMapper<User> rowMapper = new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet rs) throws SQLException {
                return new User(
                        rs.getString("userId"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email")
                );
            }
        };
        String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
        return jdbcTemplate.queryForObject(sql, rowMapper, userId);
    }
}
