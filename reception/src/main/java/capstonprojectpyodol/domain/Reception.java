package capstonprojectpyodol.domain;

import capstonprojectpyodol.ReceptionApplication;
import capstonprojectpyodol.domain.TreatmentCanceled;
import capstonprojectpyodol.domain.TreatmentReceived;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Reception_table")
@Data
//<<< DDD / Aggregate Root
public class Reception {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Date receptionDt;

    private String patientNm;

    private String address;

    private String status;

    @PostPersist
    public void onPostPersist() {
        TreatmentReceived treatmentReceived = new TreatmentReceived(this);
        treatmentReceived.publishAfterCommit();

        TreatmentCanceled treatmentCanceled = new TreatmentCanceled(this);
        treatmentCanceled.publishAfterCommit();
    }

    public static ReceptionRepository repository() {
        ReceptionRepository receptionRepository = ReceptionApplication.applicationContext.getBean(
            ReceptionRepository.class
        );
        return receptionRepository;
    }

    //<<< Clean Arch / Port Method
    public static void completeDiagnosis(
        DiagnosisCompleted diagnosisCompleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Reception reception = new Reception();
        repository().save(reception);

        */

        /** Example 2:  finding and process
        
        repository().findById(diagnosisCompleted.get???()).ifPresent(reception->{
            
            reception // do something
            repository().save(reception);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void cancelDiagnosis(DiagnosisRejected diagnosisRejected) {
        //implement business logic here:

        /** Example 1:  new item 
        Reception reception = new Reception();
        repository().save(reception);

        */

        /** Example 2:  finding and process
        
        repository().findById(diagnosisRejected.get???()).ifPresent(reception->{
            
            reception // do something
            repository().save(reception);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
