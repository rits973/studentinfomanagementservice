package com.thrymr.errorhandlers;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import javax.inject.Singleton;

import play.http.HttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;
import play.mvc.Results;

@Singleton
public class ErrorHandler implements HttpErrorHandler {
	@Override
	public CompletionStage<Result> onClientError(RequestHeader request, int statusCode, String message) {
		return CompletableFuture.completedFuture(
				//here suitable page is configured for showing internal error
                Results.status(statusCode, "A client error occurred: " + message)
        );
	}

	@Override
	public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
		return CompletableFuture.completedFuture(
				//here suitable page is configured for showing internal error
                Results.internalServerError("A server error occurred: " + exception.getMessage())
        );
	}
}
