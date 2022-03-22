<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${entityName}Mapper">
    <resultMap type="${packageName}.entity.dto.${entityName}DTO" id="${entityNameLow}ResultMap">
    <#list beans as bean>
        <#if bean.propName == bean.keyName>
          <id column="${bean.filedName}" property="${bean.propName}"/>
        <#else>
          <result column="${bean.filedName}" property="${bean.propName}"/>
        </#if>
    </#list>
    </resultMap>

</mapper>
