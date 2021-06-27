package br.com.codered.dio.person.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket personApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.codered.dio"))
                .paths(regex("/api/v1.*"))
                .build();
    }

    private ApiInfoBuilder informacoesApi() {

        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();

        apiInfoBuilder.title("Person API");
        apiInfoBuilder.description("API simples de pessoas para o Lab da DIO");
        apiInfoBuilder.version("1.0");
        apiInfoBuilder.termsOfServiceUrl("Termo de uso: para fins acadêmicos");
        apiInfoBuilder.license("Licença - MIT");
        apiInfoBuilder.licenseUrl("https://www.codered.com.br");
        apiInfoBuilder.contact(this.contato());

        return apiInfoBuilder;

    }

    private Contact contato() {

        return new Contact(
                "Mauricio Rother",
                "https://www.codered.com.br",
                "mauricio@codered.com.br");
    }
}

