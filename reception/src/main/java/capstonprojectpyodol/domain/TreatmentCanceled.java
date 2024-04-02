package capstonprojectpyodol.domain;

import capstonprojectpyodol.domain.*;
import capstonprojectpyodol.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class TreatmentCanceled extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String patientNm;
    private String address;
    private String status;

    public TreatmentCanceled(Reception aggregate) {
        super(aggregate);
    }

    public TreatmentCanceled() {
        super();
    }
}
//>>> DDD / Domain Event
