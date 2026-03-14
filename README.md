# 🐍 Snake Game in Java

A classic **Snake Game** built using **Java Swing and AWT**.  
The player controls a snake that moves around the board, eats food, and grows longer. The game ends if the snake collides with its own body.

This project demonstrates **event handling, GUI design, timers, keyboard input, and game logic** in Java.

---

## 📌 Features

- Classic Snake gameplay
- Keyboard controls (Arrow Keys)
- Score tracking
- Snake length increases after eating food
- Game Over detection
- Restart option using **SPACE key**
- Random food spawning
- Border wrapping movement

---

## 🛠 Technologies Used

- **Java**
- **Java Swing**
- **AWT (Abstract Window Toolkit)**

---

## 🎮 Game Controls

| Key | Action |
|----|------|
| ⬆ Arrow | Move Up |
| ⬇ Arrow | Move Down |
| ⬅ Arrow | Move Left |
| ➡ Arrow | Move Right |
| Space | Restart Game |

---

## 🧠 Game Logic

The game works using:

- **JFrame** for the game window
- **JPanel** for the game board
- **Timer** for continuous snake movement
- **KeyListener** for player input
- **Collision detection** for:
  - Food
  - Snake body

The snake grows when it eats food and the score increases.

---

## 📂 Project Structure

SnakeGame
│
├── Main.java
├── GamePanel.java
├── enemy.png
├── snakeimage.png
├── snaketitle.jpg
├── leftmouth.png
├── rightmouth.png
├── upmouth.png
└── downmouth.png


- **Main.java** – Starts the game and creates the main window. :contentReference[oaicite:0]{index=0}  
- **GamePanel.java** – Contains the full game logic, rendering, and controls. :contentReference[oaicite:1]{index=1}  

---

## ▶️ How to Run

### 1️⃣ Compile the program
    
  javac Main.java GamePanel.java

2️⃣ Run the game
      java Main
