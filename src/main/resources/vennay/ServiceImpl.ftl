package ${packageName}.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${packageName}.entity.po.${entityName};
import ${packageName}.mapper.${entityName}Mapper;
import ${packageName}.service.${entityName}Service;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pengwei
 */

@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class ${entityName}ServiceImpl extends ServiceImpl<${entityName}Mapper, ${entityName}PO> implements ${entityName}Service {
}
