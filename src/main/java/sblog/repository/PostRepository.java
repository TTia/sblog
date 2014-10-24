package sblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sblog.orm.Author;
import sblog.orm.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
}

