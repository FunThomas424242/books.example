
---
[[01_EinfuehrungZiele]( 01_EinfuehrungZiele.md)]

---

# 9 Entscheidungen#

## Versionierung
06.03.2015 In dem Projekt ist [Semantische Versionierung](SemantischeVersionierung.md) zu nutzen.

## Client Server Schnittstelle
* Die Anwendung wird als Sammlung von Microservices realisiert
* Das Backend wird als REST Service implementiert
* Das Frontend wird als reine AngularJS Anwendung implementiert
* Die GUI wird über AngularUI und Bootstrap realisiert
* Die Kommunikation zwischen Server und Client erfolgt zustandslos im Sinne von HATEOAS
* Weder im Client noch im Server soll ein Zustand gehalten werden. 
* Ein durch obige Entscheidungen entstehendes hohes Aufkommen an Requests wird akzeptiert
* Das Deployment soll über DockerContainer erfolgen

Entscheidung getroffen am 07.11.2015 auf folgenden Grundlagen:
* Über ein REST Backend kann eine möglichst lose Kopplung vom Client realisiert werden.
* Durch die Zustandslose Kommunikation vereinfachen sich Loadbalancing und Failover Szenarien, da keine Sessionreplikation benötigt wird.
* Durch die Erzeugung von Docker Images können bei Bedarf weitere Container bedarfsgerecht von einem Imagae erzeugt werden und dadurch kann ein Skallieren der Anwendung auf einfachste Art ermöglicht werden.
* Durch den konsequenten Einsatz von HATEOAS benötigt der Client keine Information über die URLs des Backends (bis auf den Einstieg URL). Dadurch können die URLs vom Backend nach Belieben spontan geändert werden. 
* Als GUI Framework AngularUI und Bootstrap zu verwenden wird als gut erachtet, da es aktuell sehr verbreitet ist und schneller Support über eine aktive Community gewährleistet ist.
* **RISIKO (sehr gering)** Aktuell wird die Verwendung von AngularJS als Risiko eingestuft. Hintergrund ist der gerade stattfindende API Bruch durch die Entwicklung von AngularJS v2. Da jedoch die Migrationsstrategie öffentlich von der Community auf github entwickelt wird wird dieses Risiko als sehr gering eingestuft.
* **RISKO (gering)** Die Frontendlogik im Kern auf eine Realisierung mittels Javascript auszurichten wird als Risiko erkannt, da hier vermutlich eine weitere, komplett neue Toolchain zur Entwicklung des Frontends sowie völlig andere technische Skills als in der Backend Entwicklung benötigt werden. Das Risiko wird als gering (also deutlich mehr Aufwand als bei sehr gering) eingestuft und als Herausforderung an den Entwickler gesehen. 

## Internationalisierung
### Daten und Inhalte 
Diese werden bereits in der Datenhaltungsschicht internationalisiert abgelegt. Dabei ist es vermutlich nicht erforderlich jedes Datum zu internationalisieren, da dies manchmal für die Benutzung oder dem Nutzer keinen Sinn ergibt.
### Interaktionselemente der Anwendung
Sind auf jeden Fall zu internationalisieren, damit der Nutzer die Anwendung in seiner Sprache bedienen kann. 
### Nichtfunktionale Anforderungen
* Das Frontend soll möglichst wenig über die Sprachabhängigkeit wissen. Idealerweise kommen daher die Texte vom Server.
* Das REST-Backend welches Inhalte liefert sollte möglichst wenig über die Dialoge im Frontend und deren Internationalisierung wissen. Das widerspricht natürlich dem vorgenannten Punkt.
### Lösung
* Das REST-Backend welches Inhalte bereitstellt liefert im Bedarfsfall nur i18n KEYs aus. Der einzige hier vorstellbare Grund für diese KEYs sind Fehlermeldungen und Hinweistexte die auf Serverseite durch Exception Handling entstehen.
* Das REST-Backend welches Inhalte bereitstellt muss vom Frontend einen Sprachparameter entgegennehmen und zur Suche an die Datenhaltungsschicht weitergeben. Dies ist notwendig, damit beim Vorliegen internationalisierter Daten die korrekte Version ermittelt und an das Frontend ausgeliefert werden kann. z.B. Überschriften in Tabellen oder einzelne Zelleninhalte könnten aus Daten bestehen die internationalisiert vorliegen.
* Da das Inhalte liefernde Backend keine Internationalisierung vornehmen soll und das Frontend kein Wissen über die einzelnen Sprachausprägungen besitzen soll, bleibt als Lösung nur:
  * REST-Backend-Inhalte nimmt vom Frontend Sprachparameter entgegen und liefert für Hinweise und Meldungen i18n KEYS.
  * REST-Backend-i18n nimmt vom Frontend Sprachparameter entgegen und liefert JSON Dateien der ausgewählten Sprache mit Mappings zwischen i18n KEYs und Texten zurück an das Frontend.
  * Das Frontend enthält in den Oberflächenbeschreibungen keine konkreten Texte sondern nur i18n KEYs. Zusätzlich bekommt es vom REST-Backend-Inhalte i18n KEYs für Hinweise und Fehlermeldungen. Die konkreten Texte ermittelt das Frontend über das REST-Backend-i18n. Das Frontend weiss jedoch welche Sprache der Nutzer wünscht und speichert diese Information auch Session übergreifend falls dies fachlich gefordert ist. Ob die Information zur ausgewählten Sprache per Cookie oder im URL oder anders zu speichern ist hängt von fachlichen Vorgaben ab.

