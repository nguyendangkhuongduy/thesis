package com.duy.thesisManagement.thesis.model;

import java.util.Optional;

public enum AppRole {
		ROLE_ADMIN,
		ROLE_MANAGER,
		ROLE_ASSOCIATE,
		ROLE_STUDENT;

		public static Optional<AppRole> fromValue(String roleString) {
				for (AppRole role: AppRole.values()) {
						if (role.name().equals(roleString)) {
								return Optional.of(role);
						}
				}
				return Optional.empty();
		}

		public static String getName(AppRole appRole) {
				return appRole.name();
		}
}
