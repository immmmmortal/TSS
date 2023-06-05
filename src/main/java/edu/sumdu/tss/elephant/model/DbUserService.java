package edu.sumdu.tss.elephant.model;

import edu.sumdu.tss.elephant.helper.DBPool;
import edu.sumdu.tss.elephant.helper.utils.ParameterizedStringFactory;
import io.javalin.core.util.JavalinLogger;

import java.io.File;
import java.util.List;

public final class DbUserService {

    private static final ParameterizedStringFactory CREATE_USER_SQL = new ParameterizedStringFactory("CREATE USER :name WITH PASSWORD ':password' CONNECTION LIMIT 5 IN ROLE customer;");
    private static final ParameterizedStringFactory DELETE_USER_SQL = new ParameterizedStringFactory("DROP USER :name");

    private DbUserService() { }

    public static void initUser(String username, String password) {
        //Create user
        System.out.println("Username: " + username);
        String createUserString = CREATE_USER_SQL.addParameter("name", username).addParameter("password", password).toString();
        System.out.println(createUserString);
        DBPool.getConnection().open().createQuery(createUserString, false).executeUpdate();
        //Create tablespace
        String path = UserService.userStoragePath(username);
        System.out.println("Tablespace path:" + path);
        UserService.createTablespace(username, path + File.separator + "tablespace");
    }

    //TODO: SQL injection here!
    private static final ParameterizedStringFactory RESET_USER_SQL = new ParameterizedStringFactory("ALTER USER :name WITH PASSWORD ':password'");

    public static void dbUserPasswordReset(String name, String password) {
        String query = RESET_USER_SQL.addParameter("name", name).addParameter("password", password).toString();
        JavalinLogger.info(query);
        DBPool.getConnection().open().createQuery(query, false).executeUpdate();
    }

    public static void dropUser(String name) {
        try {
            //Drop databases
            List<Database> databases = DatabaseService.forUser(name);
            for (Database database:databases) {
                DatabaseService.drop(database);
            }
            //Drop tablespace
            UserService.dropTablespace(name);
            //Drop user
            String dropUser = DELETE_USER_SQL.addParameter("name", name).toString();
            DBPool.getConnection().open().createQuery(dropUser, false).executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
