package capstonprojectpyodol.infra;

import capstonprojectpyodol.config.kafka.KafkaProcessor;
import capstonprojectpyodol.domain.*;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.naming.NameParser;
import javax.naming.NameParser;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

//<<< Clean Arch / Inbound Adaptor
@Service
@Transactional
public class PolicyHandler {

    @Autowired
    ReceptionRepository receptionRepository;

    @StreamListener(KafkaProcessor.INPUT)
    public void whatever(@Payload String eventString) {}

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DiagnosisCompleted'"
    )
    public void wheneverDiagnosisCompleted_CompleteDiagnosis(
        @Payload DiagnosisCompleted diagnosisCompleted
    ) {
        DiagnosisCompleted event = diagnosisCompleted;
        System.out.println(
            "\n\n##### listener CompleteDiagnosis : " +
            diagnosisCompleted +
            "\n\n"
        );

        // Sample Logic //
        Reception.completeDiagnosis(event);
    }

    @StreamListener(
        value = KafkaProcessor.INPUT,
        condition = "headers['type']=='DiagnosisRejected'"
    )
    public void wheneverDiagnosisRejected_CancelDiagnosis(
        @Payload DiagnosisRejected diagnosisRejected
    ) {
        DiagnosisRejected event = diagnosisRejected;
        System.out.println(
            "\n\n##### listener CancelDiagnosis : " + diagnosisRejected + "\n\n"
        );

        // Sample Logic //
        Reception.cancelDiagnosis(event);
    }
}
//>>> Clean Arch / Inbound Adaptor
