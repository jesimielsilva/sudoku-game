# 🧩 Sudoku em Java (Swing)

Um jogo clássico de Sudoku desenvolvido em **Java 17** utilizando **Swing** para a interface gráfica.  
O objetivo é preencher a grade 9x9 com números de 1 a 9 sem repetir valores nas linhas, colunas e blocos 3x3.

---

## 📌 Funcionalidades
- Interface gráfica com **Swing**
- Células fixas (valores iniciais) e editáveis
- Destaque da célula selecionada
- Validação visual de erros (números em conflito ficam vermelhos)
- Navegação com o mouse
- Entrada de números pelo teclado
- Geração de tabuleiros inicializados

---

## 🚀 Como Executar
1. **Clonar o repositório**
```bash
git clone https://github.com/seuusuario/sudoku-java.git
cd sudoku-java
```

## 📂 Estrutura do Projeto
```plaintext
src/
├── SudokuGame.java   # Lógica do jogo
├── SudokuCell.java   # Representação de uma célula
├── SudokuFrame.java  # Interface gráfica
├── Main.java         # Ponto de entrada do programa
```

## 🏗️ Como Compilar e Executar

### Compilar
```bash
javac -d bin src/*.java
```

## 🚀 Executar
```bash
java -cp bin Main
```

## 🖥️ Controles
- Clique em uma célula para selecioná-la  
- Teclas numéricas **(1-9)** para preencher a célula  
- **Backspace/Delete** para apagar valor  
- Células fixas **não podem ser alteradas**  

---

## 📸 Captura de Tela
<img src="img/tela do jogo.png" alt="Tela do Jogo">

---

## 🛠️ Tecnologias Utilizadas
- Java 17  
- Swing (AWT para renderização gráfica)  
- Programação Orientada a Objetos  

---

## 📜 Licença
Este projeto é de código aberto sob a licença **MIT**.  
Sinta-se à vontade para modificar e compartilhar.

---

## 👨‍💻 Autor
GitHub: [@jesimielsilva](https://github.com/jesimielsilva)
