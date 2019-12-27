package space.vakar.weather.rest.exception.handler;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

  public Response toResponse(NotFoundException exception) {
    return Response.status(Response.Status.NOT_FOUND)
        .entity("404: No such resource.")
        .type("text/html; charset=UTF-8")
        .build();
  }
}
