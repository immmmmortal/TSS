package edu.sumdu.tss.elephant.helper;

import edu.sumdu.tss.elephant.helper.utils.ParameterizedStringFactory;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.Map;


public final class DBPool {

    public static final String DEFAULT_DATABASE = Keys.get("DB.NAME");
    private static final HashMap<String, Pair<Long, Sql2o>> STORAGE = new HashMap<>();
    private static final int MAX_CONNECTION = 10;
    public static final ParameterizedStringFactory DB_URL =
            new ParameterizedStringFactory(
                    new ParameterizedStringFactory("jdbc:postgresql://:url::port/:database")
                            .addParameter("url", Keys.get("DB.URL"))
                            .addParameter("port", Keys.get("DB.PORT"))
                            .toString());
    private static long counter = 0;

    private DBPool() { }

    public static Sql2o getConnection() {
        return getConnection(DEFAULT_DATABASE);
    }

    public static Sql2o getConnection(String dbname) {
        counter += 1;
        boolean isNew = false;
        Pair<Long, Sql2o> temp = STORAGE.get(dbname);
        if (temp == null) {
            if (STORAGE.size() > MAX_CONNECTION) {
                flush();
            }
            temp = new Pair<>();
            isNew = true;
        }

        temp.key = counter;
        if (isNew) {
            temp.value = new Sql2o(DB_URL.addParameter("database", dbname).toString(), Keys.get("DB.USERNAME"), Keys.get("DB.PASSWORD"));
            STORAGE.put(dbname, temp);
        }
        return temp.value;
    }

    public static String dbUtilUrl(String dbName) {
        return String.format("postgresql://%s:%s@%s:%s/%s", Keys.get("DB.USERNAME"), Keys.get("DB.PASSWORD"), Keys.get("DB.URL"), Keys.get("DB.PORT"), dbName);
    }

    private static void flush() {
        Map.Entry<String, Pair<Long, Sql2o>> tmp = null;
        for (Map.Entry<String, Pair<Long, Sql2o>> pair : STORAGE.entrySet()) {
            if (tmp == null) {
                tmp = pair;
            }
            if (tmp.getValue().key < pair.getValue().key) {
                tmp = pair;
            }
        }
        if (tmp != null) {
            STORAGE.remove(tmp.getKey());
        }
    }

}
