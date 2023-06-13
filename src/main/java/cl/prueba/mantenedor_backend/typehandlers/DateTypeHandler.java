package cl.prueba.mantenedor_backend.typehandlers;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.Timestamp;
import java.util.Date;

public class DateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(java.sql.PreparedStatement ps, int i, Date parameter, JdbcType jdbcType) throws java.sql.SQLException {
        ps.setTimestamp(i, new Timestamp(parameter.getTime()));
    }

    @Override
    public Date getNullableResult(java.sql.ResultSet rs, String columnName) throws java.sql.SQLException {
        Timestamp timestamp = rs.getTimestamp(columnName);
        if (timestamp != null) {
            return new Date(timestamp.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(java.sql.ResultSet rs, int columnIndex) throws java.sql.SQLException {
        Timestamp timestamp = rs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return new Date(timestamp.getTime());
        }
        return null;
    }

    @Override
    public Date getNullableResult(java.sql.CallableStatement cs, int columnIndex) throws java.sql.SQLException {
        Timestamp timestamp = cs.getTimestamp(columnIndex);
        if (timestamp != null) {
            return new Date(timestamp.getTime());
        }
        return null;
    }
}
