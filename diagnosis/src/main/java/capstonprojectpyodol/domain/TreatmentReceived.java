package capstonprojectpyodol.domain;

import capstonprojectpyodol.domain.*;
import capstonprojectpyodol.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class TreatmentReceived extends AbstractEvent {

    private Long id;
    private Long patientId;
    private Date receptionDt;
    private String patientNm;
    private String address;
    private String status;
}
