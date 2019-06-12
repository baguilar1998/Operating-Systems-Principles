#include <sys/wait.h>
#include <stdio.h>

int main() 
{
	int processID = fork();
	
	/*
		If the process id is greater than 0. That mean the child ID
		is being returned to the parent meaning that the parent process is running
	*/
	if(processID > 0) {
		printf("I am the child with PID [%d] and my parent has PPID [%d].\n", getpid(), getppid());
		sleep(1);
	}
	/*
		If the value is not greater than 0. This means that we are in the child
		process because every child process returns the value of 0 to its parent and
		it means that the child process was created successfully
	*/
	else {
		printf("I am the parent and my id is [%d]\n", getpid());
		sleep(30);
	}
	
}
