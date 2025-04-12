# Create additional columns for Task Migration, Delayed Tasks, and Instructions Re-executed

# Simulate values based on trends from the paper
task_migration = [max(0.1, 0.5 - 0.05 * i) for i in range(len(task_counts))]  # decreasing migration time
delayed_tasks = [max(0, 100 - 10 * i) for i in range(len(task_counts))]       # fewer delays with more tasks
instructions_reexecuted = [1550 - 65 * i for i in range(len(task_counts))]    # decreasing re-execution

# Update the dataframe
df["TaskMigration"] = task_migration
df["DelayedTasks"] = delayed_tasks
df["InstructionsReexecuted"] = instructions_reexecuted

# Save updated CSV
updated_csv_path = "/mnt/data/execution_log_full.csv"
df.to_csv(updated_csv_path, index=False)

# Plot 1: Task Migration
plt.figure(figsize=(8, 5))
plt.plot(df["TaskCount"], df["TaskMigration"], marker='o', color='green', label="Task Migration Time (ms)")
plt.title("Task Migration Time vs Number of Tasks")
plt.xlabel("Number of Tasks")
plt.ylabel("Migration Time (ms)")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Plot 2: Delayed Tasks
plt.figure(figsize=(8, 5))
plt.plot(df["TaskCount"], df["DelayedTasks"], marker='o', color='red', label="Delayed Tasks")
plt.title("Delayed Tasks vs Number of Tasks")
plt.xlabel("Number of Tasks")
plt.ylabel("Number of Delayed Tasks")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

# Plot 3: Instructions Re-executed
plt.figure(figsize=(8, 5))
plt.plot(df["TaskCount"], df["InstructionsReexecuted"], marker='o', color='purple', label="Instructions Re-executed")
plt.title("Re-executed Instructions vs Number of Tasks")
plt.xlabel("Number of Tasks")
plt.ylabel("Instructions Re-executed")
plt.grid(True)
plt.legend()
plt.tight_layout()
plt.show()

updated_csv_path
