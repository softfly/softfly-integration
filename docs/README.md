# DEV Guide

## Maven commands
|Command|Description|
|--|--|
|```mvn test -Pjacoco```|Generate a test coverage.|
|```mvn -Pjavadoc site && mvn site:stage```|Generate a javadoc.|
|```mvn -Pjavadoc scm-publish:publish-scm```|Publish on GitHub Pages.|