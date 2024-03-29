## GenMed Spring : Building and Running Instructions

#### Setting up the database :

I have used MySql Server for the database ( feel free to change it as per the requirement ) 

1. Follow the following guide to install and create user in MySql : [Digital Ocean : Installing MySql on Ubuntu 20.04](https://www.digitalocean.com/community/tutorials/how-to-install-mysql-on-ubuntu-20-04)

    NOTE : In `src/main/resources/application.properties` we have the username and password for MySql user account (feel free to change the username and password, use that username in places required as per the digital ocean guide)
    - Install MySql Server : `sudo apt install mysql-server`
    - `sudo mysql`
    
    Once you enter into mysql console, create the user:

    - `CREATE USER 'spring'@'localhost' IDENTIFIED BY 'spUser@123';`
    - `GRANT ALL PRIVILEGES ON *.* TO 'spring'@'localhost' WITH GRANT OPTION;`

2. Now, create the database ( you can find the database name in application.properties, `spring.datasource.url=jdbc:mysql://localhost:3306/genmed`, here `genmed` is the name of the database)

    - `CREATE DATABASE genmed;`
    - `use genmed;` (for selecting the database)

3. Next step is to add entries into the database. We have three .sql files in the repository:

    - `database.sql` : On execution, will create all the tables in the database.
    - `dump.sql` : Random entries (for medicines) to be entered into the system.
    - `final_database.sql` : It contains queries for creating all the tables in the database and also the entries to each database. ( This was generated by creating a dump of local version of a database).

    One simple way, would be to : `source final_database.sql` (but all the entries and orders will be 2 years back, also it creates 3 accounts, I don't know the password to each).

    The other way would be to use `database.sql` for creating the tables, create the users using the application and using `dump.sql` for creating entries for medicines.

NOTE: Make sure that the username and password for sql database matches to what is written in `src/main/resources/application.properties`.



#### Building the application :

1. Installing the required java version:

    `sudo apt install default-jre`

    `sudo apt install default-jdk`

2. Run the following command from repo root:

    `./mvnw spring-boot:run` (This command is basically for compiling the spring boot application, but when ran for the first time, it also installs the required dependencies)


#### NOTES : 

- For creating a user with `admin` role, it must be done from the MySQL console itself. The application does not provide a way of doing it using the web.