package ${packageName}.web.command;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * @author codeGenerate
 */
@Data
public class ${entityName}CreateCommand implements Serializable{

    private static final long serialVersionUID = 1L;
<#list beans as bean>
    <#if bean.filedName == 'fd_id' || bean.filedName == 'fd_deleted' || bean.filedName == 'fd_createDate' || bean.filedName == 'fd_updateDate' || bean.filedName == 'fd_reserved1' || bean.filedName == 'fd_reserved2'>
        <#continue>
    <#elseif bean.type == 'String'>

    @NotBlank(message = "${bean.remarks}不能为空")
    <#else>

    @NotNull(message = "${bean.remarks}不能为空")
    </#if>
    @ApiModelProperty(value = "${bean.remarks}")
    ${bean.accessAuth} ${bean.type} ${bean.propName};
</#list>

}
