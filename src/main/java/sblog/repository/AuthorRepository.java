package sblog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sblog.orm.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	public Author findAuthorByEmailAndHpassword(String email, String hpassword);
}
