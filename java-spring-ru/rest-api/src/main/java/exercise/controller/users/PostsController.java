package exercise.controller.users;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {
    private static final List<Post> ALL_POSTS = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.OK)
    public List<Post> showUsersPosts(@PathVariable String id) {
        return ALL_POSTS.stream()
                .filter(p -> p.getUserId() == Integer.parseInt(id))
                .toList();
    }

    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post createUserPost(@PathVariable String id, @RequestBody Post newPost) {
        newPost.setUserId(Integer.parseInt(id));
        ALL_POSTS.add(newPost);
        return newPost;
    }
}
// END
