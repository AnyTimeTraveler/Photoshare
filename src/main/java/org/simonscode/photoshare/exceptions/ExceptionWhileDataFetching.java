package org.simonscode.photoshare.exceptions;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.GraphqlErrorHelper;
import graphql.execution.ExecutionPath;
import graphql.language.SourceLocation;

import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ExceptionWhileDataFetching implements GraphQLError {

    private final String message;
    private final Throwable exception;
    private final List<SourceLocation> locations;

    public ExceptionWhileDataFetching(ExecutionPath path, Throwable exception, SourceLocation sourceLocation) {
        this.exception = exception;
        this.locations = Collections.singletonList(sourceLocation);
        this.message = mkMessage(path, exception);
    }

    private String mkMessage(ExecutionPath path, Throwable exception) {
        return String.format("Exception while fetching data (%s) : %s", path, exception.getMessage());
    }


    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public List<SourceLocation> getLocations() {
        return locations;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return null;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.ValidationError;
    }

    @Override
    public String toString() {
        return "ExceptionWhileDataFetching{" +
                "exception=" + exception +
                "locations=" + locations +
                '}';
    }

    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    @Override
    public boolean equals(Object o) {
        return GraphqlErrorHelper.equals(this, o);
    }

    @Override
    public int hashCode() {
        return GraphqlErrorHelper.hashCode(this);
    }
}
