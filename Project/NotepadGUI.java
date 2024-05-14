import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotepadGUI extends JFrame {
    private JTextArea notesTextArea;
    private JButton addNoteButton;
    private JButton removeNoteButton;
    private JButton undoButton;
    private JButton redoButton;
    private Notepad notepad;

    public NotepadGUI() {
        notepad = new Notepad();

        setTitle("Notepad");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        notesTextArea = new JTextArea();
        notesTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(notesTextArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.SOUTH);

        addNoteButton = new JButton("Add Note");
        addNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note = JOptionPane.showInputDialog("Enter note:");
                if (note != null) {
                    notepad.addNote(note);
                    updateNotesTextArea();
                }
            }
        });
        panel.add(addNoteButton);

        removeNoteButton = new JButton("Remove Note");
        removeNoteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String note = JOptionPane.showInputDialog("Enter note to remove:");
                if (note != null) {
                    notepad.removeNote(note);
                    updateNotesTextArea();
                }
            }
        });
        panel.add(removeNoteButton);

        undoButton = new JButton("Undo");
        undoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notepad.undo();
                updateNotesTextArea();
            }
        });
        panel.add(undoButton);

        redoButton = new JButton("Redo");
        redoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                notepad.redo();
                updateNotesTextArea();
            }
        });
        panel.add(redoButton);

        updateNotesTextArea();
    }

    private void updateNotesTextArea() {
        notesTextArea.setText("");
        for (String note : notepad.notesSet) {
            notesTextArea.append(note + "\n");
        }
    }

    public static void main(String[] args) {
        NotepadGUI gui = new NotepadGUI();
        gui.setVisible(true);
    }
}