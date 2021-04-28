package ${mapper.packages};

<#if generator.mybatisPlus>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
</#if>
import org.apache.ibatis.annotations.Mapper;
import ${entity.packages}.${clazz.name};
<#list mapper.dynamicImports as di>
import ${di};
</#list>

/**
* ${clazz.comment}
<#if generator.author?? >
* @author ${generator.author}
</#if>
*/
@Mapper
<#if extendClass??>
public interface ${clazz.name}Mapper extends ${extendClass}{
<#else>
public interface ${clazz.name}Mapper extends BaseMapper<${clazz.name}> {
</#if>

}
