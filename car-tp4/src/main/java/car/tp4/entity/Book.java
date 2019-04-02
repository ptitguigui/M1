package car.tp4.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String author;
	private String title;

	public Book() {
	}

	public Book(String author, String title) {
		this.author = author;
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Book book = (Book) o;

		if (id != book.id)
			return false;
		if (!author.equals(book.author))
			return false;
		return title.equals(book.title);
	}

	@Override
	public int hashCode() {
		int result = (int) (id ^ (id >>> 32));
		result = 31 * result + author.hashCode();
		result = 31 * result + title.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Book{" + "author='" + author + '\'' + ", title='" + title + '\'' + '}';
	}
}
