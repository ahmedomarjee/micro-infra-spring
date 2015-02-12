package com.ofg.infrastructure.property;

import com.google.common.base.Function;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import java.io.File;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

class ConfigLocations {

    /**
     * Shared properties across environments.
     */
    private final File commonDir;

    /**
     * Properties environment specific.
     */
    private final File envDir;

    /**
     * Properties country specific.
     */
    private final File countryDir;

    ConfigLocations(File commonDir, File envDir, File countryDir) {
        this.commonDir = commonDir;
        this.envDir = envDir;
        this.countryDir = countryDir;
    }

    List<Path> getConfigPaths() {
        return Lists.transform(getAllDirs(), new Function<File, Path>() {
            @Override
            public Path apply(File dir) {
                return dir.toPath();
            }
        });
    }

    File commonPropertiesFile(String name) {
        return propertiesFile(commonDir, name);
    }

    File commonYamlFile(String name) {
        return yamlFile(commonDir, name);
    }

    File envPropertiesFile(String name) {
        return propertiesFile(envDir, name);
    }

    File envYamlFile(String name) {
        return yamlFile(envDir, name);
    }

    File countryPropertiesFile(String name) {
        return propertiesFile(countryDir, name);
    }

    File countryYamlFile(String name) {
        return yamlFile(countryDir, name);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("commonDir", commonDir)
                .add("envDir", envDir)
                .add("countryDir", countryDir)
                .toString();
    }

    private List<File> getAllDirs() {
        return Arrays.asList(commonDir, envDir, countryDir);
    }

    private File propertiesFile(File parent, String name) {
        return new File(parent, name + ".properties");
    }

    private File yamlFile(File parent, String name) {
        return new File(parent, name + ".yaml");
    }
}
