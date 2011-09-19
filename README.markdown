
#TODO: #
 1. Lägg till pets i backoffice
 2. Lägg till system-screen i backoffice
 3. Se till att frontoffice edit går

* * *

##1 * Customize your dev/ application.properties##
<code>
	jdbc.driver_class = com.mysql.jdbc.Driver
	jdbc.url = jdbc:mysql://localhost/fishreport?useUnicode=true&characterEncoding=utf8
	jdbc.username = fishy
	jdbc.password =
</code>


##2 * Add your launch parameters:##
<code>
	-Xmx2048m -Xss192k -XX:MaxPermSize=1024m -Dprofile=dev -Duser.language=sv -Duser.region=SE -Dinsight.enabled=false
</code>

##3 * Creat database
Set encoding to utf8;