University console where you can ask questions about university departments or find lectors.
The Main questions and answer you will get:

1. User Input: Who is head of department {department_name}
   Answer: Head of {department_name} department is {head_of_department_name}

2. User Input: Show {department_name} statistics.
   Answer: assistants - {assistants_count}.
   associate professors - {associate_professors_count}
   professors -{professors_count}

3. User Input: Show the average salary for the department {department_name}.
   Answer: The average salary of {department_name} is {average_salary}

4. User Input: Show count of employee for {department_name}.
   Answer: {employee_count}

5. User Input: Global search by {template}.
   Example: Global search by van
   Answer: Ivan Petrenko, Petro Ivanov

But also you can use shortened words like: stat, avg, dept or just similar words like: pay, stuff

Database is created and gets filled when you open start it for the first time with the help of data seeding.
I used keyword to determine what command is being used and with regex extracted what's needed.