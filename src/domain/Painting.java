package domain;

public class Painting extends Library{
private String publishCountry;
private int width;
private int height;
    
    public Painting(){}
	public Painting(String id, String title, String author, String level,
			String publishCountry, int width, int height) {
		super(id, title, author, level);
		this.publishCountry = publishCountry;
		this.width = width;
		this.height = height;
	}
	public String getPublishCountry() {
		return publishCountry;
	}
	public void setPublishCountry(String publishCountry) {
		this.publishCountry = publishCountry;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
    
    
    
}
