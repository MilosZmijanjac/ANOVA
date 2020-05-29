package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import jdistlib.F;
import jdistlib.T;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Controller {

    public Button izracunajRez;
    @FXML
    private Spinner<Integer> N=null;
    @FXML
    private Spinner<Integer> K =null;
    @FXML
    private Button nacrtajTabelu=null;
    @FXML
    GridPane matrix = new GridPane();
    @FXML
    private Label SSA=null;
    @FXML
    Label SSE=null;
    @FXML
    Label SST=null;
    @FXML
    Label ssSSA;
    @FXML
    Label ssSSE;
    @FXML
    Label ssSST;
    @FXML
    Label vSSA;
    @FXML
    Label vSSE;
    @FXML
    Label izracunatoF;
    @FXML
    Label c1;
    @FXML
    Label c2;
    @FXML
    Label tabelarnoF;
    @FXML
    ChoiceBox<Double> alfa=null;
    @FXML
    ChoiceBox<Integer> alt1=null;
    @FXML
    ChoiceBox<Integer> alt2=null;
    @FXML
    Label alfaLabel;
    @FXML
    Label alt1Label;
    @FXML
    Label alt2Label;

    Button izracunaj;
    Scene matrixScene;
    TextField[][] matrixTable;
    double ukupnaSuma;

   public void onPressNacrtajTabelu(ActionEvent actionEvent) throws IOException {
       int n=N.getValue();
       int k=K.getValue();
   matrixTable= new TextField[k+3][n+3];
   for (int x=0;x<(k+1);x++){
       for(int y=0;y<n+3;y++){
           matrixTable[x][y]=new TextField();
           matrixTable[x][y].setPrefHeight(20);
           matrixTable[x][y].setPrefWidth(110);
           matrixTable[x][y].setAlignment(Pos.CENTER);
           matrixTable[x][y].setEditable(true);
           if(y>n)
               matrixTable[x][y].setEditable(false);

           if(x==0&&y==0) {
               matrixTable[x][y].setText("N\\K");
               matrixTable[x][y].setEditable(false);
           }

           if(y==0&&x!=0){
                   matrixTable[x][y].setText(String.valueOf(x));
                   matrixTable[x][y].setEditable(false);
           }

           if(x==0&&y!=0){
               if(y==(n+1)){
                   matrixTable[x][y].setText("SV");
                   matrixTable[x][y].setEditable(false);
               }
               else if(y==(n+2)){
                   matrixTable[x][y].setText("EF");
                   matrixTable[x][y].setEditable(false);
               }
               else {
               matrixTable[x][y].setText(String.valueOf(y));
               matrixTable[x][y].setEditable(false);}
           }

           matrix.setRowIndex(matrixTable[x][y], y);
           matrix.setColumnIndex(matrixTable[x][y], x);
           matrix.getChildren().add(matrixTable[x][y]);

       }
   }
       izracunaj=new Button();
       izracunaj.setPrefHeight(40);
       izracunaj.setPrefWidth(100);
       izracunaj.setText("Izracunati");
       izracunaj.setOnAction(new EventHandler<ActionEvent>() {

           @Override
           public void handle(ActionEvent event) {
               try {
                   onPressIzracunaj();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       });
       matrix.setRowIndex(izracunaj, n+3);
       matrix.setColumnIndex(izracunaj, 0);
       matrix.getChildren().add(izracunaj);

       SSA=new Label();
       matrix.setRowIndex(SSA,n+5);
       matrix.setColumnIndex(SSA,0);
       matrix.getChildren().add(SSA);

       SSE=new Label();
       matrix.setRowIndex(SSE,n+5);
       matrix.setColumnIndex(SSE,1);
       matrix.getChildren().add(SSE);

       SST=new Label();
       matrix.setRowIndex(SST,n+5);
       matrix.setColumnIndex(SST,2);
       matrix.getChildren().add(SST);

       ssSSA=new Label();
       matrix.setRowIndex(ssSSA,n+6);
       matrix.setColumnIndex(ssSSA,0);
       matrix.getChildren().add(ssSSA);

       ssSSE=new Label();
       matrix.setRowIndex(ssSSE,n+6);
       matrix.setColumnIndex(ssSSE,1);
       matrix.getChildren().add(ssSSE);

       ssSST=new Label();
       matrix.setRowIndex(ssSST,n+6);
       matrix.setColumnIndex(ssSST,2);
       matrix.getChildren().add(ssSST);

       vSSA=new Label();
       matrix.setRowIndex(vSSA,n+7);
       matrix.setColumnIndex(vSSA,0);
       matrix.getChildren().add(vSSA);

       vSSE=new Label();
       matrix.setRowIndex(vSSE,n+7);
       matrix.setColumnIndex(vSSE,1);
       matrix.getChildren().add(vSSE);

       izracunatoF=new Label();
       matrix.setRowIndex(izracunatoF,n+8);
       matrix.setColumnIndex(izracunatoF,0);
       matrix.getChildren().add(izracunatoF);

       tabelarnoF=new Label();
       matrix.setRowIndex(tabelarnoF,n+8);
       matrix.setColumnIndex(tabelarnoF,1);
       matrix.getChildren().add(tabelarnoF);

       alfaLabel=new Label();
       alfaLabel.setText(" Alfa");
       alfaLabel.setPrefWidth(50);
       matrix.setRowIndex(alfaLabel,n+9);
       matrix.setColumnIndex(alfaLabel,0);
       matrix.getChildren().add(alfaLabel);

       alfa=new ChoiceBox<Double>();
       alfa.setValue(0.1);
       alfa.getItems().add(0.1);
       alfa.getItems().add(0.05);
       alfa.getItems().add(0.001);
       matrix.setRowIndex(alfa,n+9);
       matrix.setColumnIndex(alfa,1);
       matrix.getChildren().add(alfa);

       alt1Label=new Label();
       alt1Label.setText("Alternativa1: ");
       matrix.setRowIndex(alt1Label,n+10);
       matrix.setColumnIndex(alt1Label,0);
       matrix.getChildren().add(alt1Label);

       alt2Label=new Label();
       alt2Label.setText("Alternativa2: ");
       matrix.setRowIndex(alt2Label,n+11);
       matrix.setColumnIndex(alt2Label,0);
       matrix.getChildren().add(alt2Label);

       alt1=new ChoiceBox<Integer>();
       alt1.setValue(1);

       alt2=new ChoiceBox<Integer>();
       alt2.setValue(1);
       for (int i = 1; i <= N.getValue(); i++) {
           alt1.getItems().add(i);
           alt2.getItems().add(i);
       }
       matrix.setRowIndex(alt1,n+10);
       matrix.setColumnIndex(alt1,1);
       matrix.getChildren().add(alt1);
       matrix.setRowIndex(alt2,n+11);
       matrix.setColumnIndex(alt2,1);
       matrix.getChildren().add(alt2);

       c1=new Label();
       matrix.setRowIndex(c1,n+10);
       matrix.setColumnIndex(c1,2);
       matrix.getChildren().add(c1);

       c2=new Label();
       matrix.setRowIndex(c2,n+11);
       matrix.setColumnIndex(c2,2);
       matrix.getChildren().add(c2);


       matrixScene = new Scene(matrix, 900, 600);
       Stage stage = (Stage) nacrtajTabelu.getScene().getWindow();
       stage.setMaximized(true);
       stage.setScene(matrixScene);
   }



    public void onPressIzracunaj() throws IOException {
       ukupnaSuma=0.0;
       double sumaKolone=0.0;
       double srednjaVrijenostKolone=0.0;
       double efekat=0.0;
       for(int i=1;i<K.getValue()+1;i++) {
           sumaKolone=0.0;
           for (int j = 1; j < N.getValue() + 1; j++) {
               sumaKolone += Double.parseDouble(matrixTable[i][j].getText());
           }
           ukupnaSuma += sumaKolone;
           matrixTable[i][N.getValue()+1].setText(Double.toString(round(sumaKolone/(double)N.getValue())));
       }

       for(int i =1;i<K.getValue()+1;i++){
        srednjaVrijenostKolone= Double.parseDouble(matrixTable[i][N.getValue()+1].getText());
        efekat=srednjaVrijenostKolone-ukupnaSuma/(double)(N.getValue()*K.getValue());
        matrixTable[i][N.getValue()+2].setText(Double.toString(round(efekat)));
       }

        SSA.setText("SSA = "+Double.toString(round(SSA())));
        SSE.setText("SSE = "+Double.toString(round(SSE())));
        SST.setText("SST = "+Double.toString(round(SST())));
        ssSSA.setText("k-1 = "+Integer.toString(K.getValue() - 1));
        ssSSE.setText("k(n-1) = "+Integer.toString(K.getValue() * (N.getValue() - 1)));
        ssSST.setText("kn-1 = "+Integer.toString(K.getValue() * N.getValue() - 1));
        vSSA.setText("Sa² = "+Double.toString(round(SSA() / (double) (K.getValue() - 1))));
        vSSE.setText("Se² = "+Double.toString(round(SSE() / ((double) (K.getValue() * (N.getValue() - 1))))));
        izracunatoF.setText(" Izracunata F vrijednost = "+Double.toString(round((SSA() / (double) (K.getValue() - 1))/(SSE() / ((double) (K.getValue() * (N.getValue() - 1)))))));
        tabelarnoF.setText("  Tabelarna F vrijednost = "+Double.toString(tabF()));
        racunajKontrast();
    }

    public double SSA() {
       double SS=0.0;
        for(int i =1;i<K.getValue()+1;i++)
        SS+=Math.pow(Double.parseDouble(matrixTable[i][N.getValue()+2].getText()),2);
        return (double)N.getValue()*SS;
    }
    public double SSE() {
       double SS=0.0;
        for (int x = 1; x < (K.getValue() + 1); x++) {
            for (int y = 1; y < N.getValue() + 1; y++) {
                SS+=Math.pow((Double.parseDouble(matrixTable[x][y].getText())-(Double.parseDouble(matrixTable[x][N.getValue()+1].getText()))),2);
            }
        }
        return SS;
    }

    public double SST(){
       return SSE()+SSA();
    }

    public static double round(double value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(4, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public double tabF(){
       return round(F.quantile(alfa.getValue(), Double.parseDouble(ssSSA.getText().replace("k-1 = ","")), Double.parseDouble(ssSSE.getText().replace("k(n-1) = ","")), false,false));
    }

    public void racunajKontrast(){
       double alternativa1=Double.parseDouble(matrixTable[alt1.getValue()][N.getValue()+2].getText());
        double alternativa2=Double.parseDouble(matrixTable[alt2.getValue()][N.getValue()+2].getText());
        double c=alternativa1-alternativa2;
        double Sc = Math.sqrt((SSE()/Double.parseDouble(ssSSE.getText().replace("k(n-1) = ","")))) * Math.sqrt(2.0/(N.getValue()*K.getValue()));
        double C1 = c - T.quantile(alfa.getValue()/2 , (int)Double.parseDouble(ssSSE.getText().replace("k(n-1) = ","")) , false, false)*Sc;
        double C2 = c + T.quantile(alfa.getValue()/2 , (int)Double.parseDouble(ssSSE.getText().replace("k(n-1) = ","")) , false, false)*Sc;

        c1.setText("c1 = "+String.valueOf(round(C1)));
        c2.setText("c2 = "+String.valueOf(round(C2)));

    }
}
