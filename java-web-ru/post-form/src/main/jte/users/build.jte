@template.layout.page(
  content = @`
  <form action="/users">
    <div>
      <label>
        FirstName
        <input type="text" name="firstName">
      </label>
    </div>
    <div>
      <label>
        LastName
        <input type="text" name="lastName">
      </label>
    </div>
    <div>
      <label>
        Email
        <input type="email" required name="email">
      </label>
    </div>
    <div>
      <label>
        Password
        <input type="password" required name="password">
      </label>
    </div>
    <input type="submit" value="Register">
  </form>
  ` 
)