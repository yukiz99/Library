package domain;

public class Book extends Library{
   private String publishPlace;
   private String isbn;
   private int pages;
   
public Book(){}
public Book(String id, String title, String author, String level,
		String publishPlace, String isbn, int pages) {
	super(id, title, author, level);
	this.publishPlace = publishPlace;
	this.isbn = isbn;
	this.pages = pages;
}
public String getPublishPlace() {
	return publishPlace;
}
public void setPublishPlace(String publishPlace) {
	this.publishPlace = publishPlace;
}
public String getIsbn() {
	return isbn;
}
public void setIsbn(String isbn) {
	this.isbn = isbn;
}
public int getPages() {
	return pages;
}
public void setPages(int pages) {
	this.pages = pages;
}


   
}
