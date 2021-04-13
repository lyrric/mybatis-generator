package com.github.lyrric.generator;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;

/**
 * @author wangxiaodong
 * @version 1.0
 * @date 2021/4/13 16:02
 */
@Mojo(name = "mybatis-generate")
public class MybatisMojo extends AbstractMojo {

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {
            new MyBatisGenerator().generate();
        } catch (Exception e) {
            getLog().error(e);
        }
    }
}
