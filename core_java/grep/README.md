# Introduction
The Grep application is a project that searches for a pattern in all the files in a given directory and writes the resulting output to another file. The implementation of this project includes two programming methods: one using normal for-loops and recursion concepts, and the other using Java 8 version Lambda interfaces. This application uses Maven to build projects and includes all necessary dependencies, such as an SLF4J logger and Docker, to make the application easily accessible to other users.
Technologies
        Java 8
        Maven
        Java Streams/Lambda Functions
        Java IO APIs
        Docker
# Quick Start
Required Packages:

    Java 8
    Maven
    Docker

1. Using GitHub:
   - Clone the GitHub project 
   - Edit configurations of the main class and pass the values for pattern, sorce directory and the output directory. 
   - Build the project using maven 
   - Run the main class of JavaGrepImp or JavaGrepLambdaImp 
2. Using Docker:
   - Pull the docker image from docker hub. <br>
   ```docker pull manoj89/grep```
   - Run the docker container using the following command <br>
   ```docker run --rm \ -v `pwd`/data:/data -v `pwd`/log:/log \ ${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out```
# Implemenation
The App contains 1 interface:

    JavaGrep

And two implementations of JavaGrep interface:

    JavaGrepImp (Using for loop approach)
    JavaGrepStreamImp (Using Streams/Lambda approach)
## Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```
## Performance Issue
This application uses an ArrayList to store all the files from the given directory. And, once an ArrayList reaches its capacity, it grows by about 50% in size, even if we are only adding one element. We can use a buffer stream or Stream API instead of an ArrayList to solve this problem because it allocates only the required amount of memory.
# Test
I used logger slf4j and debugger for testing the application. A logger is used to display the output of each module while doing unit testing. I also input some sample data and compared the output to ensure it's working correctly. During integration testing, a debugger used to find and resolve errors in the project.

# Deployment
1. Create dockerfile 
```
cat > Dockerfile << EOF
FROM openjdk:8-alpine
COPY target/grep*.jar /usr/local/app/grep/lib/grep.jar
ENTRYPOINT ["java","-jar","/usr/local/app/grep/lib/grep.jar"]
EOF
```
2. Package Grep app<br>
```
mvn clean package
```

3. Build a new docker image locally<br>
```
docker build -t ${docker_user}/grep
```
4. Verify your image<br>
```
docker image ls | grep "grep"
```
5. Run docker container
```
docker run --rm \
-v `pwd`/data:/data -v `pwd`/log:/log \
${docker_user}/grep .*Romeo.*Juliet.* /data /log/grep.out
```
6. Push your image to Docker Hub<br>
```
docker push ${docker_user}/grep
```
7. Go to hub.docker.com and verify your image
# Improvement
  - We can redefine a GREP interface to return Java Streams in functions instead of Lists, which can improve memory utilization.
  - Add JavaGrepStream interface and its implementation, which allows functions to pass Streams between functions.
