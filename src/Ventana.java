import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import static java.awt.Font.PLAIN;
import javax.swing.border.LineBorder;

public class Ventana extends JFrame{
	GridBagConstraints gbc= new GridBagConstraints();
	GridBagLayout gbl= new GridBagLayout();
	JLabel txtResultado;
	 int totalBtn=20;
	    JButton botones[] = new JButton[totalBtn];
	    String textoBotones[] = {"c","/","*","ce","7","8","9","-","4","5","6","+","1","2","3","^","%","0",".","="};
	    int numerosBotones[] = {17, 12, 13, 14, 8, 9, 10, 4, 5, 6, 1, 2, 7,11, 15, 16};
	    boolean nuevoNumero = true;
		 boolean puntoDecimal = false;
	public Ventana() {
		getContentPane().setLayout(gbl); 
        setTitle("Calculadora"); 
        setSize(300,400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        getContentPane().setBackground(Color.BLACK); 
        setVisible(true); 
		pantalla();
		btnNum();
		
	}//constructor
	
	public void metodoMagico(Component c,int x, int y, int w, int h) {
		gbc.gridx=x;
		gbc.gridy=y;
		gbc.gridwidth=w;
		gbc.gridheight=h;
		gbc.fill=GridBagConstraints.HORIZONTAL;
		gbl.setConstraints(c, gbc);
	}
	
	public void estiloBtn(JButton c) {
		c.setFont(new Font("MONOSPACED",PLAIN,30));
		c.setOpaque(true); 
        c.setForeground(Color.BLACK); 
        add(c);
	}
	
	public void pantalla() {
		txtResultado = new JLabel("0"); 
		metodoMagico(txtResultado,0,0,4,1);
        txtResultado.setOpaque(true); 
        txtResultado.setBackground(Color.WHITE); 
        txtResultado.setForeground(Color.BLUE); 
        txtResultado.setBorder(new LineBorder(Color.DARK_GRAY)); 
        txtResultado.setFont(new Font("MONOSPACED", PLAIN, 40)); 
        txtResultado.setHorizontalAlignment(SwingConstants.RIGHT); 
        add(txtResultado); 
	}
	
	public void btnNum() {
		   int yBotones[] = {1, 1,  1,  1,  2,  2,  2,  2,  3,  3,  3,  3,  4,  4,  4,  4,  5, 5, 5, 5};
		   int xBotones[] = {0, 1,  2,  3,  0,  1,  2,  3,  0,  1,  2,  3,  0,  1,  2,  3,  0, 1, 2, 3};
		 int anchoBoton[] = {1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1, 1, 1};
		  int altoBoton[] = {1, 1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1,  1, 1, 1, 1};
		
		for (int i = 0; i < totalBtn; i++){
			 botones[i] = new JButton(textoBotones[i]);
			 metodoMagico(botones[i],xBotones[i],yBotones[i],anchoBoton[i],altoBoton[i]);
			 estiloBtn(botones[i]);
			}
		eventoBotones();
		eventoOperacion();
     }
	private void eventoBotones() {
        for (int i = 0; i < 16; i++){
            int numBoton = numerosBotones[i];
            botones[numBoton].addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (nuevoNumero){
                        if (!textoBotones[numBoton].equals("0")){
                            txtResultado.setText(textoBotones[numBoton]);
                            nuevoNumero = false;
                        }
                    }
                    else{
                    	txtResultado.setText(txtResultado.getText() + textoBotones[numBoton]);
                    }
                }
            });
        }
    }
	
	 private void eventoOperacion(){
	        botones[18].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                if (!puntoDecimal){
	                	txtResultado.setText(txtResultado.getText() + textoBotones[18]);
	                    puntoDecimal = true; 
	                    nuevoNumero = false;
	                } else if(puntoDecimal = true) {
	                	JOptionPane.showMessageDialog(null, "ya ingresaste un punto decimal");
	                }
	            }
	        });
	        botones[19].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Component c=(Component) e.getSource();
	        		if(c== botones[19]) {
	        			txtResultado.setText(Operaciones.operaciones(txtResultado.getText()));
	     	           
	        		}
	             }
	        });
	        botones[0].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Component c=(Component) e.getSource();
	        		if(c== botones[0]) {
	        			txtResultado.setText(" ");
	        			
	        		}
	             }
	        });
	        botones[3].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	            	Component c=(Component) e.getSource();
	        		if(c== botones[3]) {
	        			String resultado="";
	        			for(int i = 0; i <( txtResultado.getText().length()-1); i++) {	
	        				resultado=resultado+txtResultado.getText().charAt(i);
	        			}
	        			if(resultado=="") {
	        				resultado=" ";
	        			}
	        			txtResultado.setText(resultado);
	        			
	        		}
	             }
	        });
	    }
	
public static void main(String[] args) {
	SwingUtilities.invokeLater(new Runnable() {
		@Override
		public void run() {
			new Ventana();
		}
	});
	}
}
