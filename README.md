# QA-Cinemas Cohort5 Team 1

## Branch naming convention:
QA-XX where XX is the issue name on JIRA.

Use "git commit -m "QA-XX #comment ..." to add (something meaningful) to JIRA.

## Git commands
To pull new files from master into your branch:

git checkout QA-XX

git fetch origin master

git merge origin/master

## Variable naming
pojoNameId - camelCase with lower-case d for Id (eg: movieId, actorId etc).

UPPERCASE for constants.

## Method writing
```java
method signature(params){
//code
}
```
