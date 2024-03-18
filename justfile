run filename:
    mvn package
    java -jar target/java-course-1.0-{{filename}}.jar

list:
    @echo "Available targets:"
    @sed -n 's/.*classifier>\(.*\)<.*/\t\1/p' pom.xml

