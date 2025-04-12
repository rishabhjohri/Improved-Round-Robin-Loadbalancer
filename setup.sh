#!/bin/bash

echo "🔧 Starting IWRR environment setup in WSL..."

# Step 1: System Update
echo "📦 Updating system packages..."
sudo apt update && sudo apt upgrade -y

# Step 2: Install Java (CloudSim requirement)
echo "☕ Installing OpenJDK 11..."
sudo apt install -y openjdk-11-jdk
java -version

# Step 3: Install Git
echo "🔗 Installing Git..."
sudo apt install -y git

# Step 4: Install Python3 and pip
echo "🐍 Installing Python3 and pip..."
sudo apt install -y python3 python3-pip

# Step 5: Install Python libraries
echo "📚 Installing Python data libraries (pandas, matplotlib, seaborn)..."
pip3 install pandas matplotlib seaborn

# Step 6: Download CloudSim
echo "☁️ Cloning CloudSim..."
cd ~
git clone https://github.com/Cloudslab/cloudsim.git

# Step 7: Compile CloudSim examples
echo "⚙️ Compiling CloudSim examples..."
cd ~/cloudsim/examples
javac -cp ../jars/cloudsim-3.0.3.jar:. *.java

# Step 8: Run basic simulation test
echo "🚀 Running CloudSim test (CloudSimExample1)..."
java -cp ../jars/cloudsim-3.0.3.jar:. CloudSimExample1

echo "✅ IWRR environment setup complete!"
