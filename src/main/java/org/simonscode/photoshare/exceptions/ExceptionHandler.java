package org.simonscode.photoshare.exceptions;

import graphql.execution.DataFetcherExceptionHandler;
import graphql.execution.DataFetcherExceptionHandlerParameters;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

public class ExceptionHandler implements DataFetcherExceptionHandler {
    @Override
    public void accept(DataFetcherExceptionHandlerParameters handlerParameters) {
        Throwable exception = handlerParameters.getException();
        SourceLocation sourceLocation = handlerParameters.getField().getSourceLocation();
        ExecutionPath path = handlerParameters.getPath();
        ExceptionWhileDataFetching error = new ExceptionWhileDataFetching(path, exception.getCause().getCause(), sourceLocation);
        handlerParameters.getExecutionContext().addError(error, path);
    }
}
