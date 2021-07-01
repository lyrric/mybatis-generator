package com.github.lyrric.generator;

import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.config.EntityConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.entity.config.LombokConfig;
import com.github.lyrric.generator.enums.TemplateEnum;
import freemarker.template.Configuration;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * entity生成器
 * @author wangxiaodong
 */
public class EntityGenerator extends BaseGenerator{

    /**
     * entity config
     */
    private EntityConfig entityConfig;

    private final Clazz clazz;

    private final GeneratorConfig generatorConfig;

    private final LombokConfig lombokConfig;

    public EntityGenerator(Clazz clazz, GeneratorConfig generatorConfig, Configuration cfg) {
        super(cfg);
        this.entityConfig = generatorConfig.getEntity();
        this.clazz = clazz;
        this.generatorConfig = generatorConfig;
        this.lombokConfig = generatorConfig.getLombok();
    }

    @Override
    public void generate() throws IOException, TemplateException {
        if(!entityConfig.getEnable()){
            return ;
        }
        String fileName = entityConfig.getProject() + "/" + packageToPath(entityConfig.getPackages()) +
                "/" + clazz.getName() + ".java";

        String fullClassName = entityConfig.getPackages() + "." + clazz.getName();
        convertExtendClassStr(entityConfig.getExtendClass(), fullClassName);
        Set<String> dynamicImports = new HashSet<>(clazz.getDynamicImport());
        dynamicImports.addAll(super.dynamicImports);
        //去除自身entity的import
        dynamicImports.remove(entityConfig.getPackages() + "." + clazz.getName());
        Map<String ,Object> data = new HashMap<>(8);
        data.put("entity", entityConfig);
        data.put("generator", generatorConfig);
        data.put("lombok", lombokConfig);
        data.put("clazz", clazz);
        data.put("dynamicImports", dynamicImports);
        data.put("extendClassStr", extendClassStr);
        render(TemplateEnum.ENTITY, fileName, data);
    }


}
