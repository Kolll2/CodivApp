package com.kolll.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;

public class ConfigurationService {
    private final File SETTINGS_FiLE = new File("settings.json");
    private Gson gson = new GsonBuilder().create();

    public ConfigModel getConfiguration() throws IOException {
        if (!SETTINGS_FiLE.exists()) {
            createSettingsFile();
        }
        return readSettingsFile();
    }

    private ConfigModel readSettingsFile() throws IOException {
        ConfigModel configModel = new ConfigModel();
        try (Reader reader = new FileReader(SETTINGS_FiLE)) {
            return gson.fromJson(reader, ConfigModel.class);
        }
    }

    private void createSettingsFile() throws IOException {
        ConfigModel configModel = new ConfigModel();
        try (Writer writer = new FileWriter(SETTINGS_FiLE, false)) {
            gson.toJson(configModel, writer);
        }
    }
}
