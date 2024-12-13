package com.mtu.codeiumproject.controller.exception;

import com.mtu.codeiumproject.service.exception.HouseholdException;
import com.mtu.codeiumproject.service.exception.HouseholdNotFoundException;
import com.mtu.codeiumproject.service.exception.PetException;
import com.mtu.codeiumproject.service.exception.PetNotFoundException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.graphql.execution.ErrorType;
import org.springframework.stereotype.Component;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {
    @Override
    protected GraphQLError resolveToSingleError(Throwable exception, DataFetchingEnvironment env) {
        if (exception instanceof HouseholdException) {
            return handleHouseholdException((HouseholdException) exception);
        } else if (exception instanceof HouseholdNotFoundException) {
            return handleHouseholdNotFoundException((HouseholdNotFoundException) exception);
        } else if (exception instanceof PetException) {
            return handlePetException((PetException) exception);
        } else if (exception instanceof PetNotFoundException) {
            return handlePetNotFoundException((PetNotFoundException) exception);
        } else {
            return handleGenericException(exception);
        }
    }

    private GraphQLError handleGenericException(Throwable exception) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.INTERNAL_ERROR)
                .message("Unknown error occurred.\n" + exception.getMessage())
                .build();
    }

    private GraphQLError handleHouseholdException(HouseholdException exception) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
    }


    private GraphQLError handleHouseholdNotFoundException(HouseholdNotFoundException exception) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(exception.getMessage())
                .build();
    }

    private GraphQLError handlePetException(PetException exception) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.BAD_REQUEST)
                .message(exception.getMessage())
                .build();
    }

    private GraphQLError handlePetNotFoundException(PetNotFoundException exception) {
        return GraphqlErrorBuilder.newError()
                .errorType(ErrorType.NOT_FOUND)
                .message(exception.getMessage())
                .build();
    }
}
