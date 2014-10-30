package sblog.orm;


import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Post {
    public static final String AUTHOR_POST_JOIN_TABLE = "posts_authors",
            AUTHOR_JOIN_COLUMN = "aid",
            POST_JOIN_COLUMN = "pid";

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    Integer id;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH}, fetch=FetchType.LAZY)
    @JoinTable(name = AUTHOR_POST_JOIN_TABLE,
            joinColumns = @JoinColumn(
                    name = POST_JOIN_COLUMN),
            inverseJoinColumns = @JoinColumn(name = AUTHOR_JOIN_COLUMN),
            uniqueConstraints = @UniqueConstraint(columnNames = {
                    AUTHOR_JOIN_COLUMN, POST_JOIN_COLUMN}))
    Author author;

    @Size(min=5, max=100, message = "Il titolo deve essere compreso fra 5 e 100 caratteri.")
    @NotNull(message = "Titolo mancante.")
    @Column(nullable = false, unique = true)
    String title;

    @Size(min=5, message = "Il post deve essere almeno di 5 caratteri.")
    @NotNull(message = "Articolo mancante.")
    @Lob
    @Column(nullable = false)
    String body;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date createdAt;

    @NotNull
    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date updatedAt;
    
    public Post(){
    	id = 0;
    	setCreated_at(new Date());
    }

    public Integer getId() {
        return id;
    }
    
    @Deprecated
    public void setId(Integer id) {
		this.id = id;
	}

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Date getCreated_at() {
        return createdAt;
    }

    protected void setCreated_at(Date created_at) {
        this.createdAt = created_at;
        if(updatedAt == null){
        	this.updatedAt = this.createdAt;
        }
    }

    public Date getUpdated_at() {
        return updatedAt;
    }
    
    public String creationDetail(){
    	StringBuilder stringBuilder = new StringBuilder("Creato il ");
    	stringBuilder.append(formatDate(createdAt));
    	
    	if(createdAt.equals(updatedAt)){
    		stringBuilder.append(".");
    		return stringBuilder.toString();
    	}
    	stringBuilder.append(" e modificato il ");
    	stringBuilder.append(formatDate(updatedAt));
    	stringBuilder.append(".");
    	
    	return stringBuilder.toString();
    }

    public void setUpdated_at(Date updated_at) {
        this.updatedAt = updated_at;
    }

    @Override
    public int hashCode() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post)) {
            return false;
        }
        Post post = (Post) obj;
        return this.hashCode() == post.hashCode();
    }
    
    private String formatDate(Date date){
    	SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy @ h:mm a");
    	return simpleDateFormat.format(date);
    }
}
