@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
  content = @`
  <form action="/users" method="get">
    <input type="search" name="term" value="${page.getTerm()}">
    <input type="submit" value="Search" class="btn btn-primary">
  </form>
  <div>
    @for(var user : page.getUsers())
      <p>${user.toString()}</p>
    @endfor
  </div>
  ` 
)
