package model;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class CallableCounter implements Callable<FileInfo> {
    private final String filePath;
    private Future<FileInfo> future;

    public CallableCounter(String filePath) {
        this.filePath = filePath;
    }

    public Future<FileInfo> getFuture() {
        return future;
    }

    public void setFuture(Future<FileInfo> future) {
        this.future = future;
    }

    @Override
    public FileInfo call() throws Exception {
        Path path = Paths.get(filePath);
        if(!Files.exists(path)) {
            return null;
        }
        FileInfo result = new FileInfo(path.getFileName().toString());
        String text = new String(Files.readAllBytes(path));
        //количество всех символов в тексте
        result.setSymbolCount(text.length());
        //удаляем все пробелы и получаем разницу в длинне текста
        result.setSpaceCount(text.length() - text.replaceAll(" ", "").length());
        //разбиваем текст по пробелу и фильтруем чтобы исключить одиночные знаки
        result.setWordCount(Arrays.stream(text.split(" "))
                .filter(s -> s.length() > 1 || Character.isLetter(s.charAt(0)))
                .toArray().length);
        //разбиваем текст по точке и фильтруем чтобы исключить сокращения
        result.setSentenceCount(Arrays.stream(text.split("\\."))
                .filter(s -> s.length()>2 && s.contains(" "))
                .toArray().length);
        return result;
        }
    }

