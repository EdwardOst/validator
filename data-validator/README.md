data-validator
==============

utility wrapper service for spring expression language.


My env: java version "1.8.0_144"

Eclipse: Eclipse Java EE IDE for Web Developers. Version: Oxygen Release (4.7.0) Build id: 20170620-1800

Exit Eclipse(if it is open) and downloaded jar from https://projectlombok.org/download

execute command: java -jar lombok.jar

This command will open window as shown here https://projectlombok.org/setup/eclipse, install and quit the installer.

Add jar to build path/add it to pom.xml.

restart eclipse.

Go to Eclipse --> About Eclipse --> check 'Lombok v1.16.18 "Dancing Elephant" is installed. https://projectlombok.org/'
That's it. It worked. I did not change eclipse init script.

Note: Read the note in following image regarding -vm options If you start Eclipse with a custom -vm parameter, you'll need to add:

-vmargs -javaagent:<path-to-lombok-jar>/lombok.jar