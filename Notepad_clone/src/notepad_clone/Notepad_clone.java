//Vishal Bhaliya
//01-01-2025


package notepad_clone;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.filechooser.*;

public class Notepad_clone extends JFrame implements ActionListener{
    
    private JTextArea area;
    private JScrollPane scpan;
     String text = "";

    Notepad_clone()
    {
        setTitle("Notepad");
        ImageIcon notepadIcon = new ImageIcon(ClassLoader.getSystemResource("notepad_clone/icon/notepad.png"));
        Image icon = notepadIcon.getImage(); //Image class stored in AWT(Abstract Window ToolKit) and ImageIcon object convert into Image Object and fetch icon and setIconImage via Image object 
        setIconImage(icon);
        setExtendedState(JFrame.MAXIMIZED_BOTH); //setExtendedState is used to set Frame size according to or screen size
        
         JMenuBar menubar = new JMenuBar(); //manu bar
         JMenu file = new JMenu("File"); //file manu
         
         JMenuItem newdoc = new JMenuItem("New");
         newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));//this function is user for sort-cut key like ctrl + n for new file
         newdoc.addActionListener(this);
         
         JMenuItem open = new JMenuItem("Open");
         open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));//this function is user for sort-cut key like ctrl + n for new file
         open.addActionListener(this);
         
         JMenuItem save = new JMenuItem("Save");
         save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
         save.addActionListener(this);
         
        JMenuItem print = new JMenuItem("Print");
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
        print.addActionListener(this);
         
         JMenuItem exit = new JMenuItem("Exit");
         exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0));
         exit.addActionListener(this);
         
        JMenu edit = new JMenu("Edit");
         JMenuItem copy = new JMenuItem("Copy");
         copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
         copy.addActionListener(this);
         
         JMenuItem paste = new JMenuItem("Paste");
         paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
         paste.addActionListener(this);
         
         
         JMenuItem cut = new JMenuItem("Cut");
         cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
         cut.addActionListener(this);
         
        
         JMenuItem selectAll = new JMenuItem("selectAll");
         selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
         selectAll.addActionListener(this);
         
         JMenu about = new JMenu("Help");
         
         JMenuItem notepad = new JMenuItem("About Notepad");
         notepad.addActionListener(this);
         
        area = new JTextArea();
        area.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        area.setLineWrap(true); //this function is used for nextLine
        area.setWrapStyleWord(true); //this funvtion is used for complet full word like "Good" write a first line and "Morning" write at next line but,this function is used to wite whool word in single line like "Good Morning"
         
        scpan = new JScrollPane(area);
        scpan.setBorder(BorderFactory.createEmptyBorder()); // remove scrollerbar Border
        
        setJMenuBar(menubar);
        menubar.add(file);
        menubar.add(edit);
        menubar.add(about);
        
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        
        about.add(notepad);
        add(scpan,BorderLayout.CENTER);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("New"))
        {
            area.setText("");
        }
        else if(ae.getActionCommand().equals("Open"))
        {
            JFileChooser chooser = new JFileChooser("D:/Java");
            chooser.setAcceptAllFileFilterUsed(false);//used for not accept all file only accept "txt" file
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");//this class used for choose which type of file open
            chooser.addChoosableFileFilter(restrict);
            
            int result = chooser.showOpenDialog(this);
            if(result == JFileChooser.APPROVE_OPTION)
            {
                File file = chooser.getSelectedFile();
                
                try
                {
                    System.out.println("HEki");
                    FileReader reader = new FileReader(file);
                    BufferedReader br = new BufferedReader(reader);
                    area.read(br, null);
                    br.close();
                    area.requestFocus();
                }catch(Exception e)
                {
                    System.out.print(e);
                }
            }
         }
        else if(ae.getActionCommand().equals("Save"))
        {
            final JFileChooser SaveAs = new JFileChooser();
            SaveAs.setApproveButtonText("Save");
            int actionDialog = SaveAs.showOpenDialog(this);
            if(actionDialog != JFileChooser.APPROVE_OPTION)
            {
                return;
            }
            File fileName = new File(SaveAs.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            try
            {
                outFile = new BufferedWriter(new FileWriter(fileName));
                area.write(outFile);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print")){
            try{
                area.print();
            }catch(Exception e){}
        }else if (ae.getActionCommand().equals("Exit")) {
            System.exit(0);
        }else if (ae.getActionCommand().equals("Copy")) {
            text = area.getSelectedText();
        }else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        }else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        }else if (ae.getActionCommand().equals("SelectAll")) {
            area.selectAll();
        }else if (ae.getActionCommand().equals("About Notepad")) 
        {
            try
            {
                new About().setVisible(true);

            }catch (Exception e) {
                e.printStackTrace();
            }
                        
        }
        
    }
    public static void main(String[] args) {
       new Notepad_clone();
    }
    
}
