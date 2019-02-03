package tech.bts.cardgame.util;

import java.io.IOException;
import java.util.Map;

public class HandlebarsUtil {

    private Handlebars handlebars;

    static {
        TemplateLoader loader = new TemplateLoader();
        loader.setPrefix("/templates");
        loader.setSuffix(".hbs");
        handlebars = new Handlebars(loader);
    }

    public  String getTemplate(String templateName, Map<String, Object> values) throws IOException {
        return handlebars.compile(templateName).apply(values);
    }
}