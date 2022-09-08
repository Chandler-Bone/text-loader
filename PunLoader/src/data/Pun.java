package data;

import java.io.Serializable;

public class Pun implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String text;
	
	public Pun(Integer id, String text) {
		this.id = id;
		this.text = text;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public void setText(String text) {
		this.text = text;
	};
	
	public int getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	public void updateText(String text) {
		this.text = new String(text);
	}
	
	public String toString() {
		return id + " : \"" + text + "\"";
	}
}
