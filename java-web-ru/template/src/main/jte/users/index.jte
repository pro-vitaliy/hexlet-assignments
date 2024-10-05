@import exercise.dto.users.UsersPage
@param UsersPage page

<!doctype html>
<html lang="ru">
  <head>
      <meta charset="utf-8" />
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
            crossorigin="anonymous">
      <title>All users</title>
  </head>
  <body>
  @if(page.getUsers().isEmpty())
      <p>Нет ни одного пользователя</p>
  @else
      <table class="table table-hover">
          <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">First name</th>
                <th scope="col">Last name</th>
                <th scope="col">Email</th>
            </tr>
          </thead>
          <tbody>
            @for(var user : page.getUsers())
                <tr>
                    <th scope="row"><a href="/users/${user.getId()}" target="_blank">${user.getId()}</a></th>
                    <td><a href="/users/${user.getId()}" target="_blank">${user.getFirstName()}</a></td>
                    <td><a href="/users/${user.getId()}" target="_blank">${user.getLastName()}</a></td>
                    <td><a href="/users/${user.getId()}" target="_blank">${user.getEmail()}</a></td>
                </tr>
            @endfor
          </tbody>
      </table>
  @endif
  </body>
</html>