package domain;

public class DVD extends Library{
   private String publisher;
   private String publishYear;
   private double playTime;
   
public DVD(){}   
public DVD(String id, String title, String author, String level, String publisher,
		String publishYear, double playTime) {
	super(id, title, author, level);
	this.publisher = publisher;
	this.publishYear = publishYear;
	this.playTime = playTime;
}
public String getPublisher() {
	return publisher;
}
public void setPublisher(String publisher) {
	this.publisher = publisher;
}
public String getPublishYear() {
	return publishYear;
}
public void setPublishYear(String publishYear) {
	this.publishYear = publishYear;
}
public double getPlayTime() {
	return playTime;
}
public void setPlayTime(double playTime) {
	this.playTime = playTime;
}
   
   
}
