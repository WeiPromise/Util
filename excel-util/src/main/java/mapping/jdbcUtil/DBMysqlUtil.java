package mapping.jdbcUtil;

import mapping.model.OriginAttr;
import mapping.model.OriginTable;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by leiwei on 2020/8/6 11:30
 */
public class DBMysqlUtil {

    private static final Logger LOGGER = Logger.getLogger(DBMysqlUtil.class);
    private Connection conn = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private final static String dbDriver = "com.mysql.jdbc.Driver";
    private String dbConnectionURL = null;
    private String dbUsername = null;
    private String dbPassword = null;

    public DBMysqlUtil(String dbConnectionURL, String dbUsername,String dbPassword){
        this.dbConnectionURL = dbConnectionURL;
        this.dbUsername = dbUsername;
        this.dbPassword = dbPassword;
    }
    /**
     * 功能：获取数据库连接
     */
    public Connection getConnection() {
        try {
            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbConnectionURL, dbUsername,
                    dbPassword);
        } catch (Exception e) {
            LOGGER.error("Error: DbUtil.getConnection() 获得数据库链接失败.\r\n链接类型:"
                    + dbDriver + "\r\n链接URL:" + dbConnectionURL + "\r\n链接用户:"
                    + dbUsername + "\r\n链接密码:" + dbPassword, e);
        }
        return conn;
    }

    /**
     * 功能：执行查询语句
     */
    public ResultSet select(String sql) {
        LOGGER.info("Exec select sql:" + sql);
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.error("查询数据异常:"+ e.getMessage());
        } finally {
            close();
        }
        return rs;

    }

    /**
     * 功能：执行查询语句
     */
    public List<OriginAttr> selectOriginAttr(String sql) {
        LOGGER.info("Exec select sql:" + sql);
        List<OriginAttr> originAttrs=new ArrayList<>();
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);
            while (rs.next()){
                OriginAttr originAttr=new OriginAttr();
                List<String> samples=new ArrayList<>();
                originAttr.setOriginTableId(rs.getInt("origin_table_id"));
                originAttr.setOriginAttrId(rs.getInt("origin_attr_id"));
                originAttr.setAttrNameEn(rs.getString("attr_name_en"));
                originAttr.setAttrNameCn(rs.getString("attr_name_cn"));
                originAttr.setColumnType(rs.getString("column_type"));
                samples.add(rs.getString("sample_1"));
                samples.add(rs.getString("sample_2"));
                samples.add(rs.getString("sample_3"));
                samples.add(rs.getString("sample_4"));
                samples.add(rs.getString("sample_5"));
                originAttr.setSamples(samples);
                originAttrs.add(originAttr);
            }

        } catch (SQLException e) {
            LOGGER.error("查询数据异常:"+ e.getMessage());
        } finally {
            close();
        }
        return originAttrs;
    }

    /**
     * 功能：执行查询原始表
     */
    public List<OriginTable> selectOriginTable(String sql) {
        List<OriginTable> originTables=new ArrayList<>();
        LOGGER.info("Exec select sql:" + sql);
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);


            while (rs.next()){
                originTables.add(new OriginTable(rs.getInt("origin_table_id"),rs.getInt("data_source_info_id"),rs.getString("tb_name_en"),rs.getString("tb_name_cn")));
            }
        } catch (SQLException e) {
            LOGGER.error("查询数据异常:"+ e.getMessage());
        } finally {
            close();
        }
        return originTables;

    }



    /**
     * 功能:关闭数据库的连接
     */
    public void close() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            LOGGER.error("执行DbUtil.close()方法发生异常，异常信息：", e);
        }
    }

    public void insert(String sql) {
        LOGGER.info("Exec insert sql:" + sql);
        try {
            conn = getConnection();
            ps = conn.prepareStatement(sql);
            ps.executeQuery(sql);
        } catch (SQLException e) {
            LOGGER.error("查询数据异常:"+ e.getMessage());
        } finally {
            close();
        }
    }
}
