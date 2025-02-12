package com.schedulegroup.scheduleapp.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class FindByDateDto {
    @NotNull
    private LocalDate startDate;

    @NotNull
    private LocalDate endDate;

    public void checkDate() {
        if (startDate.isAfter(endDate)) {
            LocalDate newStartDate = endDate;
            LocalDate newEndDate = startDate;
            this.startDate = newStartDate;
            this.endDate = newEndDate;
        }
    }
}
