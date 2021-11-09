# Poised Enginneering Project Management System 

A Java Implementation for a Project Management System 

This is a part of a series of HyperionDev Capstone Projects, I will be creating a Project management program using the java language using mySQL.
In this Capstone Project, I will be creating a program for a small business called Poised Engineering, helping them keep track of their projects.


# HyperionDev Capstone Projects Instrctions 

You are asked to create a project management system for a small structural engineering firm called “Poised”. Poised does the engineering needed to ensure the structural integrity of various buildings. They want you to create a Java program that they can use to keep track of the many projects on which they work. 

	

	Write the code that will do the following:
	
		1.Design and create a database called PoisePMS. Assume that each project can only be assigned to one Structural Engineer. 
    Each project will also only have one Project Manager, one Architect and one customer.
		
			■ Poised stores the following information for each project that they work on:
			
				● First, the username followed by a comma, a space and then the password.
				● Project number. 
				● Project name. 
				● What type of building is being designed? E.g. House, apartment block or store, etc. 
				● The physical address for the project. 
				● ERF number. 
				● The total fee being charged for the project. 
				● The total amount paid to date. 
				● Deadline for the project. 
				● The name, telephone number, email address and physical address of the architect for the project. 
				● The name, telephone number, email address and physical address of the contractor for the project. 
				● The name, telephone number, email address and physical address of the customer for the project.
				● One username and corresponding password per line. 
				
				
			■ Poised wants to be able to use your program to do the following:
			
				● Capture information about new projects. If a project name is not provided when the information is captured, name the project using the surname of the customer. For example, a house
				being built by Mike Tyson would be called “House Tyson.” An apartment block owned by Jared Goldman would be called “Apartment Goldman.”
				● Update information about existing projects. Information may need to be adjusted at different stages throughout the lifecycle of a project. For example, the deadline might change after
				a meeting with various stakeholders. 
				● Finalise existing projects. 
					
			■ When a project is finalised, the following should happen:
				
				● An invoice should be generated for the client. This invoice should contain the customer’s contact details and the total amount that the customer must still pay. This amount is
				calculated by subtracting the total amount paid to date from the total fee for the project. If the customer has already paid the full fee, an invoice should not be generated. 
				● The project should be marked as “finalised” and the completion date should be added. All the information about the project should be added to a database	
			
			■ See a list of projects that still need to be completed. 
			■ See a list of projects that are past the due date. 
			■ Find and select a project by entering either the project number or project name.
      
      	■ Follow these steps:
			
				● Design and create a database called PoisePMS. Assume that each project can only be assigned to one Structural Engineer. Each project will also only have one Project Manager, one Architect and one customer.
				● An ERD that shows the relationships between the tables in your database.
				● Add at least two rows of data to each table in the database. Submit screenshots of your console that show how data is added to the tables.
		 
# Task Manager 
 
Built around the needs of a fictitious company called Poised Engineering, the Project Management System serves as a program that Poised Engineering management and staff can use to keep track of the many projects on which they work and ensure resources are well allocated towards completing each project.
     
## Features
The features of the software is as follows:
* The user should be able to manage a huge amount of projects and resources are well allocated towards completing each project by *capturing the details that are used to create a new project* or *adding tasks* or *changing the due date on on-going projects* or *changing service fees and updating bills and invoices* or *updating contractors details* and a host of other rich features all saved to a database.
