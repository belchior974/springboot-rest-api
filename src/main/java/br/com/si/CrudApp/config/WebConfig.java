package br.com.si.CrudApp.config;

import br.com.si.CrudApp.converters.YamlConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.print.attribute.standard.Media;
import java.util.List;

@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {
//    content negotiation via url parameter
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//       configurer.favorParameter(true)
//               .parameterName("media-type")
//               .ignoreAcceptHeader(true)
//               .useRegisteredExtensionsOnly(false)
//               .defaultContentType(MediaType.APPLICATION_JSON)
//               .mediaType("json", MediaType.APPLICATION_JSON)
//               .mediaType("xml", MediaType.APPLICATION_XML);
//    }

    //..create a static attribute to define YAML type
    private static MediaType APPLICATION_YAML = MediaType.valueOf("application/x-yaml");

    //..add the YAML converter to converters list
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
       converters.add(new YamlConverter());
    }

    //..content negotiation via request header (Accept)
    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(false)
                .ignoreAcceptHeader(false)
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON)
                .mediaType("json", MediaType.APPLICATION_JSON)
                .mediaType("xml", MediaType.APPLICATION_XML)
                .mediaType("x-yaml", APPLICATION_YAML);
    }

//    CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:3000/");
    }
}
