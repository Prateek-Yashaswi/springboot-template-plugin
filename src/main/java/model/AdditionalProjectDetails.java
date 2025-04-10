package model;

import enums.Choice;

public record AdditionalProjectDetails(Choice createDockerFile, Choice createSwagger) {
}
