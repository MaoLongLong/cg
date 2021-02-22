# computer-game

## Java11

```bash
cd tic-tac-toe
mvn javafx:run
```

## Java8

修改根下的 [pom.xml](./pom.xml) 中的 jdk 版本：

```xml
<maven.compiler.source>1.8</maven.compiler.source>
<maven.compiler.target>1.8</maven.compiler.target>
```

删除 `tic-tac-toe` 下的 [pom.xml](./tic-tac-toe/pom.xml) 中的部分依赖：

```diff
<dependencies>
    <dependency>
        <groupId>com.csl</groupId>
        <artifactId>monte-carlo-tree</artifactId>
        <version>1.0-SNAPSHOT</version>
    </dependency>
-   <dependency>
-       <groupId>org.openjfx</groupId>
-       <artifactId>javafx-controls</artifactId>
-       <version>15.0.1</version>
-   </dependency>
-   <dependency>
-       <groupId>org.openjfx</groupId>
-       <artifactId>javafx-fxml</artifactId>
-       <version>15.0.1</version>
-   </dependency>
</dependencies>
```

最后运行 `TicTacToeApp` 中的主方法。
