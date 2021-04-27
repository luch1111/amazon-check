Run tests: mvn clean test -Duser=YOUR_EMAIL -Dpsw=YOUR_PSW site
Allure report: target/site/allure-maven.html

For parallel run demonstration uncomment SecondTest in src/test/suites/test-set.xml

To run on specific browser use command: mvn clean test -Duser=YOUR_EMAIL -Dpsw=YOUR_PSW -Dbrowser=opera site