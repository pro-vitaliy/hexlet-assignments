@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
  page = page,
  content = @`
    <ul>
      @for(var post : page.getPosts())
        <li>${post.getName()}</li>
      @endfor
    </ul>
  `
)
