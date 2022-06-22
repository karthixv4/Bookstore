package book.store.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "genre_details")
public class Genre {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long gId;
	@Column(nullable = false, name = "genre_name", length = 100)
	private String gName;
	@OneToMany(mappedBy = "genre")
    private List<Book> books = new ArrayList<>();
	public Long getgId() {
		return gId;
	}
	public void setgId(Long gId) {
		this.gId = gId;
	}
	public String getgName() {
		return gName;
	}
	public void setgName(String gName) {
		this.gName = gName;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
