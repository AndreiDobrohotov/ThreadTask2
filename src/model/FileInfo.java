package model;

public class FileInfo {

    public FileInfo(String fileName) {
        this.fileName = fileName;
        sentenceCount = 0;
        symbolCount = 0;
        spaceCount = 0;
        wordCount = 0;
    }

    private final String fileName;
    private int symbolCount;
    private int spaceCount;
    private int sentenceCount;
    private int wordCount;

    public void setSymbolCount(int symbolCount) {
        this.symbolCount = symbolCount;
    }

    public void setSpaceCount(int spaceCount) {
        this.spaceCount = spaceCount;
    }

    public void setSentenceCount(int sentenceCount) {
        this.sentenceCount = sentenceCount;
    }

    public void setWordCount(int wordCount) {
        this.wordCount = wordCount;
    }



    @Override
    public String toString() {
        return "В файле " + fileName + " содержится:\n"+
                symbolCount + " символов\n" +
                spaceCount + " пробелов\n" +
                wordCount + " слов\n" +
                sentenceCount + " предложений\n";
    }
}
