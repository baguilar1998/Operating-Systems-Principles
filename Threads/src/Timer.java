public class Timer implements Runnable{

	// Integer values to keep of track of the fixed time intervals for the professor
	public static int arrivalTime, startOfficeHour, startChatSession, endChatSession, endOfficeHour;
	private Teacher teacher;
	
	public Timer(Teacher t) {
		teacher = t;
		arrivalTime = 4;
		startOfficeHour = 6;
		endChatSession = 10;
		endOfficeHour = 15;
	}
	
	@Override
	public void run() {
		// A counter to figure out which time interval we are in
		int counter = 0;
		
		// While the professor hasn't arrived, simulate the professor going to the 
		// office by sleep()
		while(counter!=arrivalTime) {
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		// Simulate the professor arriving to the office
		teacher.arriveToOffice();
		
		// While the professor hasn't started his office hours yet,
		// he will answer any type A questions that he has available
		while(startOfficeHour!=counter) {
			teacher.answerTypeAQuestions();
			try {
				Thread.currentThread().sleep((long) (Math.random()*2000));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		// Start the office hours
		teacher.startOfficeHours();
		
		// Initially as soon as he starts the office hours, he also starts 
		// his online chat session
		teacher.startOnlineChatSession();
		
		// While the chat session has ended, if there students waiting
		// to chat with the professor, chat with the first student in
		// line
		while(endChatSession != counter) {
			teacher.chatWithStudent();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			++counter;
		}
		
		// End the chat session
		teacher.endOnlineChatSession();

		// With the remaining time that the professor has, he will answer 
		// as many type A questions as he can
		while(endOfficeHour != counter) {
			teacher.answerTypeAQuestions();
			try {
				Thread.currentThread().sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			counter++;
		}
		
		// End the office hours
		teacher.endOfficeHours();
		

	}

}
