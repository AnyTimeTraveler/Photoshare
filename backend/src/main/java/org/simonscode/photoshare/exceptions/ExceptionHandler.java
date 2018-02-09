package org.simonscode.photoshare.exceptions;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public void accept(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        log.error("GraphQLError", exception);
        while (exception.getCause() != null) {
            exception = exception.getCause();
            log.error("GraphQLError", exception);
        }
        SourceLocation sourceLocation = handlerParameters.getField().getSourceLocation();
        ExecutionPath path = handlerParameters.getPath();
        ExceptionWhileDataFetching error = new ExceptionWhileDataFetching(path, exception, sourceLocation);
        handlerParameters.getExecutionContext().addError(error, path);
    }
}
