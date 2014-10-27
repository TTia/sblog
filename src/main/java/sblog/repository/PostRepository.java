package sblog.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sblog.orm.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	public Post findPostByTitle(String title);
	
	public List<Post> findPostByTitleContainingIgnoreCase(String title);
}

