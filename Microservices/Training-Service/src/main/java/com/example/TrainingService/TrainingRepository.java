package com.example.TrainingService;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface TrainingRepository extends CrudRepository<Trainings,Long> {
    @Query(value="select * from trainings t where t.user_id = ?1  and t.status = ?2",nativeQuery = true)
    List<Trainings> searchUserTrainings(Long userId, String status);

    @Query(value="select * from trainings t where t.mentor_id = ?1 and t.status = ?2",nativeQuery = true)
    List<Trainings> searchMentorTrainings(Long mentorId, String status);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update trainings t set progress = ?2 where t.id = ?1 ",nativeQuery = true)
    void updateProgress(Long id,Integer progress);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value="update trainings t set progress=100, status='completed' where t.id = ?1 ",nativeQuery = true)
    void completeTraining(Long id);
}
