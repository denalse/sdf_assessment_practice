## Download Maven

```
mvn archetype:generate -DgroupId=nus.iss -DartifactId=task01 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

*use DataInputStream (dis) to read the file*
*use DataOutputStream (dos) to write the file*

- mvn clean compile
- mvn package (to produce jar file)
*if no error means can run properly..*

run task01:
```
java -cp target\task01-1.0-SNAPSHOT.jar nus.iss.App mail.csv template.txt
```

run task02:
```
java -cp target\task02-1.0-SNAPSHOT.jar nus.iss.Client localhost:80
```
