import javax.swing.*;

public class MyJButton extends JButton{

    MyJButton(String title, String name){
        super(title);
        setName(name);
        setFocusable(false);
    }



}
