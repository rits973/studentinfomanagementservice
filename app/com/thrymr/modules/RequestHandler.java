package com.thrymr.modules;

import javax.inject.Inject;

import play.Logger;
import play.api.mvc.Handler;
import play.core.j.JavaHandler;
import play.core.j.JavaHandlerComponents;
import play.http.HandlerForRequest;
import play.http.HttpRequestHandler;
import play.mvc.EssentialAction;
import play.mvc.Http.RequestHeader;
import play.mvc.Results;
import play.routing.Router;
import play.libs.streams.Accumulator;

public class RequestHandler implements HttpRequestHandler {
    private final Router router;
    private final JavaHandlerComponents components;

    @Inject
    public RequestHandler(Router router, JavaHandlerComponents components) {
        this.router = router;
        this.components = components;
    }
    @Override
    public HandlerForRequest handlerForRequest(RequestHeader request) {
    	//Logger.info(router+"----request intercepted---"+request+"-----"+router.route(request));
        Handler handler = router.route(request).orElseGet(() ->
            EssentialAction.of(req -> Accumulator.done(Results.ok("You are sending bad request")))
        );
        if (handler instanceof JavaHandler) {
            handler = ((JavaHandler)handler).withComponents(components);
        }
        return new HandlerForRequest(request, handler);
    }
}