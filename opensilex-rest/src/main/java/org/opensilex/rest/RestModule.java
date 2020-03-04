//******************************************************************************
//                          RestModule.java
// OpenSILEX - Licence AGPL V3.0 - https://www.gnu.org/licenses/agpl-3.0.en.html
// Copyright © INRA 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.rest;

import org.opensilex.rest.authentication.SecurityOntology;
import org.opensilex.rest.extensions.APIExtension;
import java.util.*;
import org.opensilex.OpenSilex;
import org.opensilex.module.ModuleConfig;
import org.opensilex.OpenSilexModule;
import org.opensilex.rest.user.dal.UserDAO;
import org.opensilex.rest.user.dal.UserModel;
import org.opensilex.sparql.service.SPARQLService;
import org.opensilex.utils.ListWithPagination;

/**
 * <pre>
 * Base module implementation for OpenSilex.
 * - Enable Swagger
 * - Enable Security web services
 * - Enable SPARQL service through configuration
 * - Enable Big Data service through configuration
 * - Enable File System service through configuration
 * - Enable Authentication service through configuration
 * </pre>
 *
 * @see org.opensilex.server.ServerConfig
 * @author Vincent Migot
 */
public class RestModule extends OpenSilexModule implements APIExtension {

    @Override
    public Class<? extends ModuleConfig> getConfigClass() {
        return RestConfig.class;
    }

    @Override
    public String getConfigId() {
        return "rest";
    }

    @Override
    public List<String> getPackagesToScan() {
        List<String> list = APIExtension.super.getPackagesToScan();
        list.add("io.swagger.jaxrs.listing");
        list.add("org.opensilex.rest.authentication");
        list.add("org.opensilex.rest.filters");
        list.add("org.opensilex.rest.validation");

        return list;
    }

    @Override
    public void startup() throws Exception {
        SPARQLService sparql = OpenSilex.getInstance().getServiceInstance(SPARQLService.DEFAULT_SPARQL_SERVICE, SPARQLService.class);
        sparql.addPrefix(SecurityOntology.PREFIX, SecurityOntology.NAMESPACE);
    }
    
    @Override
    public void check() throws Exception {
        LOGGER.info("Check User existence");
        OpenSilex opensilex = OpenSilex.getInstance();
        SPARQLService sparql = opensilex.getServiceInstance(SPARQLService.DEFAULT_SPARQL_SERVICE, SPARQLService.class);
        UserDAO userDAO = new UserDAO(sparql);
        ListWithPagination<UserModel> result = userDAO.search(null, null, null, null);
        if (result.getTotal() == 0) {
            LOGGER.error("You should at least have one user defined to have a valid configuration");
            throw new Exception();
        }
    }


}
