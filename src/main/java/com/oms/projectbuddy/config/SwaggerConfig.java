package com.oms.projectbuddy.config;//package com.oms.projectbuddy.config;
//
//import com.fasterxml.classmate.ResolvedType;
//import com.fasterxml.classmate.TypeResolver;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.ParameterBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.schema.ModelReference;
//import springfox.documentation.schema.ResolvedTypes;
//import springfox.documentation.schema.TypeNameExtractor;
//import springfox.documentation.service.*;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spi.schema.TypeNameProviderPlugin;
//import springfox.documentation.spi.schema.contexts.ModelContext;
//import springfox.documentation.spi.service.OperationBuilderPlugin;
//import springfox.documentation.spi.service.contexts.OperationContext;
//import springfox.documentation.spi.service.contexts.ParameterContext;
//import springfox.documentation.spi.service.contexts.SecurityContext;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger.common.SwaggerPluginSupport;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.awt.print.Pageable;
//import java.util.Collections;
//import java.util.List;
//
//import static com.google.common.collect.Lists.newArrayList;
//import static springfox.documentation.spi.schema.contexts.ModelContext.inputParam;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//
//    private static final String TITLE = "Edufront Education AI";
//
//    private static final String DESCRIPTION = "API Documentation";
//
//    private static final String VERSION = "0.0.1";
//
//    @Bean
//    public Docket api() {
//
//        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
//                                                      .paths(PathSelectors.any()).build().enable(true).apiInfo(info())
//                                                      .securityContexts(Collections.singletonList(securityContext()))
//                                                      .securitySchemes(Collections.singletonList(apiKey()));
//    }
//
//    private ApiInfo info() {
//
//        return new ApiInfoBuilder().title(TITLE).description(DESCRIPTION).version(VERSION).build();
//    }
//
//    private SecurityContext securityContext() {
//
//        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths(PathSelectors.ant("/api/**"))
//                              .build();
//    }
//
//    private List<SecurityReference> defaultAuth() {
//
//        return newArrayList(new SecurityReference("JWT", new AuthorizationScope[] {}));
//    }
//
//    private SecurityScheme apiKey() {
//
//        return new ApiKey("JWT", "Authorization", "header");
//    }
//    @Component
//    @Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER)
//    public static class CustomTypeNameProvider implements TypeNameProviderPlugin {
//        @Override
//        public String nameFor(Class<?> type) {
//            String fullName = type.getName();
//            return fullName.substring(fullName.lastIndexOf(".") + 1);
//        }
//
//        @Override
//        public boolean supports(DocumentationType delimiter) {
//            return true;
//        }
//    }
//    @Component
//    @Order(Ordered.LOWEST_PRECEDENCE)
//    public class OperationPageableParameterReader implements OperationBuilderPlugin {
//
//        private final TypeNameExtractor nameExtractor;
//
//        private final TypeResolver resolver;
//
//        private final ResolvedType pageableType;
//
//        @Autowired
//        public OperationPageableParameterReader(TypeNameExtractor nameExtractor, TypeResolver resolver) {
//
//            this.nameExtractor = nameExtractor;
//            this.resolver = resolver;
//            pageableType = resolver.resolve(Pageable.class);
//        }
//
//        @Override
//        public void apply(OperationContext context) {
//
//            List<ResolvedMethodParameter> methodParameters = context.getParameters();
//            List<Parameter> parameters = newArrayList();
//
//            for (ResolvedMethodParameter methodParameter : methodParameters) {
//                ResolvedType resolvedType = methodParameter.getParameterType();
//
//                if (pageableType.equals(resolvedType)) {
//                    ParameterContext parameterContext = new ParameterContext(methodParameter, new ParameterBuilder(),
//                                                                             context.getDocumentationContext(),
//                                                                             context.getGenericsNamingStrategy(),
//                                                                             context);
//                    com.google.common.base.Function<ResolvedType,
//                                                    ModelReference> factory = createModelRefFactory(parameterContext);
//
//                    ModelReference intModel = factory.apply(resolver.resolve(Integer.TYPE));
//                    ModelReference stringModel = factory.apply(resolver.resolve(List.class, String.class));
//
//                    parameters.add(new ParameterBuilder().parameterType("query").name("page").modelRef(intModel)
//                                                         .description("Results page you want to retrieve (0..N)")
//                                                         .build());
//                    parameters.add(new ParameterBuilder().parameterType("query").name("size").modelRef(intModel)
//                                                         .description("Number of records per page").build());
//                    parameters.add(new ParameterBuilder().parameterType("query").name("sort").modelRef(stringModel)
//                                                         .allowMultiple(true)
//                                                         .description("Sorting criteria in the format: property(,asc|desc). "
//                                                                 + "Default sort order is ascending. "
//                                                                 + "Multiple sort criteria are supported.")
//                                                         .build());
//                    context.operationBuilder().parameters(parameters);
//                }
//            }
//        }
//
//        @Override
//        public boolean supports(DocumentationType delimiter) {
//
//            return true;
//        }
//
//        private com.google.common.base.Function<ResolvedType, ModelReference>
//                createModelRefFactory(ParameterContext context) {
//
//            ModelContext modelContext = inputParam(context.getGroupName(),
//                                                   context.resolvedMethodParameter().getParameterType(),
//                                                   context.getDocumentationType(), context.getAlternateTypeProvider(),
//                                                   context.getGenericNamingStrategy(),
//                                                   context.getIgnorableParameterTypes());
//            return ResolvedTypes.modelRefFactory(modelContext, nameExtractor);
//        }
//    }
//}
