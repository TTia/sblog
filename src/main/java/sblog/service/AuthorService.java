package sblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sblog.orm.Author;
import sblog.repository.AuthorRepository;

@Service
public class AuthorService {
	@Autowired
	AuthorRepository authorRepository;
	
	public Author getAuthor(Integer id){
		return authorRepository.findOne(id);
	}
	
	public boolean checkPassword(String email, String password){
		return false;
	}
	
	@Deprecated
	public void defineTTia(){
		if(authorRepository.count() != 0){
			return;
		}
		Author author = new Author();
		author.setEmail("ttia@sblog.io");
		author.setHsalt("hsalt");
		author.setHpassword("password");
		authorRepository.saveAndFlush(author);
	}
	
	@Deprecated
	public Author getTTia(){
		return authorRepository.findAll().get(0);
	}

}
