package capstonprojectpyodol.domain;

import capstonprojectpyodol.domain.*;
import capstonprojectpyodol.infra.AbstractEvent;
import java.util.*;
import lombok.*;

@Data
@ToString
public class ExaminationCompleted extends AbstractEvent {

    private Long id;
}
