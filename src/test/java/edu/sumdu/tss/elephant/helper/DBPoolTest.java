package edu.sumdu.tss.elephant.helper;

import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;

import java.io.File;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBPoolTest {
    @Before
    public void beforeMethod() {
        Keys.loadParams(new File("src\\pgdb.conf"));
    }

    @Test
    public void testGetConnection() throws SQLException {
        Sql2o con = DBPool.getConnection();

        assertNotNull(con);
        assertEquals(DBPool.DB_URL.addParameter("database", DBPool.DEFAULT_DATABASE).toString(),
                con.getConnectionSource().getConnection().getMetaData().getURL());
    }

    @Test
    public void testGetConnectionWithDbName() throws SQLException {
        String db_name = "elephant_db";
        Sql2o con = DBPool.getConnection(db_name);

        assertNotNull(con);
        assertEquals(DBPool.DB_URL.addParameter("database", db_name).toString(),
                con.getConnectionSource().getConnection().getMetaData().getURL());
    }

    @Test
    public void testDbUtilUrl() {
        String db_name = "elephant_db";
        String expectedUrl = String.format("postgresql://%s:%s@%s:%s/%s",
                Keys.get("DB.USERNAME"), Keys.get("DB.PASSWORD"), Keys.get("DB.URL"),
                Keys.get("DB.PORT"), db_name);

        assertEquals(expectedUrl, DBPool.dbUtilUrl(db_name));
    }
}