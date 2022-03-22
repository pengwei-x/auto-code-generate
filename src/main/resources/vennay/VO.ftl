package ${packageName}.entity.vo;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;
import java.io.Serializable;

/**
* @author pengwei
*/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${entityName} implements Serializable{

private static final long serialVersionUID = 1L;
<#list beans as bean>

<#--    /** ${bean.remarks}*/-->
    @ApiModelProperty(value = "${bean.remarks}")
    ${bean.accessAuth} ${bean.type} ${bean.propName};
</#list>

}
