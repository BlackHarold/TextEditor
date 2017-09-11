import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class MyJFrame {





    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            System.out.println("Не поддерживаемый стиль окна");
        }

        //Создаем текстовые поля
        JTextField textField = new JTextField();

        //Cчитываем предопределенный файл в буфер и заполняем textArea
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea);
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

        jButtonOK.addActionListener((ActionEvent e1) -> {
            if (jButtonOK.getName().equalsIgnoreCase("OK")) {

                textArea.insert("\n", 0);
                textArea.insert(textField.getText(), 0);

                /*FileInputStream fis1;
                BufferedReader br1;
                try {
                    fis1 = new FileInputStream("D:\\text.txt");
                    br1 = new BufferedReader(new InputStreamReader(fis1, "UTF-8"));
                    String string1;
                    while ((string1 = br1.readLine()) != null) {
                        textArea.insert(string1 + "\n", 1);
                        //textArea.insert("\n",0);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Файл D:\\text.txt не найден");
                } catch (UnsupportedEncodingException e) {
                    System.out.println("Не поддерживаемая кодировка файла");
                } catch (IOException e) {
                    e.printStackTrace();
                }*/

                System.out.println(jButtonOK.getName());
            }

        });
        jButtonErase.addActionListener(e2 -> {
            if (jButtonErase.getName().equalsIgnoreCase("Erase")) {
                System.out.println(jButtonErase.getName());
                textField.setText("");

            }
        });
        jButtonExit.addActionListener(e3 -> {
            if (jButtonExit.getName().equalsIgnoreCase("Exit")) {
                System.out.println(jButtonExit.getName());
                System.exit(0);
            }
        });


        //Создаем панели размещения
        JPanel panel1 = new JPanel(new BorderLayout());
        JPanel panel2 = new JPanel(new BorderLayout());
        JPanel panel3 = new JPanel(new BorderLayout());

        JFrame jFrame = new JFrame();
        jFrame.setSize(600, 400);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Border etchedBorder = new EtchedBorder();

        panel1.setBorder(etchedBorder);
        //panel2.setBorder(etchedBorder);
        //panel3.setBorder(etchedBorder);

        panel1.add(textField);
        panel2.add(jScrollPane);

        panel3.setLayout(new GridLayout(1, 3));
        panel3.add(jButtonOK);
        panel3.add(jButtonErase);
        panel3.add(jButtonExit);

        jFrame.add(panel1, BorderLayout.NORTH);
        jFrame.add(panel2, BorderLayout.CENTER);
        jFrame.add(panel3, BorderLayout.SOUTH);

        jFrame.setVisible(true);

    }
}
