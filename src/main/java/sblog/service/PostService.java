package sblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import sblog.orm.Author;
import sblog.orm.Post;
import sblog.repository.AuthorRepository;
import sblog.repository.PostRepository;

@Service
public class PostService {
	@Autowired
	PostRepository postRepository;
	@Autowired
	AuthorRepository authorRepository;

	public void createPost(Post post) {
		postRepository.saveAndFlush(post);
		/*
		Author author = post.getAuthor(); 
		author.addPost(post);
		authorRepository.saveAndFlush(author);
		*/
	}
	
	public List<Post> findAll(){
		return postRepository.findAll(
				new Sort(Direction.DESC, "createdAt"));
	}

	public Post getPost(Integer id) {
		return postRepository.findOne(id);
	}

	public String[] queryByTitle(String title) {
		throw new RuntimeException();
	}
	
	public Post findPostByTitle(String title){
		return postRepository.findPostByTitle(title);
	}

	public void deletePost(Integer id) {
		Post post = postRepository.findOne(id);
		Author author = post.getAuthor();
		author.removePost(post);
		authorRepository.saveAndFlush(author);
		
		postRepository.delete(id);
		postRepository.flush();		
	}

	public void updatePost(Post post) {
		throw new RuntimeException();
	}

}
