# ![Books Logo](./books.web/src/main/resources/public/img/Lieblingsbuch149x149.png) books.example
Bücherverwaltung mit JPA aber ohne Applicationserver (mittels spring boot) 

[![Build Status](https://travis-ci.org/FunThomas424242/books.example.svg?branch=master)](https://travis-ci.org/FunThomas424242/books.example)
[ ![Download Releases](./src/main/resources/img/Releases.png) ](https://bintray.com/funthomas424242/books.example/books.example/_latestVersion)
[ ![Download Snapshots](./src/main/resources/img/Snapshots.png) ](http://oss.jfrog.org/simple/oss-snapshot-local/gh/funthomas424242/webapp/)

# Dokumentation
* [Architektur](src/site/markdown/architecture/01_EinfuehrungZiele.md)

# Ausprobieren
1. Zum Ausprobieren bitte zunächst das Projekt auschecken mit git clone https://github.com/FunThomas424242/books.example.git
1. Dann das Projekt bauen mit mvn  -U clean install
1. Und starten mit:
   * java -jar books.web/target/books.app-1.1.2-SNAPSHOT.jar (Frontend)
   * java -jar books.app/target/books.app-1.1.2-SNAPSHOT.jar (Backend)
1. Per Webbrowser die URL [http://localhost:9000/](http://localhost:9000/) aufrufen und das Projekt ausprobieren.
2. Zum Herunterfahren in der gestarteten Console über Ctrl+C ;)

# Backup
* git clone --mirror https://github.com/FunThomas424242/books.example.git
* Für alle weiteren Aktualisierungen in das gespiegelte, lokale Repository wechseln und git remote update ausführen.
* obigen Befehl als shell script per cron ausführen lassen: https://www.garron.me/en/bits/backup-git-bare-repo.html

# Quellen
Das Projekt basiert auf Kode und Erkenntnissen folgender Quellen und Projekte:

* Dokumentation 
    * Swagger: http://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
* JavaScript Integration
    * http://www.beyondjava.net/blog/using-grunt-in-mixed-java-and-javascript-maven-projects/
    * https://github.com/allegro/grunt-maven-plugin
    * https://github.com/cucumber/cucumber-js
* Spring Boot Konfiguration
    * https://github.com/ewolff/spring-boot-demos 
    * http://www.leveluplunch.com/java/tutorials/019-disable-actuator-endpoints-in-spring-boot/
    * https://spring.io/blog/2013/12/19/serving-static-web-content-with-spring-boot
* Spring Boot Controller
    * Redirect über return "redirect:nextpage.html"; (http://stackoverflow.com/questions/33327678/springboot-controller-redirect-not-working)
    * return "index.html" sollte direktes forwarding sein.
* Spring Boot REST
    * https://spring.io/guides/gs/actuator-service/
    * http://geowarin.github.io/a-simple-spring-boot-and-jersey-application.html
* Spring Cloud
    * https://jaxenter.de/spring-cloud-das-microservices-framework-501
    * https://github.com/ewolff/microservice/tree/master/microservice-demo
* Maven
    * https://maven.apache.org/ref/3.3.3/maven-core/lifecycles.html#default_Lifecycle
    * https://github.com/eirslett/frontend-maven-plugin/tree/master/frontend-maven-plugin
* Docker
    * https://jaxenter.de/docker-mit-maven-steuern-20211
    * https://jaxenter.de/docker-rockt-java-wie-sie-die-docker-logging-plug-ins-verwenden-25238
    * https://github.com/craigivy/spring-cloud-microservice-example
    * https://github.com/kbastani/spring-cloud-microservice-example
* Cucumber-JVM Integration in Spring
    * https://github.com/cucumber/cucumber-java-skeleton
    * https://github.com/excellentdrums/Cucumber-JVM-Spring-MVC-Test-HTMLUnit-Demo
    * http://testerstories.com/2014/06/an-introduction-to-using-cucumber-jvm/
    * http://liminescence.blogspot.de/2013/08/integration-testing-with-spring.html
* JBehave Integration in Spring
    * https://github.com/mkuthan/example-jbehave
    * http://mkuthan.github.io/blog/2014/05/29/acceptance-testing-using-jbehave-spring-framework-and-maven/
* Debian Archiv Erstellung
    * http://debian-maven.sourceforge.net/project-info.html
    * https://wiki.debian.org/Teams/JavaPackaging
    * https://wiki.debian.org/JavaPackage
* PGP Integration
    * http://www.javacodegeeks.com/2011/06/java-pretty-good-privacy-pgp.html
* SmartCard Reader Integration
    * http://konstantin.filtschew.de/blog/2009/10/08/smart-cards-durch-die-in-java-eingebaute-java-smartcard-io-javax-smartcardio-ansprechen/
    * https://www.opensc-project.org/opensc/wiki/OverView
    * https://www.opensc-project.org/opensc/wiki/QuickStart
    * https://de.wikipedia.org/wiki/Chipkarte
* Bintray / JFroge Integration
    * https://www.jfrog.com/confluence/pages/viewpage.action?pageId=26083425
* AngularJS UI Grid
   * http://ui-grid.info/docs/#/tutorial/401_AllFeatures
* Thymleaf und AngularJS
   * http://stackoverflow.com/questions/29199089/thymeleaf-value-send-angularjs-ng-onclick-method
   * http://www.mattheye.com/passing-data-between-spring-mvc-and-angularjs/
   * http://stackoverflow.com/questions/17230242/angular-element-vs-document-getelementbyid-or-jquery-selector-with-spin-busy-c
* Testing
   * http://www.vogella.com/tutorials/JUnit/article.html
   * https://jaxenter.de/testen-ohne-neu-zu-bauen-java-8-features-in-junit-tests-36101
   * https://jaxenter.de/verteilte-systeme-automatisiert-testen-geht-das-und-wenn-ja-wie-36066?utm_source=jaxenter.de&utm_medium=referral&utm_campaign=seealso
   * http://www.jayway.com/2013/12/10/json-schema-validation-with-rest-assured/
   * http://blog.codeleak.pl/2015/03/spring-boot-integration-testing-with.html
   * http://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html
   * http://connect-group.github.io/thymeleaf-tdd/
   * http://docs.spring.io/spring-test-htmlunit/docs/current/reference/html5/
* Deployment
    * https://github.com/tcurdt/jdeb/blob/master/docs/maven.md
    * http://debian-maven.sourceforge.net/
* AngularJS
    * https://scotch.io/tutorials/angularjs-best-practices-directory-structure
* JavaScript & Co Infrastructure
    * http://www.toptal.com/angular-js/your-first-angularjs-app-part-2-scaffolding-building-and-testing
    * http://mherman.org/blog/2015/04/09/testing-angularjs-with-protractor-and-karma-part-1/#.VmyeVHrbLQo
    * http://angular.github.io/protractor/#/api
    * https://github.com/frozenamazon/open-weather/
    * https://www.npmjs.com/package/protractor-cucumber
    * https://www.digitalocean.com/community/tutorials/how-to-install-node-js-on-an-ubuntu-14-04-server
    * http://joshowens.me/cucumber-js-and-meteor-the-why-and-how-of-it/
    * http://gruntjs.com/sample-gruntfile
    * https://www.npmjs.com/package/cucumberjs-chromedriver
* JavaScript Libs & Components 
    * Ladespinner: https://github.com/fgnass/spin.js
* Grundlagen für Webanwendungen
    * https://de.m.wikipedia.org/wiki/JavaScript_Object_Notation#JSONP
    * https://de.m.wikipedia.org/wiki/Protocol_Buffers
    * https://de.m.wikipedia.org/wiki/Cross-Origin_Resource_Sharing
    * http://stateless.co/hal_specification.html
    * http://json-schema.org
    * https://github.com/jsonary-js/jsonary



