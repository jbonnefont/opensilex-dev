/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.opensilex.module.core.service.bigdata.mongodb;

import java.util.HashMap;
import org.opensilex.module.core.service.bigdata.datanucleus.AbstractDataNucleusConnection;

/**
 *
 * @author vincent
 */
public class MongoDBConnection extends AbstractDataNucleusConnection {

    public MongoDBConnection() {
        super(new HashMap<>());
    }

}
