package config;

import freemarker.template.Configuration;

public class FreemarkerConfig {

    private static final String FREEMARKER_TEMPLATES = "freemarker-templates";

    // So that another config instance can't be created
    private FreemarkerConfig() {
    }

    public static Configuration getConfig() {
        Configuration config = new Configuration(Configuration.VERSION_2_3_34);
        config.setDefaultEncoding("UTF-8");
        config.setClassLoaderForTemplateLoading(FreemarkerConfig.class.getClassLoader(), FREEMARKER_TEMPLATES);
        return config;
    }
}
