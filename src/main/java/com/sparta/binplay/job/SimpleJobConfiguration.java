package com.sparta.binplay.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Slf4j  //log 사용을 위한 lombok 어노테이션
//@RequiredArgsConstructor //생성자 DI를 위한 lombok 어노테이션
@Configuration
public class SimpleJobConfiguration {

    @Bean
    public Job simpleJob1(JobRepository jobRepository, Step simpleStep1) {
        return new JobBuilder("simpleJob", jobRepository)
                .start(simpleStep1)
                .build();
    }

    @Bean
    public Step simpleStep1(JobRepository jobRepository, Tasklet testTasklet, PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("simpleStep1", jobRepository)
                .tasklet(testTasklet, platformTransactionManager) //한 번의 처리
                .build();
    }

    @Bean
    public Tasklet testTasklet() {
        return ((contribution, chunkContext) -> {
            log.info(">>> This is Step1");
            return RepeatStatus.FINISHED;
        });
    }
}