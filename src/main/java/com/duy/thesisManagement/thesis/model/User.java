package com.duy.thesisManagement.thesis.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
		private boolean active;
		@NotBlank
		private String password;
		@NotBlank
		private String fullName;
		@NotBlank
		private String phone;
		@NotBlank
		private String gender;
		@NotBlank
		@DateTimeFormat(pattern = "dd.MM.yyyy")
		private Date createdDate;

		@ManyToOne(fetch = FetchType.EAGER)

		@JoinColumn(name = "faculty_id")
		private Faculty faculty;

		@OneToMany(mappedBy = "userId")
		private Set<CouncilPosition> councilPositionSet;

		@ManyToMany(fetch = FetchType.LAZY)
		@JoinTable(	name = "user_roles",
				joinColumns = @JoinColumn(name = "user_id"))
		private Set<Role> roles = new HashSet<>();
		@OneToMany(mappedBy = "userId")
		private Set<ThesisPosition> thesisPositionSet;
}
