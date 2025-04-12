#!/bin/bash

echo "📁 Setting up IWRR Project Directory Structure..."

# Set base project directory
PROJECT_DIR=~/IWRR_Project
mkdir -p "$PROJECT_DIR"

# Create core folders
mkdir -p "$PROJECT_DIR/src"                    # Java source code
mkdir -p "$PROJECT_DIR/data"                   # Input datasets (e.g., Aadhar data)
mkdir -p "$PROJECT_DIR/results"                # Output logs, .csv metrics
mkdir -p "$PROJECT_DIR/scripts"                # Python analysis/plotting scripts
mkdir -p "$PROJECT_DIR/docs"                   # Documentation or reports
mkdir -p "$PROJECT_DIR/test"                   # Test jobs/configurations
mkdir -p "$PROJECT_DIR/bin"                    # Compiled Java class files

# Optional: Initialize placeholder files
touch "$PROJECT_DIR/README.md"
touch "$PROJECT_DIR/src/IWRRLoadBalancer.java"
touch "$PROJECT_DIR/src/Scheduler.java"
touch "$PROJECT_DIR/scripts/analyze_results.py"
touch "$PROJECT_DIR/data/sample_jobs.csv"

# Copy CloudSim if needed
if [ -d ~/cloudsim ]; then
  echo "🔁 Linking CloudSim directory..."
  ln -s ~/cloudsim "$PROJECT_DIR/cloudsim"
else
  echo "⚠️ CloudSim directory not found at ~/cloudsim. Skipping symbolic link."
fi

echo "✅ Project structure created at $PROJECT_DIR"
tree -L 2 "$PROJECT_DIR" 2>/dev/null || ls -R "$PROJECT_DIR"