Obige Entscheidungen getroffen am 29.11.2015. 
  

## Einsprung und Wechsel zwischen Microservices
Wenn man davon ausgeht, dass das Frontend eine Menge statischer HTML Seiten mit JavaScript Libs und CSS sowie Bilder und anderen statischen Resourcen sind stellen sich 2 Fragen:
1. Wie erfolgt der Einsprung in den Microservice (http://host.com/service/index.html oder http://host.com/service)?
   Beim direkten Aufruf einer html Seite weiss diese nicht wo ihr Restservice liegt und kann diese nur aufrufen wenn in der Seite irgendwo der URL des Backends fest kodiert wurde. Oder der Seite muss ein Requestparameter mitgegeben werden. Doch wie kann dieser von der Seite selbst gelesen und verarbeitet werden? 
1. Wie kann ich zu einem anderen Microservice wechseln um z.B. den Autor eines Buches in der Adressverwaltung zu erfassen (mit Duplikaten Bearbeitung und allem Schnick Schnack) um nach Abschluss des Vorgangs automatisch per HATEOAS wieder in der Linkliste auf den ursprünglichen Microservice zurückgeleitet zu werden?

Beide Anwendungsfälle laufen auf das Grundproblem hinaus: Wie werte ich Requestparameter in einer statischen HTML Seite aus? Hierzu sind mehrere Ansätze bekannt:
* In AngularJS über Routing (http://stackoverflow.com/questions/20655877/angularjs-get-current-url-parameters)
  **TODO** Funktioniert Routing in einen anderen Microservice oder ist Routing auf einen Microservice begrenzt?
* In AngularJS über $location.search oder window.location.href (?window.location.hash?) (http://stackoverflow.com/questions/20655877/angularjs-get-current-url-parameters und http://stackoverflow.com/questions/406192/get-current-url-in-javascript?rq=1)
* Per Frames, URL, window.name oder Cookies (http://aktuell.de.selfhtml.org/artikel/javascript/wertuebergabe)
  Hierbei ist nur der URL Ansatz über **location.search** praktisch nutzbar. Die anderen scheiden aus wegen:
** window.name verändert den Fensternamen und erschwert damit den Integrationstest (selenium & Co. dürften Probleme bekommen).
** Frames waren schon vor Jahren nicht mehr gewünscht.
** Cookies können abgeschaltet sein bzw. bei Bestätigung der Abfrage ob Cookies erlaubt sind gehen die Parameter verloren. 
* HTML5 Local und Session Storage. Hier könnten Daten vor den Sprung zum nächsten Microservice abgelegt werden und auf der Zielseite wieder aus dem Storage ausgelesen werden.
  **TODO** Klären in wieweit die gleiche Aktion in zwei Browserfenstern zeitgleich ohne Störung durchgeführt werden kann. Wird der Store des einen Fensters durch das andere Fenster überschrieben? Was bedeutet im Falle von HATEOAS Browsersession? Wir dürfen keinen State weder auf Client noch auf Serverseite halten!

Um beim Einsprung in einen Microservice keine direkte HTML Seiten URL angeben zu müssen kann im Frontend auch ein Servlet installiert werden welches den eingehenden Request forwarded oder redirected. Ändert aber nix an der Parameterproblematik, der URL sieht nur schöner aus und verbirgt die Details der Implementierung. Dafür verliert man die schöne einfache statische Struktur und muss doch wieder einen ServletContainer aufbauen vs. einfachster Webserver ohne Servletunterstützung.

## Authentifizierung & Authorisierung
* Zur Authentifizierung muss sich der Nutzer beim Programm registrieren und dort einen öffentlichen PGP Key hinterlegen oder Daten (z.B. eMail) mit denen der Key aus dem Netz geholt werden kann.
* Zur Authorisierung wird an jeden URL eine UserId und ein Rechtestring angehangen aus dem die Anwendung die Identität und die aktuellen Rechte des Nutzers ermitteln kann. Die Sicherheitsinformationen sind zu zu erzeugen, dass einfache Angriffe wie nochmaliges Absetzen des kopierten Requests durch einen anderen Nutzer wirkungsvoll verhindert werden. 
* Jeder Nutzer muss sich beliebig oft registrieren und jeweils den gleichen öffentlichen PGP hinterlegen können. Die Anwendung muss es ermöglichen das der Nutzer auch Zugriff auf seine anderen (als Nutzer mit anderer id aber gleichen öffentlichen Schlüssel) Daten erhält. Hierzu sind die Eigenschaften des Public Key Verfahrens zu nutzen (Prüfung der Signatur mittels hinterlegten öffentlichen Schlüssel).
* Realisierung: Bei der Registrierung hinterlegt der Nutzer eine eMail Adresse über welche das Backend den öffentlichen PGP Schlüssel des Nutzers von einem Key Server erhalten kann. Beim Request wird an den URL die vom Backend vergebene UserId und ein Timepad (ala Google Authenticator) angehangen. Das Backend kann sich den Timepad aus den beim User hinterlegten Daten 2. Schlüsselspaares  (ein privater vom Server generierte Schlüssel und der öffentliche dazu) ebenso berechnen.

Entscheidungen getroffen am 07.11.2015 auf folgenden Grundlagen:
* Die Erzeugung eines Timepads scheint aktuell die einfachste Möglichkeit zu sein einen Schlüssel unabhängig von der Kontaktaufnahme zu anderen Zertifizierungsservern zu sein.
* Alle mir bekannten anderen Verfahren würden auf das Halten von Sessioninformationen hinauslaufen um den User sicher zu identifizieren und Kopien von Requests abzuschmettern. Auch soll der Nutzer ja in mehreren Browsern die Anwendung parallel bedienen können ohne die Workflows gegenseitig zu beeinflussen. 



## Testkonzept
### Modultest
* HTML Seiten werden mit HTMLUnit auf korrekte Struktur getestet
* JavaScript wird getetestet mit?
  * Jasmine
* Java wird getestet mit:
  * jUnit
  * TestNG 
=> (25.04.2015 Entscheidung für jUnit da keine Vorteile von TestNG gegenüber jUnit bekannt)
* REST API wird getestet mit - Entscheidung noch offen:
  * RAML - sehr strukturiert aber noch viele Integrationsprobleme. Mischt Konzepte aus Swagger und API Blueprint.
  * Swagger - scheint der stabilste Kandidat zu sein (Beispiel? https://github.com/kenshoo/swagger-validator)
  * WADL
  * I/O Docs
  * API Blueprint
  * http://www.bibsonomy.org/bibtex/294f86c9f252fc214081f44e7fdca5bed/funthomas424242

## Verteilte Konzepte
### Verteilung und Abgleich der Änderungen im Netz ###
10.04.2015 Damit die Clients autark arbeiten und dennoch Informationen an alle anderen Clients verteilen können ohne diese Clients oder deren Nutzer zu kennen, wird ein verteiltes Informationssystem benötigt an welches Aktualisierungen gesendet und über das Suchen ausgeführt werden können. Also zur Verteilung und zum Auslesen der Informationen wird ein Suchserver wie google benötigt. 

**Entscheidung:** Als Suchserver wird YaCy benutzt, da dieser sowohl lokal installiert als auch anonym mit Informationen versorgt und themenspezifisch konfiguriert werden kann.

### Verteilte Account- und Datenverwaltung ###
10.04.2015:
* Nutzer werden über eine UUID weltweit eindeutig identifiziert
* Die Daten jedes Nutzers liegen stets verschlüsselt in der Datenbank:
  * Zur Verschlüsselung wird ein Nutzerspezifischer Schlüssel verwendet. 
  * Der Schlüssel selbst stellt einen symmetrischen Schlüssel dar damit mittels symmetrischen Verfahren die Datenbankzugriffe möglichst schnell ausgeführt werden. 
  * Der symmetrische Schlüssel selber wird über ein asymetrisches Verfahren (PGP) verschlüsselt in der Datenbank abgelegt. 
  * Jeder Nutzer verfügt über einen spezifischen symmetrischen Schlüssel, so dass nach Knacken eines Schlüssels nicht die Daten aller Nutzer verloren sind. 
  * Der Login Mechanismus wird über ein asymmetrisches Verfahren abgehandelt.
 
---
[[01_EinfuehrungZiele]( 01_EinfuehrungZiele.md)]

---


