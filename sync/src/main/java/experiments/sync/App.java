package experiments.sync;

import experiments.sync.components.BasicItemReader;
import experiments.sync.components.ProcessorOne;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private static JobLauncher jobLauncher;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-batch.xml");

        jobLauncher = (JobLauncher) context.getBean("jobLauncher");

        final Job syncJob = (Job) context.getBean("syncBatchJob");
        final Job parallelJob = (Job) context.getBean("parallelBatchJob");
        final BasicItemReader basicItemReader = (BasicItemReader) context.getBean("basicItemReader");
        final ProcessorOne processorOne = (ProcessorOne) context.getBean("processorOne");

        App.runJob(syncJob);

        processorOne.resetCounter();
        basicItemReader.readItems();

        App.runJob(parallelJob);
    }

    private static void runJob(Job job) {
        System.out.println("Starting " + job.getName());

        try {
            final JobExecution execution = jobLauncher.run(job, new JobParameters());

            System.out.println("Job Status : " + execution.getStatus());
            System.out.println("Job succeeded");
        } catch (final Exception e) {
            System.out.println("Job failed");
            e.printStackTrace();
        }
    }
}
