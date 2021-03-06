//******************************************************************************
//                          TokenGetDTO.java
// OpenSILEX - Licence AGPL V3.0 - https://www.gnu.org/licenses/agpl-3.0.en.html
// Copyright © INRA 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.rest.security.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <pre>
 * DTO repensenting JSON for getting or renew user token.
 *
 * JSON representation:
 * {
 *      token: ... user token
 * }
 * </pre>
 *
 * @see
 * org.opensilex.server.security.api.SecurityAPI#authenticate(org.opensilex.server.security.api.AuthenticationDTO)
 * @see
 * org.opensilex.server.security.api.SecurityAPI#renewToken(java.lang.String,
 * javax.ws.rs.core.SecurityContext)
 * @author Vincent Migot
 */
@ApiModel
public class TokenGetDTO {

    private final String token;

    public TokenGetDTO(String token) {
        this.token = token;
    }

    @ApiModelProperty(value = "User token", example = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzUxMiJ9.eyJpc19hZG1pbiI6dHJ...")
    public String getToken() {
        return token;
    }

}
