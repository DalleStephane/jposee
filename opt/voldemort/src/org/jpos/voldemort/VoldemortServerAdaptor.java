/*
 * jPOS Project [http://jpos.org]
 * Copyright (C) 2000-2009 Alejandro P. Revilla
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.jpos.voldemort;

import voldemort.server.VoldemortConfig;
import voldemort.server.VoldemortServer;
import voldemort.server.StoreRepository;
import voldemort.store.StorageEngine;

import org.jpos.q2.QBeanSupport;
import org.jpos.util.NameRegistrar;
import org.jpos.util.LogEvent;
import org.jpos.util.Logger;
import org.jpos.core.ConfigurationException;

import java.util.Iterator;

/**
 * VolcermortServerdaptor
 * @author Alejandro Revilla
 */
public class VoldemortServerAdaptor extends QBeanSupport {
    VoldemortServer server = null;

    public VoldemortServerAdaptor () {
        super ();
    }
    public void initService() throws ConfigurationException {
        VoldemortConfig config = 
            VoldemortConfig.loadFromVoldemortHome(cfg.get ("config"));
        server = new VoldemortServer(config);
    }
    public void startService() throws ConfigurationException {
        server.start();
        NameRegistrar.register (getName(), server);
    }
    protected void stopService () throws Exception {
        try {
            server.stop();
        } catch (Exception e) {
            getLog().error (e.getMessage());
        }
        NameRegistrar.unregister (getName());
    }
}

