package sblog.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sblog.orm.Post;

public class PostTitleUniquenessValidator implements Validator {

	@Autowired
	PostRepository postRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return Post.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		// ValidationUtils.rejectIfEmpty(errors, "x", "x.empty");
		// ValidationUtils.rejectIfEmpty(errors, "y", "y.empty");
		Post post = (Post) target;
		if (postRepository.findPostByTitle(post.getTitle()) != null) {
		}
	}
}
