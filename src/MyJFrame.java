import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class MyJFrame {

    public static void main(String[] args) {

        //Применяю скинчик из стандартного набора
        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Не поддерживаемый стиль окна");
        }

        //Создаем текстовые поля
        JTextField textField = new JTextField();

        //Текстовую область засовываем в панель прокрутки
        JTextArea textArea = new JTextArea();
        //textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea);

        //Cчитываю предопределенный файл в буфер и заполняю textArea
        FileInputStream fis;
        BufferedReader br;
        try {
            fis = new FileInputStream("D:\\text.txt");
            br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
            String string;
            while ((string = br.readLine()) != null) {
                textArea.append(string);
                textArea.append("\n");
            }
            System.out.println("В поле выведено содержание файла");
            fis.close();
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл D:\\text.txt не найден");
        } catch (UnsupportedEncodingException e) {
            System.out.println("Не поддерживаемая кодировка файла");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //Создаем кнопки (см. конструктор)
        MyJButton jButtonOK = new MyJButton("Записать", "OK");
        MyJButton jButtonErase = new MyJButton("Очистить", "Erase");
        MyJButton jButtonExit = new MyJButton("Выход", "Exit");

        //Лямбдим листенеров
        //Для ОК
        jButtonOK.addActionListener((ActionEvent e1) -> {
            if (jButtonOK.getName().equalsIgnoreCase("OK")) {
                /*System.out.println(findIdx);*/
                //System.out.println(textArea.getText().length());
                int findIndex = 0;
                String findString = textField.getText();
                String string = textArea.getText();
                int idx = string.toLowerCase().indexOf(findString.toLowerCase());
                textArea.setCaretPosition(findIndex);

                if (idx == -1) {
                    textArea.insert("\n", 0);
                    textArea.insert(findString, 0);
                    System.out.println("текст записан, позиция курсора сброщена на 0");
                } else {
                    textArea.setCaretPosition(idx);
                    textArea.select(idx,idx+findString.length());
                    findIndex = idx;
                    textArea.requestFocusInWindow();
                    System.out.println("такая строка уже есть в тексте, позиция " + findIndex);
                }
            }

        });
        //Для Отмены ввода и очистки поля
        jButtonErase.addActionListener(e2 -> {
            if (jButtonErase.getName().equalsIgnoreCase("Erase")) {
                System.out.println(jButtonErase.getName());
                textField.setText("");

            }
        });
        //Для выхода из программы
        jButtonExit.addActionListener(e3 -> {
            if (jButtonExit.getName().equalsIgnoreCase("Exit")) {
                System.out.println(jButtonExit.getName());
                System.exit(0);
            }
        });


        //Создаю панели размещения
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());

        //Предопределяю размер фрейма
        JFrame jFrame = new JFrame();
        jFrame.setSize(600, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Создаю тривиальную рамку
        Border etchedBorder = new EtchedBorder();

        panel1.setBorder(etchedBorder);
        panel2.setBorder(etchedBorder);
        //panel3.setBorder(etchedBorder);

        panel1.add(textField);
        panel2.add(jScrollPane);


        //Третья панель для кнопок
        panel3.setLayout(new GridLayout(1, 3, 1,1));
        panel3.add(jButtonOK);
        panel3.add(jButtonErase);
        panel3.add(jButtonExit);

        //Втыкаю панели на фрейм
        jFrame.add(panel1, BorderLayout.NORTH);
        jFrame.add(panel2, BorderLayout.CENTER);
        jFrame.add(panel3, BorderLayout.SOUTH);

        //Поехали
        jFrame.setVisible(true);

    }


}
