package com.demo.mono_prac.db.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Tickets extends BaseEntity implements Serializable {
    private String code;
    private String seatRow;
    private int seatColumn;
    @JoinColumn(name = "USERS_ID")
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Users users;

    public String getSerialNum() {
        String serialNumFormat = "%s:%s-%s";
        return String.format(serialNumFormat, code, seatRow, seatColumn);
    }
}
