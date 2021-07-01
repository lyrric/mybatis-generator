package com.github.lyrric.generator;

import com.github.lyrric.generator.entity.Clazz;
import com.github.lyrric.generator.entity.config.BaseConfig;
import com.github.lyrric.generator.entity.config.GeneratorConfig;
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
public class XmlGenerator extends BaseGenerator{

    /**
     * entity config
     */
    private final BaseConfig xmlConfig;

    private final String mapperPackage;
    private final String entityPackage;

    private final Clazz clazz;

    public XmlGenerator(Clazz clazz,GeneratorConfig generatorConfig, Configuration cfg) {
        super(cfg);
       this.mapperPackage = generatorConfig.getMapper().getPackages();
       this.entityPackage = generatorConfig.getEntity().getPackages();
       this.clazz = clazz;
       this.xmlConfig = generatorConfig.getXml();
    }

    @Override
    public void generate() throws IOException, TemplateException {
        if(!xmlConfig.getEnable()){
            return ;
        }
        String fileName = xmlConfig.getProject() + "/" + packageToPath(xmlConfig.getPackages()) +
                "/" + clazz.getName() + "Mapper.xml";
        Map<String ,Object> data = new HashMap<>(5);
        data.put("xml", xmlConfig);
        data.put("mapperPackage", mapperPackage);
        data.put("entityPackage", entityPackage);
        data.put("clazz", clazz);
        render(TemplateEnum.XML, fileName, data);
    }

}
