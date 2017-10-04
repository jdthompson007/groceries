Groceries Java Console Program
==============================
Author: John Thompson
Date: 4th October 2017
Version: 1.0

Purpose - to read a HTML page of grocery items and return the result as JSON data

IDE used: Eclipse STS 3.83
Dependencies: Java 8 must be installed in order to run the application
Build tool: gradle 4.0.2 (now using gradle wrapper so gradle needn't be installed to build and run)

To compile from the command line run: build.bat on Windows or ./build.sh on unix 
(will create a fat jar containing dependent JAR files)
To run from the command line run: run.bat on Windows or ./run.sh on unix
To run all tests from the command line: run "gradlew.bat test" on Windows or "gradlew test" on unix 

Note that this application has been developed under Windows.  You may need to add execute permissions for the UNIX scripts
e.g.

chmod 777 gradlew
chmod 777 build.sh
chmod 777 run.sh