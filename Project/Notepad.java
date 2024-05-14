import java.util.*;

class Notepad {
    // Stack for undo functionality
    private Stack<String> undoStack;

    // Queue for redo functionality
    private Queue<String> redoQueue;

    // Set to avoid duplicate notes
    Set<String> notesSet;

    public Notepad() {
        undoStack = new Stack<>();
        redoQueue = new LinkedList<>();
        notesSet = new HashSet<>();
    }

    public void addNote(String note) {
        if (!notesSet.contains(note)) {
            undoStack.push(note);
            notesSet.add(note);
        }
    }

    public void removeNote(String note) {
        if (notesSet.contains(note)) {
            redoQueue.add(note);
            notesSet.remove(note);
        }
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            String note = undoStack.pop();
            notesSet.remove(note);
            redoQueue.add(note);
        }
    }

    public void redo() {
        if (!redoQueue.isEmpty()) {
            String note = redoQueue.poll();
            undoStack.push(note);
            notesSet.add(note);
        }
    }

    public void printNotes() {
        for (String note : notesSet) {
            System.out.println(note);
        }
    }
}