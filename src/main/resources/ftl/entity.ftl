package ${entity.packages};

<#if generator.mybatisPlus>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
</#if>
<#if generator.swagger>
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
</#if>
<#if lombok.enable>
    <#if lombok.data>
import lombok.Data;
import lombok.EqualsAndHashCode;
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
<#list dynamicImports as di>
import ${di};
</#list>

/**
* ${clazz.comment}
<#if generator.author?? >
* @author ${generator.author}
</#if>
*/
<#if lombok.enable>
    <#if lombok.data>
@Data
@EqualsAndHashCode(callSuper = false)
    </#if>
    <#if lombok.noArgsConstructor>
@AllArgsConstructor
    </#if>
    <#if lombok.allArgsConstructor>
@NoArgsConstructor
    </#if>
    <#if lombok.builder>
@Builder
    </#if>
</#if>
<#if generator.swagger>
@ApiModel("${clazz.comment}")
</#if>
<#if extendClassStr??>
public class ${clazz.name} extends ${extendClassStr}{
<#else>
public class ${clazz.name}{
</#if>
<#list clazz.fields as field>

    <#if generator.swagger>
    @ApiModelProperty(name = "${field.name}" , value = "${field.comment}")
    <#else>
    /**
    * ${field.comment}
    */
    </#if>
    <#if generator.mybatisPlus && field.primaryKey >
    <#if field.autoIncrement>
     @TableId(value = "${field.name}", type = IdType.AUTO)
    <#else>
     @TableId(value = "${field.name}", type = IdType.NONE)
    </#if>
    </#if>
	private ${field.javaType} ${field.name};
</#list>

<#list clazz.fields as field>
    public static final String ${field.columnName?upper_case} = "${field.columnName}";
</#list>

<#if !lombok.enable || !lombok.data>
    <#list clazz.fields as field>
    public ${field.javaType} get${field.name?cap_first}(){
        return ${field.name};
    }
    public void set${field.name?cap_first}(${field.javaType} ${field.name}){
        this.${field.name} = ${field.name};
    }
    </#list>
</#if>
}
	