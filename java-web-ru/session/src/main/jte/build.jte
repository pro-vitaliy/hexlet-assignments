@import exercise.dto.LoginPage
@import exercise.util.NamedRoutes
@param LoginPage page

@template.layout.page(content = @`
  @if(page.getError() != null)
    ${page.getError()}
  @endif
  <form action="${NamedRoutes.loginPath()}" method="post">
    <label>
      <input type="text" name="name" required value="${page.getName()}">
    </label>
    <label>
      <input type="password" required name="password">
    </label>
    <input type="submit" value="Login" class="btn btn-primary">
  </form>
`)
