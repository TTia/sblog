package sblog.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sblog.orm.Author;
import sblog.repository.AuthorRepository;

@Service
public class SessionService {
	@Autowired
	AuthorRepository authorRepository;

	public boolean isLogged(HttpSession session) {
		Integer id = (Integer) session.getAttribute("author_id");
		return id != null && authorRepository.findOne(id) != null;
	}

	public boolean accessGranted(String email, String password, HttpSession httpSession) {
		String hpassword = encryptPassword(password);
		Author author = authorRepository.findAuthorByEmailAndHpassword(email, hpassword);
		if(author != null){
			httpSession.setAttribute("author_id", author.getId());
			httpSession.setAttribute("author_email", author.getEmail());
			return true;
		}
		return false;
	}

	private String encryptPassword(String password) {
		return password;
	}
}
