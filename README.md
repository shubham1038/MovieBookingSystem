# Movie Ticket Booking Platform

XYZ wants to build an online movie ticket booking platform that caters to both B2B (theatre partners) and B2C (end customers) clients.

### Author
Shubham Agarwal (shubham.agarwal7076@gmail.com)

### Tech

Program uses the following technologies:

* [Java] - Code base language (version 1.8)
* [Spring] - For Dependency Injection (version 4.0)
* [Apache Maven] - Build automation tool (version 3.3.9)
* [junit] - Library for testing (version 4.12)
* [codehaus mojo] - Execute Java app plugin (version 1.6.0)

### Installation

XYZ wants to build an online movie ticket booking platform that caters to both B2B (theatre partners) and B2C (end customers) clients.

  #Enable theatre partners to onboard their theatres over this platform and get access to a bigger customer base while going digital.
  #Enable end customers to browse the platform to get access to movies across different cities, languages, and genres, as well as book tickets in advance with a seamless experience.
  
### Execution

Movie Ticket Booking System requires [Maven](https://maven.apache.org/) v3+ and [Java] v1.8+ to run.

Please set JAVA_HOME and M2_HOME in environment variable then 
Open a terminal (CMD) and go to project location before executing below commands to compile and execute:

```sh
$ mvn clean install
$ mvn exec:java
```

Output will be shown in the terminal. Find bellow an output example:

"C:\Program Files\Zulu\zulu-8\bin\java.exe"
Jun 18, 2023 2:53:59 PM org.springframework.context.support.AbstractApplicationContext prepareRefresh
INFO: Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@37a71e93: startup date [Sun Jun 18 14:53:59 IST 2023]; root of context hierarchy
--------------------------------
 Search Movies across different cities, languages         
--------------------------------
| Movie      |
 | Movie 1    |
--------------------------------
 Search Theatres         
--------------------------------
| Theatre Name | Theatre City |
--------------------------------
| Theatre 1  | City 1   |
Show Time -2023-06-18 03:53:59
--------------------------------
 Tickets booked successfully!         
--------------------------------
| Movie      | Theatre  | Number of Tickets | Time |
--------------------------------
| Movie 1    | Theatre 1 | 0003 | 2023-06-18 03:53:59 |
--------------------------------
 Bulk Booking Done successfully!         
--------------------------------
| Movie      | Theatre  | Number of Tickets | Time |
| Movie 1    | Theatre 1 | 0005 | 2023-06-18 03:53:59 |
| Movie 2    | Theatre 2 | 0005 | 2023-06-18 02:54:59 |
 -----------Bulk Booking Done successfully!---------
Jun 18, 2023 2:53:59 PM org.springframework.context.support.AbstractApplicationContext doClose
INFO: Closing org.springframework.context.annotation.AnnotationConfigApplicationContext@37a71e93: startup date [Sun Jun 18 14:53:59 IST 2023]; root of context hierarchy


### Design

These are the main classes used in Movie Ticket Booking System project:

| *com.movie.booking.service* |
| ------------ | 
| MovieTicketBookingPlatformService: * Class to perform below functionality 
| Add Movies  , Add Theatre , Search Movies, Theatres , Book Movies / Bulk Movies , Cancel Movie/ Bulk Movie and discount Feature.* |

| *com.movie.booking.repoo* |
| ------------ | 	
| MovieTicketBookingPlatformRepo: *Class to load task from resource.*  |

| *com.movie.booking.model* |
| ------------ | 
| Booking: *Class to store show timings and No of tickets to book*  |
| Movie: * Class to store Movie name , language, genre, duration .*  |
| Showtime: * Class to store Show related information .*  |
| Theatre: *Class to keep Theatre details .*  |

| *com.movie.booking.exceptionn* |
| ------------ | 
| MovieTicketBookingPlatformException: *Custom exception to handle Movie Ticket Booking exceptions*  |
