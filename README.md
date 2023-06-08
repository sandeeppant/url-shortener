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

### Classes
1. com.connectid.BitlyAPI.java

    The initializeProperties() method initializes the configPropertiesMap by calling the readProperty() method from the PropertyFile class.

    The getGroups() method sends a GET request to the Bitly API to retrieve the groups.

    The getAccessToken() method obtains an access token by sending a POST request to the Bitly API.

    The getExpandURL() method sends a POST request to the Bitly API to expand a short URL.

2. com.connectid.CatFactRestAPI.java

    The getFacts() method sends a GET request to the Cat Fact API to retrieve multiple facts.

    The getFact() method sends a GET request to the Cat Fact API to retrieve a single fact. 

3. com.connectid.BaseUITest.java
    
    The setup() method is annotated with @BeforeMethod. It sets up the WebDriver by calling WebDriverManager.chromedriver().setup() to configure the ChromeDriver, creates a new instance of the ChromeDriver, maximizes the browser window.
    
    The teardown() method is annotated with @AfterMethod. It quits the WebDriver by calling driver.quit().
    
4. com.connectid.uitests.URLShortenerUITest.java

    URLShortenerUITest extends the BaseUITest class and includes two test methods: verifyValidShorterURL() and verifyInvalidShorterURL()
    
5. com.connectid.apitests.URLShortenerAPITest.java

    Calls the BitlyAPI.getExpandURL() method with the access token and short URL to retrieve the expanded long URL. Compares the retrieved long URL with the original long URL using Assert.assertEquals().
    
6. com.connectid.apitests.CatFactRestAPITest.java

    Calls the CatFactRestAPI.getFacts() method to retrieve the response from the Cat Facts API.
    
    Calls the CatFactRestAPI.getFact() method to retrieve a single cat fact.
