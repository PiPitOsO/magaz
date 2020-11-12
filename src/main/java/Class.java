public enum Class {
    PHONE("Смартфоны"), WATCH("Часы"), PAD("Планшеты");

    private final String ru;

    Class(String ru) {
        this.ru = ru;
    }

    @Override
    public String toString() {
        return ru;
    }
}