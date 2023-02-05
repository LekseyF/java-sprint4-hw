public class Managers {
    private static InMemoryHistoryManager inMemoryHistoryManager = new InMemoryHistoryManager();

    TaskManager getDefault() {
        return null;
    }

    static HistoryManager getDefaultHistory() {
        return inMemoryHistoryManager;
    }
}
