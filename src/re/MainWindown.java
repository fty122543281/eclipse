/**
 * 
 */
package re;

import java.awt.Component;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 * @author ftyfty
 *
 */
public class MainWindown extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2948842774676877757L;
	private JButton choose;
	private JTextField path;
	private JButton rename;
	private JLabel lable;
	
	public MainWindown() throws HeadlessException {
		super();
		
	}
	
	public void create(){
		setTitle("按更新时间重命名");
		setSize(500, 200);
		setLayout(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		path = new JTextField();
		path.setSize(475,30);
		path.setLocation(5,20);
		path.setEditable(false);
		add(path);
		choose = new JButton("选择目录");
		choose.setSize(120, 30);
		choose.setLocation(5,70);
		choose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser c=new JFileChooser();
				c.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = c.showOpenDialog(choose.getParent());
				if(returnVal == JFileChooser.APPROVE_OPTION) {
					path.setText(c.getSelectedFile().getPath());
				}
			}
		});
		add(choose);
		rename = new JButton("重命名");
		rename.setSize(120, 30);
		rename.setLocation(355,70);
		rename.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reName();
			}
		});
		add(rename);
		lable = new JLabel("请选择一个目录");
		lable.setSize(475, 30);
		lable.setLocation(5, 110);
		add(lable);
		
		for (Component comp : getContentPane().getComponents()) {
			comp.setFont(new Font("微软雅黑",Font.PLAIN, 14));
		}
		
		setVisible(true);
	}

	private void reName(){
		 try
	        {
			 	if(path.getText()==null||"".equals(path.getText())){
			 		return;
			 	}
	        	File dfile = new File(path.getText());
	        	for (File file : dfile.listFiles()) {
	        		String path = file.getParent();
	        		String oldName = file.getName();
	            	String huozhui = oldName.substring(oldName.lastIndexOf("."));
	            	Date time = new Date(file.lastModified());
	            	Calendar cd = Calendar.getInstance();
	                cd.setTimeInMillis(System.currentTimeMillis());
	                String a = "";
	                int m = cd.get(Calendar.MILLISECOND);
	                if(m<10){
	                	a = "00" + m;
	                }else if(m>=10&&m<100){
	                	a = "0" + m;
	                }else{
	                	a = m + "";
	                }
	            	String newName = new SimpleDateFormat("yyyyMMddHHmm").format(time) + a +huozhui;
	            	lable.setText("正在将"+oldName+"重命名为"+newName);
	            	file.renameTo(new File(path+"\\"+newName));
				}
	        	lable.setText("重命名全部正常结束");
	        }catch (Exception e){
	        	e.printStackTrace();
	        	lable.setText("重命名是发生错误111");
	        }
	        
	}
	
	
	public static void main(String[] args){
		JFrame.setDefaultLookAndFeelDecorated(true);
		MainWindown win = new MainWindown();
		win.create();
	}
}
