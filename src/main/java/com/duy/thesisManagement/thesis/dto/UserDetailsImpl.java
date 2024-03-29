package com.duy.thesisManagement.thesis.dto;

import com.duy.thesisManagement.thesis.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsImpl implements UserDetails {

		private static final long serialVersionUID = 1L;
		private Integer id;
		private String username;
		private String email;
		@JsonIgnore
		private String password;

		private String fullName;
		private String gender;
		private String phone;
		private Integer facultyId;

		private String avatar;

		private boolean active;
		private Collection<? extends GrantedAuthority> authorities;

		public UserDetailsImpl(Integer id, String username, String email, String password, String fullName, String gender,
				String phone, Integer facultyId,String avatar,Boolean active, Collection<? extends GrantedAuthority> authorities) {
				this.id = id;
				this.username = username;
				this.email = email;
				this.password = password;
				this.authorities = authorities;
				this.fullName = fullName;
				this.gender = gender;
				this.phone = phone;
				this.facultyId = facultyId;
				this.setAvatar(avatar);
				this.setActive(active);
		}

		public static UserDetailsImpl build(User user) {
				List<GrantedAuthority> authorities = user.getRoles().stream()
						.map(role -> new SimpleGrantedAuthority(role.getName().name()))
						.collect(Collectors.toList());
				return new UserDetailsImpl(
						user.getId(),
						user.getUsername(),
						user.getEmail(),
						user.getPassword(),
						user.getFullName(),
						user.getGender(),
						user.getPhone(),
						user.getFaculty().getId(),
						user.getAvatar(),
						user.isActive(),
						authorities);
		}

		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
				return authorities;
		}

		public Integer getId() {
				return id;
		}

		public String getEmail() {
				return email;
		}

		@Override
		public String getPassword() {
				return password;
		}

		@Override
		public String getUsername() {
				return username;
		}

		@Override
		public boolean isAccountNonExpired() {
				return true;
		}

		@Override
		public boolean isAccountNonLocked() {
				return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
				return true;
		}

		@Override
		public boolean isEnabled() {
				return true;
		}

		@Override
		public boolean equals(Object o) {
				if (this == o) {
						return true;
				}
				if (o == null || getClass() != o.getClass()) {
						return false;
				}
				UserDetailsImpl user = (UserDetailsImpl) o;
				return Objects.equals(id, user.id);
		}

	public String getFullName() {
		return fullName;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public Integer getFacultyId() {
		return facultyId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}