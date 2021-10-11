package com.aspireapp.utils.config;

import com.beust.jcommander.DynamicParameter;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.converters.FileConverter;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CommandLine {

  @Parameter(names = "-c", converter = FileConverter.class, description = "Alternate config file", order = 1)
  private File configFile = new File("aspire.properties");

  @Parameter(names = "-C",  description = "Don't use config file - all config must be via Overrides", order = 2)
  private boolean noConfigFile = false;


  @DynamicParameter(names = "-O", description = "Configuration Overrides")
  private Map<String, String> overrides = new HashMap<>();

  public File getConfigFile() {
    return configFile;
  }

  public Map<String, String> getArguments() {
    return overrides;
  }
  public boolean isNoConfigFile() {
    return noConfigFile;
  }


}
