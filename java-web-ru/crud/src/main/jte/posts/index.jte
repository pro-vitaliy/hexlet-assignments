@import exercise.dto.posts.PostsPage
@import exercise.util.NamedRoutes
@param PostsPage page

@template.layout.page(
  content = @`
    <ul>
      @for(var post : page.getPosts())
        <li>
          <a href="${NamedRoutes.postPath(post.getId())}">${post.getName()}</a>
        </li>
      @endfor
    </ul>
  @if(page.getTerm() > 1)
    <a href="${NamedRoutes.postsPath()}?page=${page.getTerm() - 1}">Previous page</a>
  @endif
    <a href="${NamedRoutes.postsPath()}?page=${page.getTerm() + 1}">Next page</a>
  `
)
