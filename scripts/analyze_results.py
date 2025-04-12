import pandas as pd
import matplotlib.pyplot as plt

# Example: Load CSV log file (you'll generate it from Java later)
df = pd.read_csv("../results/execution_log.csv")

# Plot Execution Time
plt.plot(df["TaskCount"], df["ExecutionTime"], marker='o')
plt.title("Execution Time vs Task Count")
plt.xlabel("Number of Tasks")
plt.ylabel("Execution Time (ms)")
plt.grid(True)
plt.tight_layout()
plt.show()
