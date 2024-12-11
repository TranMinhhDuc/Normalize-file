package Model;

import java.time.LocalDateTime;
import java.util.Collection;

public class History {
	
	private int id;
	private String describe;
	private LocalDateTime time;
	private String location;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescribe() {
		return describe;
	}
	public void setDescribe(String describe) {
		this.describe = describe;
	}
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String localPath) {
		this.location = localPath;
	}
	
	
}
