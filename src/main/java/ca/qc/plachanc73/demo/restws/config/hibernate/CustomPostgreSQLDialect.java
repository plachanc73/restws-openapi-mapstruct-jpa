package ca.qc.plachanc73.demo.restws.config.hibernate;

import org.hibernate.boot.model.FunctionContributions;
import org.hibernate.dialect.PostgreSQLDialect;

@SuppressWarnings("unused")
public class CustomPostgreSQLDialect extends PostgreSQLDialect {
    public CustomPostgreSQLDialect() {
        super();
    }

    @Override
    public void initializeFunctionRegistry(FunctionContributions functionContributions) {
        super.initializeFunctionRegistry(functionContributions);
        var functionRegistry = functionContributions.getFunctionRegistry();
        functionRegistry.registerPattern(
                "tsvector_match",
                "(?1 @@ plainto_tsquery('simple', ?2))"
        );
        functionRegistry.registerPattern(
                "ilike",
                "(?1 ilike ?2)"
        );
    }
}