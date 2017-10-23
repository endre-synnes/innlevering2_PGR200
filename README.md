# Exercise 2 PGR200

**How to run the application**

1.  First you start the server, this is done by opening 
    the ServerApplication-class and run this class. This
    class will call the class "DBCreator" create an database 
    and populate it with all the files within the "files" folder.

2.  Now you can open the ClientApplication class and run this. It will
    automatically connect to the server doe to the fact both server
    and client use the same "Properties"-file.
    

**What can you do with my application**

When you run the Client-class you will be prompted with some alternatives.
Here you can ask the server to send content of a table, print out all tables
in the Database, get metadata from a table and so on.


**Comments to my solution**

***The good***
I am satisfied with the fact that my solution can handle any types of MySQL-tables.
It is a very dynamic solution. When i send data from the server, it is stored in an object.
This object gets unpacked by the client and presented to the user with good formatting so it
is easy to understand for the user of this application.


***The bad***
My solution only send primitives such as Strings to the server, this should be done by Objects.
Exception handling is not good. I have not had time to fix this as i wanted. The names of variables
and methods should be better to describe more what they do. I also have to write more tests to
test all aspects of my solution. Javadoc have to be generated.