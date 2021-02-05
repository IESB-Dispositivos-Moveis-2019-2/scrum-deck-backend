package br.iesb.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.Scopes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public Info info() {
        return new Info()
            .version("1.0.0")
            .title("IESB - Especialização em Aplicativos Móveis - Scrum Deck Backend")
            .description("Backend do aplicativo Scrum Deck - Fins Educacionais")
            .contact(new Contact().name("Prof. Pedro Henrique").email("pedro.figueiredo@iesb.br"))
            .license(new License().name("MIT").url("https://opensource.org/licenses/MIT"))
            .termsOfService("https://www.iesb.br/pos/curso/especializacao-em-aplicativos-moveis");
    }

    @Bean
    @ConditionalOnMissingBean
    public Scopes scopes() {
        return new Scopes();
    }

    @Bean
    @ConditionalOnMissingBean
    public Components components() {
        return new Components();
    }

    @Bean
    @ConditionalOnMissingBean
    public OpenAPI customOpenAPI(@Autowired Info info, @Autowired Components components) {
        return new OpenAPI()
            .components(components)
            .info(info);
    }

}
