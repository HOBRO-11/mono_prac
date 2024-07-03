package com.demo.mono_prac.db.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    // @Column(name = "TICKETS_ID")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Tickets> ticketList = new ArrayList<>();

    public void addTicket(Tickets tickets) {
        ticketList.add(tickets);
    }

    public void removeTicket(Tickets tickets) {
        ticketList.remove(tickets);
    }
}
