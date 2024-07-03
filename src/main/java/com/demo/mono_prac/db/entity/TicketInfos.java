package com.demo.mono_prac.db.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TicketInfos extends BaseEntity {

    private String title;

    private String availableSeat;

    @OneToMany(mappedBy = "ticketInfos", cascade = CascadeType.ALL)
    private List<Tickets> ticketList = new ArrayList<>();

	public void addTickets(Tickets tickets) {
		ticketList.add(tickets);
	}

}
