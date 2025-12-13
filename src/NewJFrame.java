/*//GEN-LINE:variables
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author yesho
 */
import java.io.*;
import java.unit.*;
import javax.swing.*;

public class NewJFrame extends javax.swing.JFrame {
   //Constant
 private static final String QUESTION_FILE = "questions.txt" ;// question bank file
 private static final String WRONG_FILE ="wrong.txt";// wrong question storage file
 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(NewJFrame.class.getName());
    // Instance variables
      private ArrayList<Question> questionList = new ArrayList<>();// all questions
      private ArrayList<Question> wrongList = new ArrayList<>();// wrong questions
      private int currentIndex = 0;// current question index
      private boolean reviewingWrong = false;// review mode toggle

      //Grade Statistics
     private int correctCount = 0; // number of correct answers
     private int wrongCount = 0;  // number of wrong answers

    /**
     * Creates new form NewJFrame
     */
    public NewJFrame() {
        initComponents();// load UI components
        loadQuestions();// read questions from file
        loadWrong();// read wrong questions from file
        txtDisplay.setText("Click START to begin"); // initial screen message
    }
    // Method overloading (second constructor)
    public NewFrame(String title){
        this();// call default constructor
        setTitle(title);// set window title
    }
    //STATIC method
    public static void log(String msg){
        logger.info(msg);// print message to log
    }
    // Read all questions from the question file
    private void loadQuestions(){
        try (BufferedReader br = new BufferedReader(new FileReader(QUESTION_FILE))){
         String line; // each line represents one question
          // Read file line by line until no more lines exist
         while ((line = br.readLine())!= null){
             // Create a new EthicalQuestion object and add to question list
            // (polymorphism: EthicalQuestion extends Question)
             questionList.add(new EthicalQuestion(line));
         }
          // Log how many questions were successfully loaded
         log("Loaded questions: " + questionList.size());
        }catch (Exception e){
             // If file can't be found or read, show message on screen
            txtDisplay.setText("Cannot find "+QUESTION_FILE);
        }
    }   
    /**
     * Loads wrong questions from wrong.txt into wrongList.
     */
    private void loadWrong(){
        try (BufferedReader br=new BufferedReader(new FileReader(WRONG_FILE))){
            String line;
            //Read each line of the file (each line is one question)
            while((line = br.readLine())!=null){
                //Create a Question object (polymorphism: EthicalQuestion extends Question)
                wrongList.add(new EthicalQuestion(line));                
            }
        }catch (Exception e){
            //If file does not exist, simply log the message
            log("No wrong.txt found." );
        }
    }   
    /**
     * Saves all wrong questions from wrongList into wrong.txt.
     */
    private void saveWrong(){
        try (FileWriter fw = new FileWriter(WRONG_FILE)){
            //Write each wrong question line into the file
            for(Question q :wrongList) fw.write(q.raw+ "\n");
        }catch(Exception e){
            //log if saving fails
            log("Error saving wrong.txt");           
        }
    }
    /**
     * Shows the current question or completion message on screen.
     * @return 
     */
    private Void displayQuestion(){
        ArrayList<Question> list = reviewingWrong ? wrongList : questionList;
        //If the list is empty, show no-questions message
        if (list.isEmpty()){
            txtDisplay.setText("No questions available");
            return;
        }
        //If index exceeds number of questions, show completion message
        if (currentIndex >= list.size()){
            txtDisplay.setText("All questions comleted!");
            return;
        }
        Question q = list.get(currentIndex);
        txtDisplay.setText(q.show());
        lblQuestionNumber.setText("Q"+(currentIndex+1));
    }
    /**
     * Checks the user's answer and updates score and wrongList.
     * @param userChoice The answer selected by the user (A-D).
     */
    private void checkAnswer(StringuserChoice){
        ArrayList<Question>list = reviewingWrong ? wrongList : questionList;
        //If no more questions to check, exit
        if (currentIndex >= list.size()) return;
        Question q =list.get(currentIndex);
        boolean isCorrect = q.isCorrect(userChoice);
        String result = isCorrect ? "Correct!" : "Wrong!";
        txtDisplay.setText(result+"\n\n"+q.show());
        //Only track score in normal mode(not in review-wrong mode)
        if(!reviewingWrong){
            if(isCorrect) correctcount++;   //Increase correct answer counter
            else{
                wrongCount++;     //Increase wrong answer counter
                wrongList.add(q);
                saveWrong;
            }
        }
        currentIndex++;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txtDisplay = new javax.swing.JTextArea();
        btnA = new javax.swing.JButton();
        btnB = new javax.swing.JButton();
        btnC = new javax.swing.JButton();
        btnD = new javax.swing.JButton();
        btnStart = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnReviewWrong = new javax.swing.JButton();
        btnFinish = new javax.swing.JButton();
        lblQuestionNumber = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDisplay.setColumns(20);
        txtDisplay.setRows(5);
        jScrollPane1.setViewportView(txtDisplay);

        btnA.setBackground(new java.awt.Color(255, 204, 153));
        btnA.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnA.setText("A");
        btnA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAActionPerformed(evt);
            }
        });

        btnB.setBackground(new java.awt.Color(153, 255, 255));
        btnB.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnB.setText("B");
        btnB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBActionPerformed(evt);
            }
        });

        btnC.setBackground(new java.awt.Color(204, 255, 204));
        btnC.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnC.setText("C");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnD.setBackground(new java.awt.Color(204, 204, 255));
        btnD.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnD.setText("D");
        btnD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDActionPerformed(evt);
            }
        });

        btnStart.setBackground(new java.awt.Color(255, 204, 204));
        btnStart.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnStart.setText("Start Quiz");
        btnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStartActionPerformed(evt);
            }
        });

        btnNext.setBackground(new java.awt.Color(204, 204, 204));
        btnNext.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnNext.setText("Next");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnReviewWrong.setBackground(new java.awt.Color(255, 255, 204));
        btnReviewWrong.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 18)); // NOI18N
        btnReviewWrong.setText("Review Wrong");
        btnReviewWrong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReviewWrongActionPerformed(evt);
            }
        });

        btnFinish.setBackground(new java.awt.Color(255, 204, 255));
        btnFinish.setFont(new java.awt.Font("Microsoft YaHei UI", 0, 24)); // NOI18N
        btnFinish.setText("Finish");
        btnFinish.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinishActionPerformed(evt);
            }
        });

        lblQuestionNumber.setText(" ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnC)
                    .addComponent(btnA))
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnB)
                    .addComponent(btnD))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnStart)
                        .addGap(50, 50, 50))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnFinish, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblQuestionNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 284, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(60, 60, 60))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnReviewWrong)
                        .addGap(44, 44, 44))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnB)
                            .addComponent(btnStart)
                            .addComponent(btnA)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(btnNext)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblQuestionNumber)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnFinish)
                            .addComponent(btnReviewWrong))
                        .addGap(25, 25, 25))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnC)
                            .addComponent(btnD))
                        .addGap(45, 45, 45))))
        );

        pack();
    }// </editor-fold>                        

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {                                        
        displayQuestion();
    }                                       

    private void btnFinishActionPerformed(java.awt.event.ActionEvent evt) {                                          
        txtDisplay.setText(
        "Finished!\n\n"+
                "Correct: "+correctCount+"\n"+  //show how many answers were correct
                "Wrong: "+wrongCount + "\n"+    //show how many were wrong
                "Total: "(correctCount + wrongCount)    //show total questions attempted
                );
    
    }                                         

    private void btnStartActionPerformed(java.awt.event.ActionEvent evt) {                                         
        reviewingWrong = false;    //start normal mode, not reviewing wrong questions
        currentIndex = 0;    //go tp first question
        correctCount = 0;    //reset score
        wrongCount = 0;      //reset wrong counter
        displayQuestion();   //show first question
    }                                        

    private void btnReviewWrongActionPerformed(java.awt.event.ActionEvent evt) {                                               
        reviewingWrong = true;         //switch to wrong-question review mode
        currentIndex = 0;        //restart from the beginning of wrongList
        displayQuestion();          //show the first wrong question
    }                                              

    private void btnAActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void btnBActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    private void btnDActionPerformed(java.awt.event.ActionEvent evt) {                                     
        // TODO add your handling code here:
    }                                    

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new NewJFrame().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnA;
    private javax.swing.JButton btnB;
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnD;
    private javax.swing.JButton btnFinish;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnReviewWrong;
    private javax.swing.JButton btnStart;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton lblQuestionNumber;
    private javax.swing.JTextArea txtDisplay;
    // End of variables declaration                   
}
