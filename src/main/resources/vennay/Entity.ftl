package ${packageName}.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import java.io.Serializable;
import java.util.Date;
import lombok.experimental.Accessors;

/**
 * @author pengwei
 */
@Data
@Accessors(chain = true)
@TableName("${tableName}")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${entityName}PO implements Serializable{

    private static final long serialVersionUID = 1L;
<#list beans as bean>
    <#if bean.filedName == "fd_deleted" >

    /** ${bean.remarks}*/
    @TableLogic
    @TableField("${bean.filedName}")
    ${bean.accessAuth} ${bean.type} ${bean.propName};
    <#elseif bean.propName != bean.keyName>

    /** ${bean.remarks}*/
    @TableField("${bean.filedName}")
    ${bean.accessAuth} ${bean.type} ${bean.propName};
    <#elseif entityKeyFieldName != "fd_id">

    /** ${bean.remarks}*/
    @TableField("${bean.filedName}")
    ${bean.accessAuth} ${bean.type} ${bean.propName};
    <#else>

    /** ${bean.remarks}*/
    @TableId(type = IdType.AUTO,value = "fd_id")
    private Integer id;
    </#if>
</#list>

}
