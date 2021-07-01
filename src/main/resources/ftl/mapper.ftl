package ${mapper.packages};

import org.apache.ibatis.annotations.Mapper;
<#list dynamicImports as di>
import ${di};
</#list>

/**
* ${clazz.comment}
<#if generator.author?? >
* @author ${generator.author}
</#if>
*/
@Mapper
<#if extendClassStr??>
public interface ${clazz.name}Mapper extends ${extendClassStr}{
<#else>
public interface ${clazz.name}Mapper{
</#if>

}
