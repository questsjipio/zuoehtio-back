# Getting Started with ZuoEhTio back-end

## Location of Java codes
`src/main/java/com/zuoehtio`

## Location of SQL for creating scripts
`src/main/resources/ZuoEhTio_DB_Create.sql`

## Location of schema diagram
`src/main/resources/Schema.png`

## Steps: Create database schema for ZuoEhTio

1. Go to:
`src/main/resources/ZuoEhTio_DB_Create.sql`

2. ENSURE THERE IS NO EXISTING ZuoEhTio schema in MySQL database. 
- If so, proceed to step 3. 
- If not, back-up the existing ZuoEhTio database if you need it. Then, delete the existing ZuoEhTio database before you proceed to step 3.

3. Execute the SQL script from step 1 in MySQL database.

## Steps: Execute Spring-Boot

1. Go to `src/main/resources` and open application.properties using a text editor

2. Add in the following lines (replace <...> with the correct database parameter values)
- `spring.datasource.url=jdbc:mysql://<MySQL Database Address>/zuoehtio`
- `spring.datasource.username=<Username for Accessing MySQL Database>`
- `spring.datasource.password=<Password for Accessing MySQL Database>`

3. Open Command Prompt (CMD) and change directory to root folder of ZuoEhTio back-end. Enter this into CMD:
`mvn clean install`

4. Enter this into the same CMD window:
`mvn spring-boot:run`

## Steps: Create target folder's jar file (Optional)

1. If you want to create jar file in target folder, ensure that you have completed the basic steps above first

2. Go to root folder of ZuoEhTio's back-end, then go into target folder from there

3. Inside the target folder, there is a jar file named similarly to: zuoehtio-0.0.1-SNAPSHOT.jar

4. Open Command Prompt (CMD) and change directory to the target folder. Enter this into CMD:
`java -jar zuoehtio-0.0.1-SNAPSHOT.jar`
- Replace "zuoehtio-0.0.1-SNAPSHOT.jar" with the jar file's name that you see in target folder

## Run Full-Stack of Web App

- You need to have front-end code (https://github.com/questsjipio/zuoehtio-front), back-end code and database (https://github.com/questsjipio/zuoehtio-back) to be running at the same time in order to run full stack.
- To do so, please clone and download both ZuoEhTio's front-end and back-end. Then, follow the README.md in those repositories.