package sblog.orm;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@Entity
public class Author {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	Integer id;

	@NotNull
	@Column(nullable = false, unique = true)
	String email;

	@NotNull
	@Column(nullable = false)
	String hpassword;

	@NotNull
	@Column(nullable = false)
	String hsalt;

	@OneToMany(mappedBy = "author", cascade = { CascadeType.ALL }, fetch = FetchType.LAZY)
	Set<Post> posts;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date created_at;

	@NotNull
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date updated_at;

	public Author() {
		setCreated_at(new Date());
	}

	public Integer getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public String getHpassword() {
		return hpassword;
	}

	public void setHpassword(String hpassword) {
		this.hpassword = hpassword;
	}

	public String getHsalt() {
		return hsalt;
	}

	public void setHsalt(String hsalt) {
		this.hsalt = hsalt;
	}

	protected Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
        if(updated_at == null){
        	this.updated_at = this.created_at;
        }
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public Set<Post> getPosts() {
		return posts == null ? new HashSet<>() : posts;
	}

	public boolean addPost(Post post) {
		if (posts == null) {
			posts = new HashSet<>();
		}
		return posts.add(post);
	}
	
	public boolean removePost(Post post){
		if (posts == null) {
			return false;
		}
		return posts.remove(post);
	}

	public String authorDetail() {
		return new StringBuilder("Scritto da ").append(email).toString();
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Author)) {
			return false;
		}
		Author author = (Author) obj;
		return this.hashCode() == author.hashCode();
	}
}
