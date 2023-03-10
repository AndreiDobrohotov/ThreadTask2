package view;

import model.FileInfo;

import java.util.Scanner;
import java.util.Set;

public class View {
    private final Scanner scanner;

    public View() {
        scanner = new Scanner(System.in);
    }

    //вывод основного меню
    public void printMenu(){
        System.out.println("Вводите путь к файлам чтобы добавить их в очередь.\n" +
                            "Или введите команду \"запуск\" для начала подсчетов:");
    }

    //читаем путь к файлу или комманду на запуск
    public String GetPathOrCommandToStart(){
        return scanner.nextLine();
    }

    //выводим результаты подсчетов
    public void printResult(Set<FileInfo> result){
        for(FileInfo fileInfo: result){
            System.out.println(fileInfo);
        }
    }

    //вывод в случае runtime ошибки
    public void error(){
        System.out.println("Что-то пошло не так...");
    }

    //вывод статуса добавленного файла
    public void printFileStatus(boolean exist){
        System.out.println(exist? "Файл успешно добавлен.": "Указанный файл не существует.");
    }
}
