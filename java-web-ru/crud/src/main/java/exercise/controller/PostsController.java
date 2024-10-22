package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class PostsController {

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();

        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Page not found"));
        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        var pageNum = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        var pageSize = ctx.queryParamAsClass("size", Integer.class).getOrDefault(5);
        var posts = PostRepository.findAll(pageNum, pageSize);
        var page = new PostsPage(posts, pageNum);
        ctx.render("posts/index.jte", model("page", page));
    }
    // END
}
