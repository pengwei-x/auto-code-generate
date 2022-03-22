package ${packageName}.web.assembler;

import ${packageName}.web.command.${entityName}CreateCommand;
import ${packageName}.web.command.${entityName}UpdateCommand;
import ${packageName}.entity.po.${entityName};

import java.util.Date;

/**
 * @author codeGenerate
 */
public class ${entityName}Assembler{
    public static ${entityName} to${entityName}(${entityName}CreateCommand command){
        Date now = new Date();

        return new ${entityName}()
        <#list beans as bean>
            <#if bean.filedName == 'fd_id' || bean.filedName == 'fd_deleted' || bean.filedName == 'fd_createDate' || bean.filedName == 'fd_updateDate' || bean.filedName == 'fd_reserved1' || bean.filedName == 'fd_reserved2'>
                <#continue>
            </#if>
            // ${bean.remarks}
            .set${bean.propNameUP}(command.get${bean.propNameUP}())
        </#list>
            // 创建时间
            .setCreateDate(now)
            // 最后更新时间
            .setUpdateDate(now);
    }

    public static ${entityName} to${entityName}(${entityName}UpdateCommand command){
        Date now = new Date();

        return new ${entityName}()
        <#list beans as bean>
            <#if bean.filedName == 'fd_deleted' || bean.filedName == 'fd_createDate' || bean.filedName == 'fd_updateDate' || bean.filedName == 'fd_reserved1' || bean.filedName == 'fd_reserved2'>
                <#continue>
            </#if>
            // ${bean.remarks}
            .set${bean.propNameUP}(command.get${bean.propNameUP}())
        </#list>
            // 最后更新时间
            .setUpdateDate(now);
    }
}
