package capstonprojectpyodol.domain;

import capstonprojectpyodol.domain.*;
import capstonprojectpyodol.infra.AbstractEvent;
import java.time.LocalDate;
import java.util.*;
import lombok.*;

//<<< DDD / Domain Event
@Data
@ToString
public class ExaminationCompleted extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Long examId;
    private Date examDt;
    private String status;

    public ExaminationCompleted(Examination aggregate) {
        super(aggregate);
    }

    public ExaminationCompleted() {
        super();
    }
}
//>>> DDD / Domain Event
