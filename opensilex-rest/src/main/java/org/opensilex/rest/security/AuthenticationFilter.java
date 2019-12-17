//******************************************************************************
//                      AuthenticationFilter.java
// OpenSILEX - Licence AGPL V3.0 - https://www.gnu.org/licenses/agpl-3.0.en.html
// Copyright © INRA 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************n the template in the editor.
package org.opensilex.rest.security;

import com.auth0.jwt.exceptions.JWTVerificationException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import org.opensilex.server.exceptions.ForbiddenException;
import org.opensilex.server.exceptions.UnauthorizedException;
import org.opensilex.server.exceptions.UnexpectedErrorException;
import org.opensilex.rest.security.dal.SecurityAccessDAO;
import org.opensilex.rest.security.dal.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 * Authentication filter
 * For more information on request filters with Jersey
 * see: https://eclipse-ee4j.github.io/jersey.github.io/documentation/latest/filters-and-interceptors.html
 *
 * For any API service call, check if method is annotated with {@code org.opensilex.server.security.ApiProtected}
 * In that case parse header token to determine current user.
 * If user as "admin" flag, allow access to protected service.
 * Otherwise check if current method is in user credentials.
 * Throw {@code org.opensilex.server.exceptions.UnauthorizedException} if issues occured during token decoding or no token provided
 * Throw {@code org.opensilex.server.exceptions.ForbiddenException} if user is not found or don't have right credentials
 *
 * If user is identified, it can be accessed in the corresponding API method this way:
 * <code>
 *   ...
 *
 *   @Inject
 *   private AuthenticationService authentication;
 *
 *   ...
 *
 *   @GET
 * @Path("api-method") ...
 * @Produces(MediaType.APPLICATION_JSON) public Response apiMethod(
 * @Context SecurityContext securityContext ) throws Exception { UserModel
 * currentUser = authentication.getCurrentUser(securityContext);
 *
 * ... Do stuff with current user }
 *
 * ...
 * </code>
 * </pre>
 *
 * @author Vincent Migot
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Context
    ResourceInfo resourceInfo;

    @Inject
    AuthenticationService authentication;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method apiMethod = resourceInfo.getResourceMethod();

        if (apiMethod != null) {
            // Get method ApiProtected annotation
            ApiProtected securityAnnotation = apiMethod.getAnnotation(ApiProtected.class);

            if (securityAnnotation != null) {
                // If annotation is present, get user header token
                String tokenValue = requestContext.getHeaderString(ApiProtected.HEADER_NAME);

                // Throw exception if no token
                if (tokenValue == null) {
                    throw new UnauthorizedException();
                }

                try {
                    // Decode token
                    String token = tokenValue.replace(ApiProtected.TOKEN_PARAMETER_PREFIX, "");
                    URI userURI = authentication.decodeTokenUserURI(token);

                    // Get corresponding user
                    UserModel user;
                    if (authentication.hasUserURI(userURI)) {
                        user = authentication.getUserByUri(userURI);
                    } else {
                        throw new ForbiddenException("User not found with URI: " + userURI);
                    }

                    // If user is not an admin check credentials
                    if (!user.isAdmin()) {
                        // Get current API service credential
                        String credentialId = SecurityAccessDAO.getCredentialIdFromMethod(apiMethod, requestContext.getMethod());

                        // Get user credentials from token
                        String[] accessList = authentication.decodeTokenCredentialsList(user.getToken());

                        // Check user credential existence
                        boolean hasAccess = Arrays.stream(accessList).anyMatch(credentialId::equals);
                        if (!hasAccess) {
                            throw new ForbiddenException("You don't have credentials to access this API");
                        }
                    }

                    // Define user to be accessed through SecurityContext
                    SecurityContext originalContext = requestContext.getSecurityContext();
                    SecurityContext newContext = new SecurityContextProxy(originalContext, user);
                    requestContext.setSecurityContext(newContext);

                } catch (JWTVerificationException | URISyntaxException ex) {
                    LOGGER.debug("Error while decoding and verifying token: " + ex.getMessage());
                    throw new UnauthorizedException();
                } catch (ForbiddenException ex) {
                    throw ex;
                } catch (Throwable ex) {
                    throw new UnexpectedErrorException(ex);
                }
            }
        }
    }
}