package ${serviceImpl.packages};

import ${service.packages}.${clazz.name}Service;
import ${mapper.packages}.${clazz.name}Mapper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

/**
* @author mybatis-generator
*/
@Service
public class ${clazz.name}ServiceImpl implements ${clazz.name}Service  {

    @Resource
    private ${clazz.name}Mapper ${clazz.name?uncap_first}Mapper;
}