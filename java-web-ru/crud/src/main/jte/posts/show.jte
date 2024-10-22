@import exercise.dto.posts.PostPage
@import exercise.util.NamedRoutes
@param PostPage page

@template.layout.page(
  content = @`
    <h3>Post: ${page.getPost().getName()}</h3>
    <p>ID: ${page.getPost().getId()}</p>
    <p>${page.getPost().getBody()}</p>
    <a href="${NamedRoutes.postsPath()}">To posts list</a>
  `
)