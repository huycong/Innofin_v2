package com.innofin.web.config;

import org.aeonbits.owner.ConfigCache;

public class ConfigFactory {
    private ConfigFactory() {
    }

    public static Configuration getConfig() {
        return ConfigCache.getOrCreate(Configuration.class);
    }
}
