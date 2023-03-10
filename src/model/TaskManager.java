package model;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TaskManager {
    private final Set<CallableCounter> taskSet = new HashSet<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(3);

    public boolean addTask(String filePath) {
        if(Files.exists(Paths.get(filePath))) {
            taskSet.add(new CallableCounter(filePath));
            return true;
        }
        return false;
    }

    public Set<FileInfo> execute() throws InterruptedException, ExecutionException {
        Set<FileInfo> result = new HashSet<>();
        for (CallableCounter counter : taskSet) {
            counter.setFuture(executorService.submit(counter));
        }
        executorService.shutdown();
        if(!executorService.awaitTermination(60, TimeUnit.SECONDS)){
            executorService.shutdownNow();
        }
        for(CallableCounter counter : taskSet){
            result.add(counter.getFuture().get());
        }
        return result;
    }

}
