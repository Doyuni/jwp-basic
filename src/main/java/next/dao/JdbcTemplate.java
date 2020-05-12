package next.dao;

import core.jdbc.ConnectionManager;
import next.exception.DataAccessException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {

    public void update(String sql, Object... parameters) throws DataAccessException {
        try(Connection conn = ConnectionManager.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i+1, parameters[i]);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new DataAccessException(e);
        }
    }


    @SuppressWarnings("rawtypes")
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException {
        ResultSet rs = null;
        try(Connection con = ConnectionManager.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql)) {
            for (int i = 0; i < parameters.length; i++) {
                pstmt.setObject(i+1, parameters[i]);
            }

            rs = pstmt.executeQuery();

            List<T> result = new ArrayList<T>();
            while(rs.next()) {
                result.add(rowMapper.mapRow(rs));
            }
            return  result;
        } catch (SQLException e) {
          throw new DataAccessException(e);
        }
    }
    @SuppressWarnings("rawtypes")
    public <T> T queryForObject(String sql, RowMapper<T> rowMapper, Object... parameters) throws DataAccessException{
        List<T> result = query(sql, rowMapper, parameters);
        if (result.isEmpty()) {
            return null;
        }
        return result.get(0);
    }
}
