/*
 * Copyright (c) 2016 WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.identity.provider.model;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Configuration of an outbound provisioning entity.
 */
public class ProvisionerConfig implements Serializable {

    private static final long serialVersionUID = -4569973060498183209L;

    protected String name;
    protected boolean isEnabled;
    //Properties of the connector to be used for outbound provisioning
    protected Map<String,Object> properties = new HashMap<>();

    private ProvisionerConfig(ProvisionerConfigBuilder builder) {
        this.name = builder.name;
        this.properties = builder.properties;
    }

    public String getName() {
        return name;
    }

    public Map<String,Object> getProperties() {
        return MapUtils.unmodifiableMap(properties);
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    /**
     * Builds the configuration of a provisioning connector.
     */
    public class ProvisionerConfigBuilder {

        protected String name;
        protected boolean isEnabled;
        protected Map<String,Object> properties = new HashMap<String,Object>();

        public ProvisionerConfigBuilder(String name) {
            this.name = name;
        }

        public ProvisionerConfigBuilder setEnabled(boolean isEnabled) {
            this.isEnabled = isEnabled;
            return this;
        }

        public ProvisionerConfigBuilder setProperties(Map<String,Object> properties) {
            if (!properties.isEmpty()) {
                this.properties = new HashMap<>(properties);
            }
            return this;
        }

        public ProvisionerConfigBuilder addProperty(String name, Object value) {
            if (StringUtils.isNotBlank(name) && value != null) {
                this.properties.put(name, value);
            }
            return this;
        }

        public ProvisionerConfigBuilder addProperties(Map<String,Object> properties) {
            if (!properties.isEmpty()) {
                this.properties.putAll(properties);
            }
            return this;
        }

        public ProvisionerConfig build() {
            return new ProvisionerConfig(this);
        }
    }

}
