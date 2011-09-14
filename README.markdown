
* #1 Customize your dev/ application.properties
jdbc.driver_class = com.mysql.jdbc.Driver
jdbc.url = jdbc:mysql://localhost/fishreport?useUnicode=true&characterEncoding=utf8
jdbc.username = fishy
jdbc.password = finns1sj0n


* #2 Add your launch parameters:_
-Xmx2048m -Xss192k -XX:MaxPermSize=1024m -Dprofile=dev -Duser.language=sv -Duser.region=SE -Dinsight.enabled=false


* TODO: 
	1, Lägg till pets i backoffice
	2, Lägg till system-screen i backoffice
	3, Se till att frontoffice edit går