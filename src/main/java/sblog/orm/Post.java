package sblog.orm;


import javax.persistence.*;

import org.springframework.stereotype.Component;

import java.util.Date;

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

    @Column(nullable = false)
    String title;

    @Column(nullable = false)
    String body;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date created_at;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    Date updated_at;

    public Integer getId() {
        return id;
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
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
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
}
