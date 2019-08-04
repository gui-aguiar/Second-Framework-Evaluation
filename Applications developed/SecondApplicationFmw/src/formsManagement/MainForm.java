package formsManagement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextArea;
import machineLearning.Application;

public class MainForm extends JFrame{

	Panel mainPanel;
	Panel questionPanelsPane;
	JTextArea intructions;
	Label title;
	
	int numberOfQuestionForms;
	int numberOfQuestions;
	double[] dataToPredict;
	
	Application application;
	
	private static final long serialVersionUID = 1L;
	
	public static void main(String args[]) {
		new MainForm();
	}
	
	public MainForm () {
		configureCloseAction();
		defineDataInfo();
		setInstrucionsPanelComponents();
		setQuestionPanelsComponents();
		checkNumberOfPages();
		setFormInfo();	
		runApplication();		
	}

	private void configureCloseAction() {
		this.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		       application.close();		         // TODO: This can be edited to perform another actions 
		    }
		});
	}
		
	private void defineDataInfo() {
		this.setNumberOfQuestionForms(1);
		this.setNumberOfQuestions(4);
		dataToPredict = new double[this.getNumberOfQuestions()];
	}

	private void setFormInfo() {
		this.setTitle("Detector de Espécide de Flor de Íris");
		this.setInstructions(
			"Esta aplicação tem por objetivo realizar a detecção da espécie de flor de íris baseada nas seguintes características:" +
			"\r\n" +
			"\r\n. Tamanho e largura da sépala" +
			"\r\n. Tamanho e largura da pétala" +
			"\r\n" +
			"\r\nO processo de detecção faz uso de técnicas de aprendizado de máquina para descobrir qual a espécie de flor de íris que mais " +
			"se aproximada das características informadas como parâmetros.");
	}

	private void runApplication() {
		this.application = new Application(this.getNumberOfQuestions());		
		this.application.setNumberOfQuestionForms(this.getNumberOfQuestionForms());
	}
   
	public void setApplication(Application application) {
		this.application = application;
	}

	public int getNumberOfQuestionForms() {
		return numberOfQuestionForms;
	}

	public void setNumberOfQuestionForms(int numberOfQuestionForms) {
		this.numberOfQuestionForms = numberOfQuestionForms;
	}

	public int getNumberOfQuestions() {
		return numberOfQuestions;
	}

	public void setNumberOfQuestions(int numberOfQuestions) {
		this.numberOfQuestions = numberOfQuestions;
	}

	protected void setInstrucionsPanelComponents() {  // TODO - User can create another instruction panel
		this.setVisible(true);
		this.setBounds(100, 100, 680, 600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		mainPanel = new Panel();
		
		mainPanel.setBounds(10, 10, 680, 590);
		this.getContentPane().add(mainPanel);
		mainPanel.setLayout(null);
		mainPanel.setVisible(true);
		
		title = new Label();
		title.setAlignment(Label.CENTER);
		title.setFont(new Font("Arial", Font.BOLD, 22));
		title.setBounds(182, 35, 251, 66);
		mainPanel.add(title);
		
		intructions = new JTextArea();
		intructions.setLineWrap(true);
		intructions.setFont(new Font("Tahoma", Font.PLAIN, 11));
		intructions.setEditable(false);
		intructions.setBounds(10, 153, 594, 206);
		intructions.setOpaque(false);  
	    mainPanel.add(intructions);
		
		Button button = new Button("Iniciar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				startTest();
			}
		});
		button.setBounds(273, 500, 70, 22);
		mainPanel.add(button);
		this.repaint();
	}
	
	private void setQuestionPanelsComponents() { // TODO: This can be edited
		questionPanelsPane = new Panel();
		questionPanelsPane.setVisible(false);
		questionPanelsPane.setBounds(10, 10, 640, 590);
		getContentPane().add(questionPanelsPane);
		questionPanelsPane.setLayout(new CardLayout(0, 0));

		addQuestionPanels();	
	}	

	private void addQuestionPanels() {
        QuestionPanel irisFlowerQuestionPanel = new IrisFlowerQuestionPanel(this);
        irisFlowerQuestionPanel.setData(dataToPredict);
        irisFlowerQuestionPanel.setIsFirstPanel(true);
        irisFlowerQuestionPanel.setIsLastPanel(true);
        irisFlowerQuestionPanel.setIndex(0);

        questionPanelsPane.add("Questions1", irisFlowerQuestionPanel);
	}
	
	private void checkNumberOfPages() {
		if (this.numberOfQuestionForms != questionPanelsPane.getComponentCount()) {
			JOptionPane.showMessageDialog(this, "O número de páginas configurado não está de acordo com a configuração.",
				    "Atenção", JOptionPane.ERROR_MESSAGE);
			throw new UnsupportedOperationException("O número de páginas configurado não está de acordo com a configuração");
		}
	}
	
	protected void startTest() {
		if (application.getOperationMode() instanceof  machineLearning.Learn ) {
			application.train();
		} 

	    mainPanel.setVisible(false);
	    questionPanelsPane.setVisible(true);				
	}
		
	public void setInstructions(String text) {
		intructions.setText(text);		
	}
	
	public void setTitle(String text) {
		title.setText(text);		
	}
	
	public void finish() {
		double prediction = application.classify(dataToPredict);
		setFinishMessage(prediction);
	}

	private void setFinishMessage(double prediction) {
		String result = "Desconhecida";

		switch(new Double(prediction).intValue()) {
			case 0:
				result = "Iris setosa";
				break;
			case 1: ;
				result = "Iris versicolor";
				break;
			case 2: ;
				result = "Iris virginica";
				break;
		}

		JOptionPane.showMessageDialog(this, "Resultado do processo de detecção da espécie de flor de íris foi '" + result + "'",
				"Resultado!", JOptionPane.INFORMATION_MESSAGE);
	}
		
}