Run tests: mvn clean test site
Allure report: target/site/allure-maven.html

For parallel run demonstration uncomment SecondTest in src/test/suites/test-set.xml

To run on specific browser use command: mvn clean test -Dbrowser=opera site