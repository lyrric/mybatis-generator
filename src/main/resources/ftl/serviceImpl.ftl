package ${generator.serviceImplPackage};

import ${generator.servicePackage}.${clazz.name}Service;
import ${generator.mapperPackage}.${clazz.name}Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* generated by mybatis-generator
*/
@Service
public class ${clazz.name}ServiceImpl implements ${clazz.name}Service  {

    @Resource
    private ${clazz.name}Mapper ${clazz.name?uncap_first}Mapper;
}