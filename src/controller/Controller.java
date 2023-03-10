package controller;

import model.TaskManager;
import view.View;
import java.util.concurrent.ExecutionException;

public class Controller {
    private final TaskManager model;
    private final View view;

    public Controller() {
        model = new TaskManager();
        view = new View();
    }

    public void execute()  {
        view.printMenu();
        while (true) {
            try{
                String pathOrCommand = view.GetPathOrCommandToStart();
                if (pathOrCommand.equalsIgnoreCase("запуск")) {
                    view.printResult(model.execute());
                    break;
                }
                else {
                    view.printFileStatus(model.addTask(pathOrCommand));
                }
            } catch (ExecutionException | InterruptedException e) {
                view.error();
            }
        }
    }
}
