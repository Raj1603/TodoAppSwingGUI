import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TodoApp {
    public static void main(String[] args) {
        // Always start Swing apps on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new TodoApp().createAndShowGUI());
    }

    private DefaultListModel<String> taskListModel; // Holds the tasks
    private JList<String> taskList;                 // UI list component
    private JTextField taskInput;                   // Input field

    private void createAndShowGUI() {
        // Main frame (window)
        JFrame frame = new JFrame("📝 To-Do List");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLayout(new BorderLayout());

        // Top panel: input + Add button
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        taskInput = new JTextField();
        JButton addButton = new JButton("Add");

        inputPanel.add(taskInput, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        // Center: JList inside a scroll pane
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskList);

        // Bottom panel: Delete button
        JPanel bottomPanel = new JPanel();
        JButton deleteButton = new JButton("Delete");
        bottomPanel.add(deleteButton);

        // Add panels to frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        // 🔹 Event: Add task
        addButton.addActionListener(e -> addTask());
        taskInput.addActionListener(e -> addTask()); // Press Enter in text field

        // 🔹 Event: Delete task
        deleteButton.addActionListener(e -> deleteTask());

        // Show window
        frame.setVisible(true);
    }

    private void addTask() {
        String task = taskInput.getText().trim();
        if (!task.isEmpty()) {
            taskListModel.addElement(task); // Add to the list
            taskInput.setText("");          // Clear input
        } else {
            JOptionPane.showMessageDialog(null, "Task cannot be empty!");
        }
    }

    private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) {
            taskListModel.remove(selectedIndex);
        } else {
            JOptionPane.showMessageDialog(null, "Select a task to delete!");
        }
    }
}
