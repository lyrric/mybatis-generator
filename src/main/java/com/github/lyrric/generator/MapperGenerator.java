package com.github.lyrric.generator;

import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.config.EntityConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.entity.config.LombokConfig;
import com.github.lyrric.generator.entity.config.MapperConfig;
import com.github.lyrric.generator.enums.TemplateEnum;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang3.StringUtils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * entity生成器
 * @author wangxiaodong
 */
public class MapperGenerator extends BaseGenerator{

    /**
     * entity config
     */
    private final MapperConfig mapperConfig;

    private final Clazz clazz;

    private final GeneratorConfig generatorConfig;

    private final String MYBATIS_PLUS_MAPPER = "com.baomidou.mybatisplus.core.mapper.BaseMapper<T>";

    public MapperGenerator(Clazz clazz, GeneratorConfig generatorConfig, Configuration cfg) {
        super(cfg);
        this.mapperConfig = generatorConfig.getMapper();
        this.clazz = clazz;
        this.generatorConfig = generatorConfig;
    }

    @Override
    public void generate() throws IOException, TemplateException {
        if(!mapperConfig.getEnable()){
            return ;
        }
        String fileName = mapperConfig.getProject() + "/" + packageToPath(mapperConfig.getPackages()) +
                "/" + clazz.getName() + "Mapper.java";
        String fullGenericsClazzName = mapperConfig.getPackages() + "." + clazz.getName();
        //当指定了extendClass，又同时支持mybabtis-plus，优先支持自定义的extendClass
        if(StringUtils.isNotBlank(mapperConfig.getExtendClass())){
            //处理自定义extends
            convertExtendClassStr(mapperConfig.getExtendClass(), fullGenericsClazzName);
        }else if(generatorConfig.getMybatisPlus()){
            //处理mybabtis-plus支持
            convertExtendClassStr(MYBATIS_PLUS_MAPPER, fullGenericsClazzName);
        }

        Map<String ,Object> data = new HashMap<>(8);
        data.put("mapper", mapperConfig);
        data.put("generator", generatorConfig);
        data.put("clazz", clazz);
        data.put("dynamicImports", dynamicImports);
        data.put("extendClassStr", super.extendClassStr);
        render(TemplateEnum.MAPPER, fileName, data);
    }

}
