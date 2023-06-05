package edu.sumdu.tss.elephant.controller;

import edu.sumdu.tss.elephant.helper.UserRole;
import edu.sumdu.tss.elephant.helper.ViewHelper;
import edu.sumdu.tss.elephant.helper.utils.ParameterizedStringFactory;
import edu.sumdu.tss.elephant.model.Database;
import edu.sumdu.tss.elephant.model.TableService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * show DB stats
 **/
public class TableController extends AbstractController {

    public static final String BASIC_PAGE = "/database/{database}/table/";
    private static final ParameterizedStringFactory DEFAULT_CRUMB = new ParameterizedStringFactory("<a href='/database/:database/table'>Tables</a>");
    private static final int DEFAULT_LIMIT = 10;
    private static final int DEFAULT_OFFSET = 0;
    private static final int MAX_LIMIT = 10000;
    private static final int MAX_OFFSET = 10000;
    private static final int MIN_OFFSET = 0;
    private static final int MIN_LIMIT = 0;

    public TableController(Javalin app) {
        super(app);
    }

    public static void index(Context context) {
        Database database = currentDB(context);
        var tables = TableService.list(database.getName());
        var model = currentModel(context);
        model.put("tables", tables);
        ViewHelper.breadcrumb(context).add("Tables");
        context.render("/velocity/table/index.vm", model);
    }

    public static void previewTable(Context context) {
        Database database = currentDB(context);
        String tableName = context.pathParam("table");
        int limit = context.queryParamAsClass("limit", Integer.class).check(it -> it > MIN_LIMIT && it < MAX_LIMIT, "Limit must be a positive").getOrDefault(DEFAULT_LIMIT);
        int offset = context.queryParamAsClass("offset", Integer.class).check(it -> it > MIN_OFFSET && it < MAX_OFFSET, "Offset must be a positive").getOrDefault(DEFAULT_OFFSET);
        var table = TableService.byName(database.getName(), tableName, limit, offset * limit);
        int size = TableService.getTableSize(database.getName(), tableName);

        var model = currentModel(context);
        model.put("table", table);
        model.put("pager", ViewHelper.pager((size / limit) + 1, offset));
        ViewHelper.breadcrumb(context).add(DEFAULT_CRUMB.addParameter("database", database.getName()).toString());
        ViewHelper.breadcrumb(context).add(tableName);
        context.render("/velocity/table/show.vm", model);
    }

    /* https://www.postgresql.org/docs/current/infoschema-tables.html */

    @Override
    public void register(Javalin app) {
        app.get(BASIC_PAGE, TableController::index, UserRole.AUTHED);
        app.get(BASIC_PAGE + "{table}", TableController::previewTable, UserRole.AUTHED);
    }

}
