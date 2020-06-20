package domain;

public  class Library {
  private String id;
  private String title;
  private String author;
  private String level;
  
  
public Library(){}
public Library(String id, String title, String author, String level) {
	super();
	this.id = id;
	this.title = title;
	this.author = author;
	this.level = level;
}

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getAuthor() {
	return author;
}
public void setAuthor(String author) {
	this.author = author;
}
public String getLevel() {
	return level;
}
public void setLevel(String level) {
	this.level = level;
}


}
