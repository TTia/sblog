package sblog.service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

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

	public Post createPost(Post post) {
		return postRepository.saveAndFlush(post);
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
		List<Post> posts = postRepository.findPostByTitleContainingIgnoreCase(title);
		String[] postsTitles = new String[posts.size()];
		for(int i = 0; i<posts.size(); i++){
			postsTitles[i] = posts.get(i).getTitle(); 
		}
		return postsTitles;
	}
	
	public List<Post> findPostsByTitle(String title){
		List<Post> posts = postRepository.findPostByTitleContainingIgnoreCase(title);
		System.err.println(posts);
		return posts;
	}
	
	public Post findPostByTitle(String title){
		return postRepository.findPostByTitle(title);
	}

	public String deletePost(Integer id) {
		Post post = postRepository.findOne(id);
		Author author = post.getAuthor();
		author.removePost(post);
		authorRepository.saveAndFlush(author);
		
		postRepository.delete(id);
		postRepository.flush();
		return post.getTitle();
	}

	public Post updatePost(Post post) {
		Post olderVersion = postRepository.getOne(post.getId());
		olderVersion.setTitle(post.getTitle());
		olderVersion.setBody(post.getBody());
		olderVersion.setUpdated_at(new Date());
		return postRepository.saveAndFlush(olderVersion);
	}

}
