package com.the703.basic014_ex;

import java.util.Objects;

public class BookDto {
	private String title;  
	private String author;
	
	public BookDto() { super(); }
	public BookDto(String title, String author) { super(); this.title = title; this.author = author; }
	
	@Override
	public int hashCode() {
		return Objects.hash(author, title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDto other = (BookDto) obj;
		return Objects.equals(author, other.author) && Objects.equals(title, other.title);
	}
	@Override public String toString() { return "BookDto [title=" + title + ", author=" + author + "]"; }
	
	public String getTitle() { return title; }
	public void setTitle(String title) { this.title = title; }
	public String getAuthor() { return author; }
	public void setAuthor(String author) { this.author = author; }
}