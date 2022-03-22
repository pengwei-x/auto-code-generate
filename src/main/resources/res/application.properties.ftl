spring.application.name=${projectName}
server.port=8080
spring.profiles.active=dev


logging.path=/data/logs/${projectName}
logging.config=classpath:logback-spring.xml
logging.level.${packageName}=debug
logging.level.org.springframework=WARN
logging.level.org.spring.springboot.dao=DEBUG
spring.jackson.serialization.write-dates-as-timestamps = true