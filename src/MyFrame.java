import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener {
    private JTextField nameTF;
    private JTextField emailTF;
    private JCheckBox news;

    public MyFrame() {
        super("Моя программа");
        JFrame frame = new JFrame("Моя программа");
        //Dimension size = Toolkit.getDefaultToolkit().getScreenSize();//размер экрана
        //frame.setLocation(0,0);//начало окна
        //frame.setSize(size); //установления размера через Dimension
        frame.setBounds(300, 300, 600, 400);
        //frame.setResizable(); //регулирование размера окна
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //завершает программу при закрытие окна
        frame.setLayout(new BorderLayout());
        //JPanel panel = new JPanel();


       /* JButton button = new JButton ("Кнопка");
        panel.add(button);*/
        JPanel top = new JPanel();
        top.setBorder(new EmptyBorder(0, 0, 0, 0));
        JLabel labelTitle = new JLabel("Моя форма");
        labelTitle.setFont(new Font("Ariel", Font.BOLD, 22));
        top.add(labelTitle);

        JPanel form = new JPanel();
        form.setLayout(new GridBagLayout());
        JLabel nameL = new JLabel("Имя:");
        nameTF = new JTextField();
        JLabel emailL = new JLabel("Почта:");
        emailTF = new JTextField();

        nameL.setHorizontalAlignment(SwingConstants.RIGHT);
        emailL.setHorizontalAlignment(SwingConstants.RIGHT);

        nameTF.setPreferredSize(new Dimension(200, 40));
        emailTF.setPreferredSize(new Dimension(200, 40));

        GridBagConstraints g = new GridBagConstraints();
        g.fill = GridBagConstraints.BOTH;//выбирает что делать с элементами, растягивать ли до конца окна
        g.gridx = 0;
        g.gridy = 0;
        g.insets = new Insets(0, 0, 10, 5);
        form.add(nameL, g);
        g.gridx = 1;
        form.add(nameTF, g);
        g.gridx = 0;
        g.gridy = 1;
        form.add(emailL, g);
        g.gridx = 1;
        form.add(emailTF, g);


        JPanel botton = new JPanel();
        news = new JCheckBox("Подписаться на новости");
        JButton button = new JButton("Отправить");
        button.setPreferredSize(new Dimension(200, 50));
        botton.add(news);
        botton.add(button);
        botton.setBorder(new EmptyBorder(0, 0, 20, 0));

        JMenuBar mb = new JMenuBar();
        JMenu main = new JMenu("программа");
        JMenuItem exit = new JMenuItem("выход");
        main.add(exit);
        mb.add(main);
        /*JCheckBox check = new JCheckBox("текст чекбокса");
        panel.add(check);

        JTextField tf = new JTextField("текст по умолчанию");
        panel.add(tf);

        ButtonGroup bg = new ButtonGroup();

        JRadioButton rb_1 = new JRadioButton("Кнопка 1");
        JRadioButton rb_2 = new JRadioButton("Кнопка 2");
        bg.add(rb_1);
        bg.add(rb_2);
        panel.add(rb_1);
        panel.add(rb_2);

        JTextArea ta = new JTextArea("текст по умолчанию");
        panel.add(ta);

        String[][] data = {{"1","2"},{"3","4"}};
        String[] coln = {"столбец1","столбец2"};

        JTable table = new JTable(data,coln);
        JScrollPane scroll = new JScrollPane(table);
        panel.add(scroll);*/

        button.addActionListener(this);
        news.addActionListener(this);
        frame.add(top, BorderLayout.NORTH);
        frame.add(form, BorderLayout.CENTER);
        frame.add(botton, BorderLayout.SOUTH);
        //frame.add(panel);
        frame.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        System.out.println(ae.getActionCommand());
        if (ae.getActionCommand().equals("Подписаться на новости")) {
            System.out.println("нажат чекбокс");
        } else if (ae.getActionCommand().equals("Отправить")) {
            System.out.println("нажата кнопка");
            String name = nameTF.getText();
            String email = emailTF.getText();
            boolean bnews = news.isSelected();
            try {
                User user = new User(name, email, bnews);
                User.add(user);
                User.printAllUsers();
            } catch (UserException u) {
                if (u.getCode() == UserException.NO_NAME) showError("Вы не ввели имя");
                if (u.getCode() == UserException.NO_EMAIL) showError("Вы не ввели почту");

            }
        }
    }

    private void showError(String text) {
        JOptionPane.showMessageDialog(this, text,"Ошибка",JOptionPane.ERROR_MESSAGE);
    }
}
