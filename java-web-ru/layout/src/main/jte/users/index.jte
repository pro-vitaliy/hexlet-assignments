@import exercise.dto.users.UsersPage
@param UsersPage page

@template.layout.page(
content = @`
  <div class="d-flex justify-content-center align-items-center">
    <ul class="list-group">
      @for(var user : page.getUsers())
        <li class="list-group-item">
          <a href="users/${user.getId()}">${user.getFirstName() + " " + user.getLastName()}</a>
        </li>
      @endfor
    </ul>
  </div>
`
)