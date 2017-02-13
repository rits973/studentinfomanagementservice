package com.thrymr.modules;

import javax.inject.Inject;

import play.api.mvc.Handler;
import play.core.j.JavaHandler;
import play.core.j.JavaHandlerComponents;
import play.http.HandlerForRequest;
import play.http.HttpRequestHandler;
import play.libs.streams.Accumulator;
import play.mvc.EssentialAction;
import play.mvc.Http.RequestHeader;
import play.mvc.Results;
import play.routing.Router;

public class RequestHandler implements HttpRequestHandler {
	private final Router router;
	private final JavaHandlerComponents components;

	@Inject
	public RequestHandler(final Router router, final JavaHandlerComponents components) {
		this.router = router;
		this.components = components;
	}
	@Override
	public HandlerForRequest handlerForRequest(final RequestHeader request) {
		//Logger.info(this.router+"----request intercepted---"+request+"-----"+this.router.route(request));
		Handler handler = this.router.route(request).orElseGet(() ->
		EssentialAction.of(req -> Accumulator.done(Results.ok("You are sending bad request")))
				);
		if (handler instanceof JavaHandler) {
			handler = ((JavaHandler)handler).withComponents(this.components);
		}
		return new HandlerForRequest(request, handler);
	}
}