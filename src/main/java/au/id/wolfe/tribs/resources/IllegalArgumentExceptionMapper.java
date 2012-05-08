package au.id.wolfe.tribs.resources;

import au.id.wolfe.tribs.data.Message;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 */
@Provider
public class IllegalArgumentExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException illegalArgumentException) {
        return Response.status(Response.Status.BAD_REQUEST)
                .entity(new Message(illegalArgumentException.getMessage()))
                .build();
    }
}
