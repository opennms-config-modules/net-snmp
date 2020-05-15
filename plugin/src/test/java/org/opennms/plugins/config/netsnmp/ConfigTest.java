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

package org.opennms.plugins.config.netsnmp;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.opennms.integration.api.v1.config.events.EventDefinition;
import org.opennms.integration.api.v1.model.Severity;

import java.util.List;

public class ConfigTest {

    @Test
    public void canLoadSnmpCollectionExtension() {
        NetSnmpSnmpCollectionExtensionImpl snmpCollectionExtension = new NetSnmpSnmpCollectionExtensionImpl();
        assertThat(snmpCollectionExtension.getSnmpDataCollectionGroups(), hasSize(3));
    }

    @Test
    public void canLoadGraphPropertiesExtension() {
        NetSnmpGraphPropertiesExtension graphPropertiesExtension = new NetSnmpGraphPropertiesExtension();
        assertThat(graphPropertiesExtension.getPrefabGraphs(), hasSize(27));
    }

    @Test
    public void canLoadResourceTypesExtension() {
        NetSnmpResourceTypesExtension resourceTypesExtension = new NetSnmpResourceTypesExtension();
        assertThat(resourceTypesExtension.getResourceTypes(), hasSize(2));
    }

    @Test
    public void canReadEventDefinitionsFromExtension() {
        NetSnmpEventsExtension netSnmpEventConfExtension = new NetSnmpEventsExtension();
        List<EventDefinition> eventDefinitions = netSnmpEventConfExtension.getEventDefinitions();
        assertThat(eventDefinitions, hasSize(3));
        EventDefinition nsNotifyStart = eventDefinitions.get(0);
        assertThat(nsNotifyStart.getUei(), equalTo("uei.opennms.org/vendor/netsnmp/traps/nsNotifyStart"));
        assertThat(nsNotifyStart.getPriority(), equalTo(20));
        assertThat(nsNotifyStart.getSeverity(), equalTo(Severity.NORMAL));

        EventDefinition nsNotifyShutdown = eventDefinitions.get(1);
        assertThat(nsNotifyShutdown.getUei(), equalTo("uei.opennms.org/vendor/netsnmp/traps/nsNotifyShutdown"));
        assertThat(nsNotifyShutdown.getPriority(), equalTo(20));
        assertThat(nsNotifyShutdown.getSeverity(), equalTo(Severity.WARNING));

        EventDefinition nsNotifyRestart = eventDefinitions.get(2);
        assertThat(nsNotifyRestart.getUei(), equalTo("uei.opennms.org/vendor/netsnmp/traps/nsNotifyRestart"));
        assertThat(nsNotifyRestart.getPriority(), equalTo(20));
        assertThat(nsNotifyRestart.getSeverity(), equalTo(Severity.NORMAL));
    }

    @Test
    public void testAliasRrdMax19CharLimit() {
        NetSnmpSnmpCollectionExtensionImpl snmpCollectionExtension = new NetSnmpSnmpCollectionExtensionImpl();
        snmpCollectionExtension.getSnmpDataCollectionGroups().forEach(collectionGroup -> collectionGroup.getGroups().forEach(group -> group.getMibObjs().forEach(mibObj -> assertThat(mibObj.getAlias().length(), lessThanOrEqualTo(19)))));
    }
}
