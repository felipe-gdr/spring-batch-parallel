package experiments.sync.components;

import experiments.sync.model.Item;
import org.apache.commons.io.FileUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component("basicItemWriter")
public class BasicItemWriter implements ItemWriter<Item>, StepExecutionListener {
    private File outFile;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        String jobName = stepExecution.getJobExecution().getJobInstance().getJobName();

        outFile = new File("out/" + jobName + ".txt");

        try {
            if(outFile.exists()) {
                FileUtils.forceDelete(outFile);
            }
        } catch (IOException e) {
            System.out.printf("Error deleting directory %s", outFile.getAbsolutePath());
            e.printStackTrace();
        }
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.printf("%n\t\tFile written to disk. Total size: %s%n%n", FileUtils.byteCountToDisplaySize(outFile.length()));

        return ExitStatus.COMPLETED;
    }

    @Override
    public void write(List<? extends Item> items) throws Exception {
        FileUtils.writeLines(
                outFile,
                items,
                true
        );
    }
}
