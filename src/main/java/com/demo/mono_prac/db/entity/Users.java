package com.demo.mono_prac.db.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users extends BaseEntity implements Serializable {
    @Column(unique = true)
    private String userId;
    @Column(unique = true)
    private String nickname;
    @JsonIgnore
    @JsonProperty(access = Access.WRITE_ONLY)
    private String password;
}
