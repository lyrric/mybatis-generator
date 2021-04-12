package ${generator.entityPackage};

<#if mybatisPlus.enable>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
</#if>
<#if swagger.enable>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if lombok.enable>
    <#if lombok.data>
import lombok.Data;
    </#if>
    <#if lombok.noArgsConstructor>
import lombok.AllArgsConstructor;
    </#if>
    <#if lombok.allArgsConstructor>
import lombok.NoArgsConstructor;
    </#if>
    <#if lombok.builder>
import lombok.Builder;
    </#if>
</#if>
<#list clazz.dynamicImport as di>
import ${di};
</#list>

/**
* ${clazz.comment}
* @author: mybatis-generator
*/
@Data
<#if swagger.enable>
@ApiModel("${clazz.comment}")
</#if>
public class ${clazz.name} {
<#list clazz.fields as field>

    <#if swagger.enable>
    @ApiModelProperty(name = "${field.name}" , value = "${field.comment}")
    <#else>
    /**
    * ${field.comment}
    */
    </#if>
    <#if mybatisPlus.enable && field.primaryKey>
    @TableId(value = "${field.name}", type = IdType.AUTO)
    </#if>
	private ${field.javaType} ${field.name};
</#list>

<#list clazz.fields as field>
    public static final String ${field.columnName?upper_case} = "${field.columnName}";
</#list>
}
	