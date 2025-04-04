package model;

import model.enums.ConfigurationType;

public record BasicProjectDetails(String springVersion,
                                  String projectName,
                                  String groupId,
                                  String artifactId,
                                  String packageName,
                                  Integer javaVersion,
                                  ConfigurationType configurationType) {
}
