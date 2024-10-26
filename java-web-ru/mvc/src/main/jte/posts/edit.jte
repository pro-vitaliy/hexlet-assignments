@import exercise.dto.posts.EditPostPage
@param EditPostPage page

@template.layout.page(
  content = @`
    @if(page.getErrors() != null)
      <div class="mb-3">
        <ul>
          @for(var validator : page.getErrors().values())
            @for(var error : validator)
              <li>${error.getMessage()}</li>
            @endfor
          @endfor
        </ul>
      </div>
    @endif
    <div class="mx-auto p-4 py-md-5">
      <form action="/posts/${page.getId()}" method="post">
        <div class="mb-3">
          <label class="form-label">
            Post name
            <input type="text" class="form-control" required name="name" value="${page.getName()}">
          </label>
        </div>
        <div class="mb-3">
          <label class="form-label">
            Post body
            <textarea class="form-control" name="body" cols="70" rows="20">${page.getBody()}</textarea>
          </label>
        </div>
        <input type="submit" class="btn btn-primary" value="Update post">
      </form>
    </div>
  `
)
