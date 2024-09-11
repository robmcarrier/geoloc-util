Hi Fetch,
Here's my _Fetch SDET Take-Home Test_.  It's written in java and can be ran a couple ways.

Be sure to create a `application.properties` file in `src/main/resources`(use example in same directory) 

1. Run the maven package command then run the .jar
   * `mvn package`
   * `java -jar target/geoloc-util-1.0-SNAPSHOT-jar-with-dependencies.jar {{your zipcodes or location names}}`
2. Build the docker image and run it
   * `docker build -t geoloc .`
   * `docker run geoloc {{your zipcodes or location names}}`