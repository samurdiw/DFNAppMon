set CLASSPATH=%CLASSPATH%;.\lib\dfn\*
java -classpath %CLASSPATH% -Dlog4j.configurationFile=log4j2.xml -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=7778 -Xms512m -Xmx1024m -jar MonAgent-1.0.jar