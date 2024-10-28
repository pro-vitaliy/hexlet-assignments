package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;
import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.repository.UsersRepository;
import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import exercise.util.Security;
import io.javalin.http.Context;

public class SessionsController {

    // BEGIN
    public static void index(Context ctx) {
        var name = ctx.sessionAttribute("currentUser");
        var page = new MainPage(name);
        ctx.render("index.jte", model("page", page));
    }

    public static void build(Context ctx) {
        ctx.render("build.jte", model("page", new LoginPage()));
    }

    public static void login(Context ctx) {
        var nickname = ctx.formParam("name").trim();
        var page = new LoginPage(nickname, "Wrong username or password");
        if (!UsersRepository.existsByName(nickname)) {
            ctx.render("build.jte", model("page", page));
        } else {
            var password = Security.encrypt(ctx.formParam("password"));
            var user = UsersRepository.findByName(nickname).get();
            if (!user.getPassword().equals(password)) {
                ctx.render("build.jte", model("page", page));
            } else {
                ctx.sessionAttribute("currentUser", nickname);
                ctx.redirect(NamedRoutes.rootPath());
            }
        }
    }

    public static void logout(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }
    // END
}
