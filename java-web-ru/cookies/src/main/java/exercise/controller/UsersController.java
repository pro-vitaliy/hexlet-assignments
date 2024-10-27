package exercise.controller;

import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;
import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void register(Context ctx) {
        var firstName = StringUtils.capitalize(ctx.formParamAsClass("firstName", String.class).get().trim());
        var lastName = StringUtils.capitalize(ctx.formParamAsClass("lastName", String.class).get().trim());
        var email = ctx.formParamAsClass("email", String.class).get().trim().toLowerCase();
        var password = ctx.formParamAsClass("password", String.class).get();
        var token = Security.generateToken();
        var user = new User(firstName, lastName, email, password, token);
        UserRepository.save(user);
        ctx.cookie("token", token);
        ctx.redirect(NamedRoutes.userPath(user.getId()));
    }

    public static void show(Context ctx) {
        var token = ctx.cookie("token");
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("User not found"));
        if (!user.getToken().equals(token)) {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
        var page = new UserPage(user);
        ctx.render("users/show.jte", model("page", page));
    }
    // END
}
