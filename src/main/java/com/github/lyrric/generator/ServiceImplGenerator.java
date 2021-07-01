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
import java.util.Map;

/**
 * entity生成器
 * @author wangxiaodong
 */
public class ServiceImplGenerator extends BaseGenerator{

    /**
     * entity config
     */
    private BaseConfig serviceImplConfig;

    private final String mapperPackage;

    private final String servicePackage;

    private final String author;

    private final Clazz clazz;

    public ServiceImplGenerator(Clazz clazz, GeneratorConfig generatorConfig, Configuration cfg) {
        super(cfg);
        this.clazz = clazz;
        mapperPackage = generatorConfig.getMapper().getPackages();
        servicePackage = generatorConfig.getService().getPackages();
        serviceImplConfig = generatorConfig.getServiceImpl();
        author = generatorConfig.getAuthor();
    }

    @Override
    public void generate() throws IOException, TemplateException {
        if(!serviceImplConfig.getEnable()){
            return ;
        }
        String fileName = serviceImplConfig.getProject() + "/" + packageToPath(serviceImplConfig.getPackages()) +
                "/" + clazz.getName() + "ServiceImpl.java";
        Map<String ,Object> data = new HashMap<>(5);
        data.put("serviceImpl", serviceImplConfig);
        data.put("servicePackage", servicePackage);
        data.put("mapperPackage", mapperPackage);
        data.put("author", author);
        data.put("clazz", clazz);
        render(TemplateEnum.SERVICE_IMPL, fileName, data);
    }

}
