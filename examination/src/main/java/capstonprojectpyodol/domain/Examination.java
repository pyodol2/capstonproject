package capstonprojectpyodol.domain;

import capstonprojectpyodol.ExaminationApplication;
import capstonprojectpyodol.domain.ExaminationCanceled;
import capstonprojectpyodol.domain.ExaminationCompleted;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Examination_table")
@Data
//<<< DDD / Aggregate Root
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long patientId;

    private Long examId;

    private Date examDt;

    private String status;

    @PostPersist
    public void onPostPersist() {}

    @PostUpdate
    public void onPostUpdate() {
        ExaminationCompleted examinationCompleted = new ExaminationCompleted(
            this
        );
        examinationCompleted.publishAfterCommit();

        ExaminationCanceled examinationCanceled = new ExaminationCanceled(this);
        examinationCanceled.publishAfterCommit();
    }

    public static ExaminationRepository repository() {
        ExaminationRepository examinationRepository = ExaminationApplication.applicationContext.getBean(
            ExaminationRepository.class
        );
        return examinationRepository;
    }

    //<<< Clean Arch / Port Method
    public static void prescriptionInfoTransfer(Prescribed prescribed) {
        //implement business logic here:

        /** Example 1:  new item 
        Examination examination = new Examination();
        repository().save(examination);

        */

        /** Example 2:  finding and process
        
        repository().findById(prescribed.get???()).ifPresent(examination->{
            
            examination // do something
            repository().save(examination);


         });
        */

    }
    //>>> Clean Arch / Port Method

}
//>>> DDD / Aggregate Root
