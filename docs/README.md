# DEV Guide

## Maven commands
|Command|Description|
|--|--|
|```mvn -Pdev-tools formatter:format```|Format java code.|
|```mvn test -Pjacoco```|Generate a test coverage.|
|```mvn -Pjavadoc site && mvn site:stage```|Generate a javadoc.|
|```mvn -Pjavadoc scm-publish:publish-scm```|Publish on GitHub Pages.|
|```mvn sonar:sonar```| [https://sonarcloud.io/](https://sonarcloud.io/dashboard?id=pl.softfly.integ%3Asoftfly-integ)|
|```mvn coveralls:report```| [https://coveralls.io/](https://coveralls.io/github/softfly/softfly-integration)|