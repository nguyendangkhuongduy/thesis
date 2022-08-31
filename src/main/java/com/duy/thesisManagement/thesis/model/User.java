package com.duy.thesisManagement.thesis.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(	name = "users",
		uniqueConstraints = {
				@UniqueConstraint(columnNames = "username"),
				@UniqueConstraint(columnNames = "email")
		})
public class User {
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		@NotBlank
		private String username;
		@NotBlank
		@Email
		private String email;
		@NotBlank
		private String password;
		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"))
		private Set<Role> roles = new HashSet<>();
}
