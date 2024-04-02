package capstonprojectpyodol.domain;

import capstonprojectpyodol.DiagnosisApplication;
import capstonprojectpyodol.domain.DiagnosisCompleted;
import capstonprojectpyodol.domain.DiagnosisRejected;
import capstonprojectpyodol.domain.Prescribed;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Diagnosis_table")
@Data
//<<< DDD / Aggregate Root
public class Diagnosis {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Date receptionDt;

    private String priscribeCode;

    private Date priscribeDt;

    private String diagnosisStatus;

    @PostPersist
    public void onPostPersist() {
        Prescribed prescribed = new Prescribed(this);
        prescribed.publishAfterCommit();

        DiagnosisCompleted diagnosisCompleted = new DiagnosisCompleted(this);
        diagnosisCompleted.publishAfterCommit();

        DiagnosisRejected diagnosisRejected = new DiagnosisRejected(this);
        diagnosisRejected.publishAfterCommit();
    }

    public static DiagnosisRepository repository() {
        DiagnosisRepository diagnosisRepository = DiagnosisApplication.applicationContext.getBean(
            DiagnosisRepository.class
        );
        return diagnosisRepository;
    }

    //<<< Clean Arch / Port Method
    public static void patientInfoTransfer(
        TreatmentReceived treatmentReceived
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(treatmentReceived.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updatePrescribeStatus(
        ExaminationCanceled examinationCanceled
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(examinationCanceled.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

    }

    //>>> Clean Arch / Port Method
    //<<< Clean Arch / Port Method
    public static void updatePrescribeStatus(
        ExaminationCompleted examinationCompleted
    ) {
        //implement business logic here:

        /** Example 1:  new item 
        Diagnosis diagnosis = new Diagnosis();
        repository().save(diagnosis);

        */

        /** Example 2:  finding and process
        
        repository().findById(examinationCompleted.get???()).ifPresent(diagnosis->{
            
            diagnosis // do something
            repository().save(diagnosis);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
