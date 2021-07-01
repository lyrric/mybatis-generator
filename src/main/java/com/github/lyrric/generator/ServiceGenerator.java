package com.github.lyrric.generator;

import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.config.BaseConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
import com.github.lyrric.generator.entity.config.MapperConfig;
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
public class ServiceGenerator extends BaseGenerator{

    /**
     * entity config
     */
    private final BaseConfig serviceConfig;

    private final Clazz clazz;

    private final GeneratorConfig generatorConfig;

    public ServiceGenerator(Clazz clazz, GeneratorConfig generatorConfig, Configuration cfg) {
        super(cfg);
        this.serviceConfig = generatorConfig.getService();
        this.clazz = clazz;
        this.generatorConfig = generatorConfig;
    }

    @Override
    public void generate() throws IOException, TemplateException {
        if(!serviceConfig.getEnable()){
            return ;
        }
        String fileName = serviceConfig.getProject() + "/" + packageToPath(serviceConfig.getPackages()) +
                "/" + clazz.getName() + "Service.java";
        Map<String ,Object> data = new HashMap<>();
        data.put("service", serviceConfig);
        data.put("generator", generatorConfig);
        data.put("clazz", clazz);
        render(TemplateEnum.SERVICE, fileName, data);
    }

}
