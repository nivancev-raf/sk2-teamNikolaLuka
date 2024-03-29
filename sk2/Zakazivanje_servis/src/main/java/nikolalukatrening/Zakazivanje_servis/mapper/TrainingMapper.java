package nikolalukatrening.Zakazivanje_servis.mapper;

import nikolalukatrening.Zakazivanje_servis.dto.TrainingDto;
import nikolalukatrening.Zakazivanje_servis.model.Training;
import org.springframework.stereotype.Component;

@Component
public class TrainingMapper {

    public Training trainingDtoToTraining(TrainingDto trainingDto){
        Training training = new Training();
        training.setTrainingType(trainingDto.getTrainingType());
        training.setIsGroupTraining(trainingDto.getIsGroupTraining());
        training.setDate(trainingDto.getDate());
        training.setStartTime(trainingDto.getStartTime());
        training.setGymId(trainingDto.getGymId());
        training.setMaxParticipants(trainingDto.getMaxParticipants());
        training.setUserId(trainingDto.getUserId());
        training.setIsAvailable(trainingDto.getIsAvailable());

        return training;
    }

    public TrainingDto trainingToTrainingDto(Training training){
        TrainingDto trainingDto = new TrainingDto();
        trainingDto.setTrainingType(training.getTrainingType());
        trainingDto.setIsGroupTraining(training.getIsGroupTraining());
        trainingDto.setDate(training.getDate());
        trainingDto.setStartTime(training.getStartTime());
        trainingDto.setGymId(training.getGymId());
        trainingDto.setMaxParticipants(training.getMaxParticipants());
        trainingDto.setUserId(training.getUserId());
        trainingDto.setIsAvailable(training.getIsAvailable());
        return trainingDto;
    }

}
