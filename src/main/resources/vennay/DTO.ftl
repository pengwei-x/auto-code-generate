package ${packageName}.entity.dto;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;
import java.util.Date;

import java.io.Serializable;

/**
 * @author codeGenerate
 */
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ${entityName}DTO implements Serializable{

    private static final long serialVersionUID = 1L;
<#list beans as bean>

    /** ${bean.remarks}*/
    ${bean.accessAuth} ${bean.type} ${bean.propName};
</#list>

}
