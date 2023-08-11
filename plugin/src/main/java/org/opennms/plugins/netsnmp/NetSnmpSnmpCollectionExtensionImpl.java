/*******************************************************************************
 * This file is part of OpenNMS(R).
 *
 * Copyright (C) 2019 The OpenNMS Group, Inc.
 * OpenNMS(R) is Copyright (C) 1999-2019 The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is a registered trademark of The OpenNMS Group, Inc.
 *
 * OpenNMS(R) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 *
 * OpenNMS(R) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with OpenNMS(R).  If not, see:
 *      http://www.gnu.org/licenses/
 *
 * For more information contact:
 *     OpenNMS(R) Licensing <license@opennms.org>
 *     http://www.opennms.org/
 *     http://www.opennms.com/
 *******************************************************************************/

package org.opennms.plugins.netsnmp;

import java.util.List;

import org.opennms.integration.api.v1.config.datacollection.SnmpCollectionExtension;
import org.opennms.integration.api.v1.config.datacollection.SnmpDataCollection;
import org.opennms.integration.api.xml.ClasspathSnmpDataCollectionLoader;

public class NetSnmpSnmpCollectionExtensionImpl implements SnmpCollectionExtension {

    private final ClasspathSnmpDataCollectionLoader snmpDataCollectionLoader =
            new ClasspathSnmpDataCollectionLoader(NetSnmpSnmpCollectionExtensionImpl.class,
                    "netsnmp.disk.xml",
                    "netsnmp.lmsensors.xml",
                    "netsnmp.system.xml");

    @Override
    public List<SnmpDataCollection> getSnmpDataCollectionGroups() {
        return snmpDataCollectionLoader.getSnmpDataCollections();
    }

    @Override
    public String getSnmpCollectionName() {
        return "default";
    }
}
