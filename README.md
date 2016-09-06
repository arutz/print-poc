maven ear example
=====================
# Camunda Wildfly Full distribution
* Download the Wildfly 10 Camunda full distribution (https://camunda.org/release/camunda-bpm/wildfly10/7.5/camunda-bpm-wildfly10-7.5.0.zip)
* Unzip this distribution to a path of you choosing

# Set up maven
* Open your maven settings file (under "[usr]/.m2/settings.xml" for example)
* Look up / add the "profiles" section and add the following:
```xml
<profile>
    <id>wildfly-local</id>
    <properties>
        <wildfly-home>/camunda-bpm-wildfly10-7.5.0/server/wildfly-10.0.0.Final</wildfly-home>
        <wildfly-hostname>127.0.0.1</wildfly-hostname>
        <wildfly-port>9999</wildfly-port>
        <wildfly-username>wildflyadmin</wildfly-username>
        <wildfly-password>wildflyadmin</wildfly-password>
    </properties>
</profile>
```
