import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyJButton extends JButton implements ActionListener{

    MyJButton(String title, String name){
        super(title);
        setName(name);
        setFocusable(false);
        //addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(this.getName().equalsIgnoreCase("Exit")){
            System.out.println(this.getName());
            System.exit(0);
        } else if (this.getName().equalsIgnoreCase("Cancel")){

        }
    }
}
