package com.ite.pickon.domain.transport.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;


@Getter
@AllArgsConstructor
public class TransportScheduleVO {
    private int storeId;
    private Date departureTime;

}
