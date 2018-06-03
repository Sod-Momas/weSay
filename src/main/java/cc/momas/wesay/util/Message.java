package cc.momas.wesay.util;

public class Message {
	private String username;
	private String content;

	//constructor
	public Message() {
		super();
	}

	public Message(String username, String content) {
		super();
		this.username = username;
		this.content = content;
	}

	// getter and setter
	public String getUsername() {
		return username;
	}

	public String getContent() {
		return content;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setContent(String content) {
		this.content = content;
	}

	
	@Override
	public String toString() {
		return "Message [content=" + content + ", username=" + username + "]";
	}

	
}
