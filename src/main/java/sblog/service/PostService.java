package sblog.service;

import sblog.orm.Author;
import sblog.orm.Post;
import sblog.repository.PostRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;

	public void createPost() {
		throw new NotImplementedException();
	}

	public Post getPost(Integer id) {
		return postRepository.findOne(id);
	}

	public List<Post> queryForAll() {
		Author author = new Author();
		author.setEmail("tizio@");
		Post p0 = new Post(), p1 = new Post();
		p0.setId(0);
		p0.setTitle("t0");
		p0.setBody("qwerty");
		p0.setAuthor(author);
		
		p1.setId(1);
		p1.setTitle("t1");
		p1.setBody("qwerty");
		p1.setAuthor(author);
		return Arrays.asList(p0, p1);
		//return postRepository.findAll();
	}

	public List<Post> queryByTitle(String title) {
		throw new NotImplementedException();
	}

	public void deletePost(Integer id) {
		throw new NotImplementedException();
	}

	public void updatePost(Post post) {
		throw new NotImplementedException();
	}

}
