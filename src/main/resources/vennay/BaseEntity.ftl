package ${packageName}.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author codeGenerate
 */
@Data
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BaseEntity {
    /**
     * 逻辑删除是为了方便数据恢复和保护数据本身价值等等的一种方案，但实际就是删除
     * 如果你需要再查出来就不应使用逻辑删除，而是以一个状态去表示
     * 效果：使用mp自带方法删除和查找都会附带逻辑删除功能 (自己写的xml不会)
     */
    @TableLogic
    @TableField("fd_deleted")
    private Integer deleted;
    /**
     * 更新时间
     */
    @TableField("fd_updateDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateDate;
    /**
     * 修改时间
     */
    @TableField("fd_createDate")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createDate;
    /**
     * 预留字段1
     */
    @TableField("fd_reserved1")
    private String reserved1;
    /**
     * 预留字段2
     */
    @TableField("fd_reserved2")
    private String reserved2;
}