package edu.sumdu.tss.elephant.controller;

import edu.sumdu.tss.elephant.helper.Keys;
import edu.sumdu.tss.elephant.helper.UserRole;
import edu.sumdu.tss.elephant.helper.ViewHelper;
import edu.sumdu.tss.elephant.helper.enums.Lang;
import edu.sumdu.tss.elephant.helper.utils.ResponseUtils;
import edu.sumdu.tss.elephant.helper.utils.StringUtils;
import edu.sumdu.tss.elephant.model.DbUserService;
import edu.sumdu.tss.elephant.model.User;
import edu.sumdu.tss.elephant.model.UserService;
import io.javalin.Javalin;
import io.javalin.core.util.JavalinLogger;
import io.javalin.http.Context;

public class ProfileController extends AbstractController {

    public static final String BASIC_PAGE = "/profile";
    private static final int API_KEY_SIZE = 20;

    public ProfileController(Javalin app) {
        super(app);
    }

    public static void show(Context context) {
        context.render("/velocity/profile/show.vm", currentModel(context));
    }


    public static void language(Context context) {
        User user = currentUser(context);
        var lang = context.queryParam("lang");
        user.setLanguage(Lang.byValue(lang).toString());
        UserService.save(user);
        context.redirect(BASIC_PAGE);
    }

    public static void resetDbPassword(Context context) {
        User user = currentUser(context);
        //TODO add password validation
        JavalinLogger.info(user.toString());
        user.setDbPassword(context.formParam("db-password"));
        JavalinLogger.info(user.toString());
        UserService.save(user);
        DbUserService.dbUserPasswordReset(user.getUsername(), user.getDbPassword());
        context.sessionAttribute(Keys.INFO_KEY, "DB user password was changed");
        context.redirect(BASIC_PAGE);
    }

    public static void resetWebPassword(Context context) {
        User user = currentUser(context);
        //TODO add password validation
        user.password(context.formParam("web-password"));
        UserService.save(user);
        context.sessionAttribute(Keys.INFO_KEY, "Web user password was changed");
        context.redirect(BASIC_PAGE);
    }

    public static void resetApiPassword(Context context) {
        User user = currentUser(context);
        //TODO add password validation
        user.setPrivateKey(StringUtils.randomAlphaString(API_KEY_SIZE));
        user.setPublicKey(StringUtils.randomAlphaString(API_KEY_SIZE));
        UserService.save(user);
        context.sessionAttribute(Keys.INFO_KEY, "API keys was reset successful");
        context.redirect(BASIC_PAGE);
    }

    public static void upgradeUser(Context context) {
        User user = currentUser(context);
        if (user.role() != UserRole.UNCHEKED) {
            user.setRole(UserRole.valueOf(context.formParam("role")).getValue());
            UserService.save(user);
            context.sessionAttribute(Keys.INFO_KEY, "Role has been changed");
        } else {
            context.sessionAttribute(Keys.ERROR_KEY, "You need to approve you email before can upgrade userRole");
        }
        context.redirect(BASIC_PAGE);
    }

    public static void removeSelf(Context context) {
        User user = currentUser(context);
        DbUserService.dropUser(user.getUsername());
        UserService.deleteUserStorage(user.getUsername());
        UserService.deleteUser(user.getLogin());
        ResponseUtils.flushFlash(context);
        context.sessionAttribute(Keys.SESSION_CURRENT_USER_KEY, null);
        context.sessionAttribute(Keys.INFO_KEY, "Account has been deleted");
        context.redirect("/login");
    }

    public static void remove(Context context) {
        String button = ViewHelper.getDeleteButton();
        context.sessionAttribute(Keys.ERROR_KEY,
                String.format("Are you sure that you want to delete your account? All your data will be deleted!\n %s", button));
        context.redirect(BASIC_PAGE);
    }

    public void register(Javalin app) {
        app.get(BASIC_PAGE + "/lang", ProfileController::language, UserRole.AUTHED);
        app.post(BASIC_PAGE + "/reset-password", ProfileController::resetWebPassword, UserRole.AUTHED);
        app.post(BASIC_PAGE + "/reset-db", ProfileController::resetDbPassword, UserRole.AUTHED);
        app.post(BASIC_PAGE + "/reset-api", ProfileController::resetApiPassword, UserRole.AUTHED);
        app.post(BASIC_PAGE + "/upgrade", ProfileController::upgradeUser, UserRole.AUTHED);
        app.post(BASIC_PAGE + "/remove-self", ProfileController::removeSelf, UserRole.AUTHED);
        app.get(BASIC_PAGE + "/remove", ProfileController::remove, UserRole.AUTHED);
        app.get(BASIC_PAGE, ProfileController::show, UserRole.AUTHED);
    }

}
