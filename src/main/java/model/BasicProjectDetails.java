package model;

import enums.ConfigurationType;

public record BasicProjectDetails(String springVersion,
                                  String projectName,
                                  String groupId,
                                  String artifactId,
                                  String packageName,
                                  String javaVersion,
                                  ConfigurationType configurationType) {
}
