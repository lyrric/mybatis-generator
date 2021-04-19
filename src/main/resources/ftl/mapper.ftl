package ${mapper.packages};

<#if mybatisPlus>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
</#if>
import org.apache.ibatis.annotations.Mapper;
import ${entity.packages}.${clazz.name};

/**
* @author mybatis-generator
*/
@Mapper
public interface ${clazz.name}Mapper extends BaseMapper<${clazz.name}> {
	
}
	