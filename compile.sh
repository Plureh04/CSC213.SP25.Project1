#!/bin/bash

# Define variables
SRC_DIR="src/main/java"
OUT_DIR="out"
JAR_FILE="uniquehands.jar"
MAIN_CLASS="edu.canisius.csc213.project1.UniqueHands"

# Clean previous build
echo "🧹 Cleaning previous build..."
rm -rf "$OUT_DIR"
rm -f "$JAR_FILE"

# Create output directory
mkdir -p "$OUT_DIR"

# Compile Java files
echo "🚀 Compiling Java files..."
javac -d "$OUT_DIR" -sourcepath "$SRC_DIR"
"$SRC_DIR"/edu/canisius/csc213/project1/*.java

# Check if compilation succeeded
if [ $? -ne 0 ]; then
    echo "Failed, need fix"
    exit 1
fi

# Package into a JAR
echo "📦 Creating JAR file..."
jar cfe "$JAR_FILE" "$MAIN_CLASS" -C "$OUT_DIR" .

# Check if JAR creation succeeded
if [ $? -ne 0 ]; then
    echo "Jar file was not created"
    exit 1
fi

# Done!
echo "Build worked!To Run: java -jar $JAR_FILE"
