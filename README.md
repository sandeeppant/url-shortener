## URL Shortener and CAT Fact Rest API

### Prerequisite
1.	Java 8
2.	Eclipse 2021-09
3.	Install Gradle 8.1.1
4.  Git

### How to Clone, Build and Run Jar
1. git clone https://github.com/sandeeppant/url-shortener.git
2. gradlew clean build fatJar
3. java -cp build\libs\app.jar;build\classes\java\test;build\resources\test org.testng.TestNG testng.xml

### Import Code into Eclipse
1. Download the code to local folder from GitHub using git clone https://github.com/sandeeppant/url-shortener.git
2. Goto Eclipse File > Import > Gradle > Existing Gradle Project
3. Navigate to folder where code is downloaded e.g. D:\Code\url-shortener
4. Click Next > Next > Finish
5. Install TestNG from Eclipse Market Place in case we want to run Test Cases as TestNG

### Test Cases
https://github.com/sandeeppant/url-shortener/blob/main/TestCases.txt
