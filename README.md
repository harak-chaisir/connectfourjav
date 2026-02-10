# Connect Four (Java)

A simple Connect Four implementation in Java with both a command-line interface (CLI) and a JavaFX GUI.

Project highlights
- Minimal, well-structured Java code implementing the game logic (domain), a controller, a lightweight CLI, and a JavaFX UI.
- Build tool: Maven
- Java target version: 25 (see `pom.xml`)

Key files
- `src/main/java/org/connect/domain` — core game logic: `Board`, `Disc`, `Game`, `GameStatus`, `Player`.
- `src/main/java/org/connect/app` — application entry points: `Main` (CLI), `JavaFxApp` (GUI), and `CliRunner`.
- `src/main/java/org/connect/controller` — `GameController` to wire UI and domain.
- `src/main/java/org/connect/ui` — `BoardView` JavaFX view.

Prerequisites
- Java 25 JDK installed and available on PATH.
- Maven installed.
- On macOS, ensure your Java installation is compatible with JavaFX 25 (the project uses OpenJFX dependencies and the javafx-maven-plugin).

Build
To compile the project:

```bash
mvn clean package
```

Run (CLI)
Start the text-based CLI version (no JavaFX required):

```bash
# Build then run
mvn -q -DskipTests package
java -cp target/classes org.connect.app.Main
```

Run (JavaFX GUI)
The project includes the `javafx-maven-plugin` configured to launch the JavaFX application. Use this command to run the GUI:

```bash
mvn javafx:run
```

Notes:
- The `javafx:run` goal uses the plugin configuration in `pom.xml` and launches `org.connect.app.JavaFxApp`.
- If you prefer to run the GUI from an IDE (IntelliJ, VS Code), open the project as a Maven project and run `org.connect.app.JavaFxApp` as a Java application.

How to play
- CLI: enter a column number (0-6) when prompted to drop a disc in that column.
- GUI: click on a column circle to drop a disc.
- The game alternates between Player 1 (RED) and Player 2 (YELLOW). The game ends when one player connects four discs or the board fills (draw).

Project behavior and invariants
- Board is 6 rows x 7 columns.
- Column indices: 0..6.
- Illegal moves (out of range or into a full column) throw a runtime exception; the UI displays an error popup for invalid moves.

Troubleshooting
- If you see errors about JavaFX modules when running from the IDE or command line, make sure your JDK is set up correctly and compatible with OpenJFX 25. Using the `javafx-maven-plugin` (`mvn javafx:run`) usually handles module arguments automatically.
- If you get `java: invalid source release: 25`, ensure your `JAVA_HOME` points to a JDK 25 installation.

Extending the project
- Add unit tests under `src/test/java` to validate `Board` and `Game` logic.
- Add player name input and better GUI controls.

License
This repository does not include a license file. Add one (for example, MIT) if you want to make the project explicitly open-source.

Contact
If you want help improving the project (tests, CI, packaging into a native app), tell me what you want and I can implement it.
