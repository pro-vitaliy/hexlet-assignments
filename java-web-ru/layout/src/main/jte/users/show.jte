@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
  content = @`
    <div class="d-flex justify-content-center align-items-center">
      <table class="table">
        <thead>
        <tr>
          <th scope="col">Id</th>
          <th scope="col">First name</th>
          <th scope="col">Last name</th>
          <th scope="col">Email</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <th scope="row">${page.getUser().getId()}</th>
          <td>${page.getUser().getFirstName()}</td>
          <td>${page.getUser().getLastName()}</td>
          <td>${page.getUser().getEmail()}</td>
        </tr>
        </tbody>
      </table>
    </div>
  `
)
