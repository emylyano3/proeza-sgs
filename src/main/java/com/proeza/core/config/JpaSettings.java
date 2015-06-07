package com.proeza.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JpaSettings extends Settings {

    @Value("${hibernate.hbm2ddl.auto}")
    private String hbm2ddlAuto;

    @Value("${hibernate.hbm2ddl.import_files}")
    private String hbm2ddlImportFiles;

    @Value("${hibernate.show_sql}")
    private String showSql;

    @Value("${hibernate.generate_statistics}")
    private String generateStatistics;

    @Value("${hibernate.cache.use_second_level_cache}")
    private String useSecondLevelCache;

    @Value("${hibernate.cache.use_query_cache}")
    private String useQueryCache;

    @Value("${hibernate.cache.region.factory_class}")
    private String cacheRegionFactoryClass;

    @Value("${hibernate.dialect}")
    private String dialect;

    @Value("${hibernate.current_session_context_class}")
    private String currentSessionContextClass;

    public String getHbm2ddlAuto () {
        return this.hbm2ddlAuto;
    }

    public String getHbm2ddlImportFiles () {
        return this.hbm2ddlImportFiles;
    }

    public String getShowSql () {
        return this.showSql;
    }

    public String getGenerateStatistics () {
        return this.generateStatistics;
    }

    public String getUseSecondLevelCache () {
        return this.useSecondLevelCache;
    }

    public String getUseQueryCache () {
        return this.useQueryCache;
    }

    public String getCacheRegionFactoryClass () {
        return this.cacheRegionFactoryClass;
    }

    public String getDialect () {
        return this.dialect;
    }

    public String getCurrentSessionContextClass () {
        return this.currentSessionContextClass;
    }
}