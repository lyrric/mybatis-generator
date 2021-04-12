package ${generator.entityPackage};

<#if mybatisPlus.enable>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
</#if>
import org.apache.ibatis.annotations.Mapper;
import ${generator.entityPackage}.${clazz.name};
/**
* @author: mybatis-generator
*/
@Mapper
public interface ${clazz.name}Mapper extends BaseMapper<${clazz.name}> {
	
}
	