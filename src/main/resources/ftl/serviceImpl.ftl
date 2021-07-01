package ${serviceImpl.packages};

import ${servicePackage}.${clazz.name}Service;
import ${mapperPackage}.${clazz.name}Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* ${clazz.comment}
<#if author?? >
* @author ${author}
</#if>
*/
@Service
public class ${clazz.name}ServiceImpl implements ${clazz.name}Service  {

    @Resource
    private ${clazz.name}Mapper ${clazz.name?uncap_first}Mapper;
}