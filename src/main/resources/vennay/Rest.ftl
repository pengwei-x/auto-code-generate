package ${packageName}.web.rest;

import com.yunzhicloud.core.domain.ResultDTO;
import ${packageName}.common.*;
import ${packageName}.web.assembler.${entityName}Assembler;
import ${packageName}.web.command.${entityName}CreateCommand;
import ${packageName}.web.command.${entityName}UpdateCommand;
import ${packageName}.entity.po.${entityName};
import ${packageName}.entity.vo.${entityName}VO;
import ${packageName}.service.${entityName}Service;
import lombok.RequiredArgsConstructor;
import cn.hutool.core.convert.Convert;
import com.yunzhicloud.web.base.BaseController;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author pengwei
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/${moduleName}/${entityNameLow}")
@Api(value = "${entityNameLow}Service")
public class ${entityName}Rest extends BaseController {

    private final ${entityName}Service ${entityNameLow}Service;

    @PostMapping
    @ApiOperation(value = "add ${entityName}", notes = "接口描述")
    public ResultDTO<Boolean> add(@RequestBody @Valid ${entityName}CreateCommand command) {
        ${entityName} entity = ${entityName}Assembler.to${entityName}(command);
        return success(${entityNameLow}Service.save(entity));
    }

    @DeleteMapping
    @ApiOperation(value = "delete ${entityName} by id", notes = "接口描述")
    public ResultDTO<Boolean> delete(@RequestBody CommonDTO commonDTO){
        boolean aBoolean = ${entityNameLow}Service.removeByIds(Arrays.asList(commonDTO.getIds()));
        return success(aBoolean);
    }

    @PutMapping
    @ApiOperation(value = "update ${entityName} by id", notes = "接口描述")
    public ResultDTO<Boolean> update(@RequestBody @Valid ${entityName}UpdateCommand command) {
        ${entityName} entity = ${entityName}Assembler.to${entityName}(command);
        return success(${entityNameLow}Service.updateById(entity));
    }

    @GetMapping
    @ApiOperation(value = "get ${entityName} detail by id", notes = "接口描述")
    public ResultDTO<${entityName}VO> detail(@RequestParam Integer id) {
        ${entityName} ${entityNameLow} = ${entityNameLow}Service.getById(id);
        return success(Convert.convert(${entityName}VO.class, ${entityNameLow}));
    }

    @GetMapping("page")
    @ApiOperation(value = "get ${entityName} page", notes = "接口描述")
    public ResultDTO<IPage<${entityName}VO>> page(
            @RequestParam(defaultValue = "1") Integer pageNum,
            @RequestParam(defaultValue = "10")Integer pageSize){

        Page<${entityName}> page = new Page<>(pageNum, pageSize);

        Map<String, Object> map = new HashMap<>(4);
        IPage<${entityName}> ${entityNameLow}Page = ${entityNameLow}Service.page(page);
        IPage<${entityName}VO> convert = Convert.convert(IPage.class, ${entityNameLow}Page);

        return success(convert);
    }
}