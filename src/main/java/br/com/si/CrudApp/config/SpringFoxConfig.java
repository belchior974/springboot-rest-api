package br.com.si.CrudApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.si.CrudApp"))
                .paths(PathSelectors.any())
                .build().apiInfo(this.apiInfo());
    }

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Documentação Oficial",
                "Documentação da Aplicação de Gerenciamento de Carros",
                "V1",
                "http://www.fatecjales.edu.br",
                new Contact(
                        "Jorge Luís Gregório",
                        "http://www.jlgregorio.com.br",
                        "jorge.gregorio@fatec.sp.gov.br"
                ),
                "Licença Padrão", "http://www.fatecjales.edu.br",
                Collections.emptyList()
        );
    }

}
