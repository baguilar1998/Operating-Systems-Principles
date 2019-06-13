import java.util.*;
public class Teacher implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Teacher can arrive to the office and answer
	 * some typeA questions before office hours start.
	 * 
	 * Students can send typeA questions and the teacher could have
	 * not arrived to his office yet
	 */
	public void arriveToOffice() {
		
	}
	
	/**
	 * As soon as the office hours start, the teacher
	 * has to stop answering typeA questions. It's okay
	 * if he can't answer all of them
	 */
	public void startOfficeHours() {
		
	}
	
	/**
	 * Once the online chat session starts, he will answer
	 * type B questions for the first student that arrived 
	 * to the chat session.
	 * 
	 */
	public void startOnlineChatSession() {
		
	}
	
	/**
	 * If there are no more students are in the online chat.
	 * End the online chat session and answer any remaining
	 * type A questions
	 */
	public void endOnlineChatSession() {
		
	}
	
	/**
	 * End the office hours
	 */
	public void endOfficeHours() {
		
	}
}
