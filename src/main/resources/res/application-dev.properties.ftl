# datasource
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
#spring.datasource.url=jdbc:mysql://localhost:3306/gd_medical_dev?useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT%2B8
spring.datasource.url=${url}
spring.datasource.username=${user}
spring.datasource.password=${pwd}
spring.datasource.hikari.connection-test-query=SELECT 1 FROM DUAL
spring.datasource.hikari.minimum-idle=1
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.pool-name=webPool
spring.datasource.hikari.max-lifetime=1800

# jwt
auth.jwt.secret=dev

# mybatis-plus
mybatis-plus.mapper-locations=classpath:mapper/**/*.xml
mybatis-plus.type-aliases-package=${packageName}.entity
mapper.not-empty=false
mapper.identity=MYSQL

# pageHelper
pagehelper.helperDialect=mysql
pagehelper.reasonable=false
pagehelper.supportMethodsArguments=true
pagehelper.params=count=countSql




